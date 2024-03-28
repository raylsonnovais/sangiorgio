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
@Table(name = "cobranca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cobranca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobranca_id_seq")
    @SequenceGenerator(name = "cobranca_id_seq", sequenceName = "cobranca_id_seq", allocationSize = 1)
    @Column(name = "id")
    private String id;

    @Column
    private double valorOriginal;
}

