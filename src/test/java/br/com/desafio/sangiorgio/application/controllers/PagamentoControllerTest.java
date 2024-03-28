package br.com.desafio.sangiorgio.application.controllers;

import br.com.desafio.sangiorgio.application.controllers.PagamentoController;
import br.com.desafio.sangiorgio.application.excption.BusinessException;
import br.com.desafio.sangiorgio.application.payloads.requests.PedidoRequest;
import br.com.desafio.sangiorgio.domain.services.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PagamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
    }

    @Test
    void testProcessPayment_Success() throws Exception {
        PedidoRequest pedidoRequest = new PedidoRequest();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/payments/pay")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"some\": \"data\" }"))
                .andExpect(status().isOk());
    }

}

