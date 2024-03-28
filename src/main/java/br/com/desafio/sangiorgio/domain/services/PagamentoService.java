package br.com.desafio.sangiorgio.domain.services;


import br.com.desafio.sangiorgio.application.enums.StatusPagamentoEnum;
import br.com.desafio.sangiorgio.application.excption.BusinessException;
import br.com.desafio.sangiorgio.application.payloads.requests.PagamentoRequest;
import br.com.desafio.sangiorgio.application.payloads.requests.PedidoRequest;
import br.com.desafio.sangiorgio.domain.model.Pagamento;
import br.com.desafio.sangiorgio.domain.model.Vendedor;
import br.com.desafio.sangiorgio.infrastructure.repositories.PagamentoRepository;
import br.com.desafio.sangiorgio.infrastructure.repositories.VendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PagamentoService {
    private final ApplicationEventPublisher eventPublisher;
    private final PagamentoRepository pagamentoRepository;
    private final VendedorRepository vendedorRepository;

    public PagamentoService(ApplicationEventPublisher eventPublisher, PagamentoRepository pagamentoRepository, VendedorRepository vendedorRepository) {
        this.eventPublisher = eventPublisher;
        this.pagamentoRepository = pagamentoRepository;
        this.vendedorRepository = vendedorRepository;
    }

    public void create(PedidoRequest pedidoRequest) {
        Vendedor vendedor = vendedorRepository.findById(Long.parseLong(pedidoRequest.getCodigoVendedor()))
                .orElseThrow(() -> new BusinessException("Vendedor não encontrado.", "VENDEDOR_NAO_ENCONTRADO"));


        for (var pagamentoRequest : pedidoRequest.getPagamentos()) {
            Pagamento pagamento = processarPagamento(vendedor, pagamentoRequest);

            log.info("Creating payment: {}", pagamento);
            pagamentoRepository.save(pagamento);
            eventPublisher.publishEvent(new PagamentoCreatedEvent(pagamento));

        }

    }

    private Pagamento processarPagamento(Vendedor vendedor, PagamentoRequest pagamento) {
        double originalValue = getOriginalValueForCobranca(pagamento.getCodigo());
        StatusPagamentoEnum status = null;
        if (pagamento.getValorPago() < originalValue) {
            status = StatusPagamentoEnum.PARCIAL;
        } else if (pagamento.getValorPago() > originalValue) {
            status = StatusPagamentoEnum.EXCEDENTE;
        } else {
            status = StatusPagamentoEnum.TOTAL;
        }

        return new Pagamento(null, pagamento.getValorPago(), status);
    }

    private double getOriginalValueForCobranca(String codigoCobranca) {

        return 100.00;
    }

    public record PagamentoCreatedEvent(Pagamento pagamento) {
    }


}
