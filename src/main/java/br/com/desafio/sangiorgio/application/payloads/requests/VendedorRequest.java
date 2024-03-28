package br.com.desafio.sangiorgio.application.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorRequest {
    private String nomeVendedor;
}
