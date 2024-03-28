package br.com.desafio.sangiorgio.application.payloads.responses;

import br.com.desafio.sangiorgio.application.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private double valorPago;
    private StatusPagamentoEnum status;
}

