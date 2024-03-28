package br.com.desafio.sangiorgio.application.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {
    @NotNull
    private String codigoVendedor;
    @JsonProperty("pagamentos")
    private List<PagamentoRequest> pagamentos;

}
