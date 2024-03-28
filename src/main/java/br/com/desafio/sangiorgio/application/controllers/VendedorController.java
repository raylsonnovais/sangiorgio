package br.com.desafio.sangiorgio.application.controllers;

import br.com.desafio.sangiorgio.application.payloads.requests.VendedorRequest;
import br.com.desafio.sangiorgio.application.payloads.responses.PagamentoResponse;
import br.com.desafio.sangiorgio.application.payloads.responses.VendedorResponse;
import br.com.desafio.sangiorgio.domain.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Vendas", description = "API de Vendas")
@RestController
@RequestMapping("/api/v1/vendedor")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Operation(summary = "Insere um vendedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = VendedorResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Não existem Registros", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<VendedorResponse> create(@RequestBody VendedorRequest vendedorRequest) {
        return ResponseEntity.ok().body(vendedorService.create(vendedorRequest));
    }
}
