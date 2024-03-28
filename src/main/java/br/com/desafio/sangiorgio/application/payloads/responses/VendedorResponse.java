package br.com.desafio.sangiorgio.application.payloads.responses;

import br.com.desafio.sangiorgio.domain.model.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorResponse {
    private Long id;
    private String nomeVendedor;

    public Vendedor toEntity() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(this.id);
        vendedor.setNomeVendedor(this.nomeVendedor);
        return vendedor;
    }

    public static VendedorResponse fromEntity(Vendedor vendedor) {
        return new VendedorResponse(vendedor.getId(), vendedor.getNomeVendedor());
    }
}
