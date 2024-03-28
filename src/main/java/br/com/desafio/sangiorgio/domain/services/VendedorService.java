package br.com.desafio.sangiorgio.domain.services;

import br.com.desafio.sangiorgio.application.payloads.requests.VendedorRequest;
import br.com.desafio.sangiorgio.application.payloads.responses.VendedorResponse;
import br.com.desafio.sangiorgio.domain.model.Vendedor;
import br.com.desafio.sangiorgio.infrastructure.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public VendedorResponse create(VendedorRequest vendedorRequest) {
        Vendedor vendedor = new Vendedor();
        vendedor.setNomeVendedor(vendedorRequest.getNomeVendedor());
        return VendedorResponse.fromEntity(vendedorRepository.save(vendedor));
    }
}
