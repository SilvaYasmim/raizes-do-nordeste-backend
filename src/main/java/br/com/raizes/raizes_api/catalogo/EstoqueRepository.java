package br.com.raizes.raizes_api.catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    List<Estoque> findByLojaIdLoja(Long idLoja);

    Optional<Estoque> findByLojaIdLojaAndProdutoIdProduto(Long idLoja, Long idProduto);

}
