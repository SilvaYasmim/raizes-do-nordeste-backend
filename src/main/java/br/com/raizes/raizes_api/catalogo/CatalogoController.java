package br.com.raizes.raizes_api.catalogo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lojas")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }
    @GetMapping("/{id_loja}/catalogo")
    public CatalogoResponse buscarCatalogo(@PathVariable("id_loja") Long idLoja) {
        return catalogoService.buscarCatalogoPorLoja(idLoja);
    }
}