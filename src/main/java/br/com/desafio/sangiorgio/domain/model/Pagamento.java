package br.com.desafio.sangiorgio.domain.model;

import br.com.desafio.sangiorgio.application.enums.StatusPagamentoEnum;
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
@Table(name = "pagamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_id_seq")
    @SequenceGenerator(name = "pagamento_id_seq", sequenceName = "pagamento_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor_pago")
    private double valorPago;

    @Column(name = "status")
    private StatusPagamentoEnum status;


}
