package caeta.techalanger.adapter.driver.controller.produto;

import caeta.techalanger.adapter.driver.controller.produto.request.ProdutoRequest;
import caeta.techalanger.adapter.driver.controller.produto.request.ProdutoUpdate;
import caeta.techalanger.adapter.driver.controller.produto.response.ProdutoResponse;
import caeta.techalanger.core.application.ports.ProdutoServicePort;
import caeta.techalanger.core.domain.Categoria;
import caeta.techalanger.core.domain.Produto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    private final ProdutoServicePort servicePort;

    public ProdutoController(ProdutoServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request) {
        Produto novoProduto = servicePort.cadastraProduto(request.paraProduto());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(novoProduto.getId())
            .toUri();

        return ResponseEntity.created(location).body(ProdutoResponse.paraDomainResponse(novoProduto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> editaProduto(@PathVariable Long id, @RequestBody @Valid ProdutoUpdate request) {
        Produto editaProduto = servicePort.editaProduto(id, request.paraProduto());

        return ResponseEntity.ok(ProdutoResponse.paraDomainResponse(editaProduto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable Long id) {
        Produto novoProduto = servicePort.buscarProdutoPorId(id);
        return ResponseEntity.ok(novoProduto);
    }

    @DeleteMapping("/{id}")
    public void removerProdutoPorId(@PathVariable Long id) {
        servicePort.removerProduto(id);
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> buscaProdutoPorCategoria(@PathVariable String categoria) {
        List<Produto> listaDeProduto = servicePort.buscarProdutoPorCategoria(Categoria.valueOf(categoria));
        return ResponseEntity.ok(listaDeProduto);
    }
}
