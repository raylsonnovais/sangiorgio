package br.com.desafio.sangiorgio.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendedor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vendedor_id_seq")
    @SequenceGenerator(name = "vendedor_id_seq", sequenceName = "vendedor_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome_vendedor")
    private String nomeVendedor;
}
