package br.com.raizes.raizes_api.pedido;

import br.com.raizes.raizes_api.catalogo.Estoque;
import br.com.raizes.raizes_api.catalogo.EstoqueRepository;
import br.com.raizes.raizes_api.catalogo.Produto;
import br.com.raizes.raizes_api.catalogo.ProdutoRepository;
import br.com.raizes.raizes_api.cliente.Cliente;
import br.com.raizes.raizes_api.cliente.ClienteRepository;
import br.com.raizes.raizes_api.loja.Loja;
import br.com.raizes.raizes_api.loja.LojaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PedidoService {

    private final ClienteRepository clienteRepository;
    private final LojaRepository lojaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoService(
            ClienteRepository clienteRepository,
            LojaRepository lojaRepository,
            ProdutoRepository produtoRepository,
            EstoqueRepository estoqueRepository,
            PedidoRepository pedidoRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.lojaRepository = lojaRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public PedidoResponse criarPedido(CriarPedidoRequest request) {
        // 1. Buscar cliente e loja (404 se algum não existir)
        Cliente cliente = clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Loja loja = lojaRepository.findById(request.idLoja())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        // Validar regra de agendamento exclusivo no APP (400 Bad Request)
        if (request.dataHoraAgendamento() != null && request.canal() != CanalPedido.APP) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agendamento de pedido é permitido apenas pelo canal APP"
            );
        }

        // 2. Montar o Pedido inicial
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setLoja(loja);
        pedido.setCanal(request.canal());
        pedido.setDataHoraAgendamento(request.dataHoraAgendamento());
        pedido.setConsumirNoLocal(request.consumirNoLocal());
        pedido.setStatus(StatusPedido.RECEBIDO);
        pedido.setDataHora(LocalDateTime.now());
        pedido.setCodigoRetirada(gerarCodigoRetirada());

        // 3. Processar cada item do request
        for (ItemPedidoRequest itemReq : request.itens()) {
            Produto produto = produtoRepository.findById(itemReq.idProduto())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Produto não encontrado: ID " + itemReq.idProduto()
                    ));

            Estoque estoque = estoqueRepository
                    .findByLojaIdLojaAndProdutoIdProduto(loja.getIdLoja(), produto.getIdProduto())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Produto sem estoque cadastrado nesta loja: " + produto.getNome()
                    ));

            int quantidadeDisponivel = estoque.getQuantidade() != null ? estoque.getQuantidade() : 0;
            boolean estaDisponivel = Boolean.TRUE.equals(estoque.getDisponivel());

            if (!estaDisponivel || quantidadeDisponivel < itemReq.quantidade()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Estoque insuficiente ou produto indisponível: " + produto.getNome()
                );
            }

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemReq.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco()); // preço congelado do servidor

            pedido.getItens().add(itemPedido);

            int novaQuantidade = quantidadeDisponivel - itemReq.quantidade();
            estoque.setQuantidade(novaQuantidade);
            if (novaQuantidade == 0) {
                estoque.setDisponivel(false);
            }
        }

        // 4. Salvar pedido (itens via cascade; estoque via dirty checking do @Transactional)
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // 5. Converter entidade salva para PedidoResponse
        List<ItemPedidoResponse> itensResponse = pedidoSalvo.getItens().stream()
                .map(item -> new ItemPedidoResponse(
                        item.getIdItemPedido(),
                        item.getProduto().getIdProduto(),
                        item.getQuantidade(),
                        item.getPrecoUnitario()
                ))
                .toList();

        return new PedidoResponse(
                pedidoSalvo.getIdPedido(),
                pedidoSalvo.getCliente().getIdCliente(),
                pedidoSalvo.getLoja().getIdLoja(),
                pedidoSalvo.getCodigoRetirada(),
                pedidoSalvo.getCanal(),
                pedidoSalvo.getStatus(),
                pedidoSalvo.getDataHora(),
                pedidoSalvo.getDataHoraAgendamento(),
                pedidoSalvo.getConsumirNoLocal(),
                itensResponse
        );
    }

    private String gerarCodigoRetirada() {
        int numero = ThreadLocalRandom.current().nextInt(1000, 10000);
        return "RN-" + numero;
    }
}