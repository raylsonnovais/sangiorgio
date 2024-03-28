package br.com.desafio.sangiorgio.application.controllers;

import br.com.desafio.sangiorgio.application.excption.BusinessException;
import br.com.desafio.sangiorgio.application.payloads.requests.PedidoRequest;
import br.com.desafio.sangiorgio.application.payloads.responses.PagamentoResponse;
import br.com.desafio.sangiorgio.domain.services.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pagamentos", description = "API de Pagamentos")
@RestController
@RequestMapping("/api/v1/payments")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Realiza um pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PagamentoResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Não existem Registros", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/pay")
    public void processPayment(@RequestBody PedidoRequest pedidoRequest ) {
        try {
            pagamentoService.create(pedidoRequest);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), "ERRO_AO_SALVAR_PAGAMENTO");
        }
    }
}
