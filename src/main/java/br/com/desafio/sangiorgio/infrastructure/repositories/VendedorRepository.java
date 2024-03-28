package br.com.desafio.sangiorgio.infrastructure.repositories;

import br.com.desafio.sangiorgio.domain.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
}
