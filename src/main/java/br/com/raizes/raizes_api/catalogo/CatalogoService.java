package br.com.raizes.raizes_api.catalogo;

import br.com.raizes.raizes_api.loja.Loja;
import br.com.raizes.raizes_api.loja.LojaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CatalogoService {

    private final LojaRepository lojaRepository;
    private final EstoqueRepository estoqueRepository;

    public CatalogoService(LojaRepository lojaRepository, EstoqueRepository estoqueRepository) {
        this.lojaRepository = lojaRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public CatalogoResponse buscarCatalogoPorLoja(Long idLoja) {
        // 1. Busca a loja; se não encontrar, devolve 404 Not Found
        Loja loja = lojaRepository.findById(idLoja)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada"));

        // 2. Busca todos os estoques daquela loja
        List<Estoque> estoques = estoqueRepository.findByLojaIdLoja(idLoja);

        // 3. Transforma cada Estoque em ItemCatalogoResponse
        List<ItemCatalogoResponse> itens = estoques.stream()
                .map(estoque -> new ItemCatalogoResponse(
                        estoque.getProduto().getIdProduto(),
                        estoque.getProduto().getNome(),
                        estoque.getProduto().getPreco(),
                        estoque.getProduto().getCategoria(),
                        estoque.getQuantidade(),
                        estoque.getDisponivel()
                ))
                .toList();

        // 4. Monta e devolve o DTO final
        return new CatalogoResponse(loja.getIdLoja(), loja.getNome(), itens);
    }
}
