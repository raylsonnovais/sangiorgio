package br.com.desafio.sangiorgio.application.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequest {

    @NotNull
    @JsonProperty("valorPago")
    private double valorPago;

    @NotNull
    @JsonProperty("codigo")
    private String codigo;

}
