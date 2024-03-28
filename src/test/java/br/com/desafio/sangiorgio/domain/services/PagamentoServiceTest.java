package br.com.desafio.sangiorgio.domain.services;

import br.com.desafio.sangiorgio.application.enums.StatusPagamentoEnum;
import br.com.desafio.sangiorgio.application.excption.BusinessException;
import br.com.desafio.sangiorgio.application.payloads.requests.PagamentoRequest;
import br.com.desafio.sangiorgio.application.payloads.requests.PedidoRequest;
import br.com.desafio.sangiorgio.domain.model.Pagamento;
import br.com.desafio.sangiorgio.domain.model.Vendedor;
import br.com.desafio.sangiorgio.infrastructure.repositories.PagamentoRepository;
import br.com.desafio.sangiorgio.infrastructure.repositories.VendedorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
class PagamentoServiceTest {

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void testCreate_VendedorNotFound() {
        PedidoRequest pedidoRequest = new PedidoRequest("1", Collections.emptyList());
        Mockito.when(vendedorRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(BusinessException.class, () -> pagamentoService.create(pedidoRequest));
    }

    @Test
    void testCreate_ParcialPayment() {
        PedidoRequest pedidoRequest = new PedidoRequest("1", Collections.singletonList(new PagamentoRequest(50.0, "1")));
        Vendedor vendedor = new Vendedor(1L, "João");
        Mockito.when(vendedorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(vendedor));
        Mockito.when(pagamentoRepository.save(any())).thenReturn(null);
        pagamentoService.create(pedidoRequest);
        Mockito.verify(pagamentoRepository).save(new Pagamento(null, 50.0, StatusPagamentoEnum.PARCIAL));
    }


}
