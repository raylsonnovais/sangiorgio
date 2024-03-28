package br.com.desafio.sangiorgio.application.excption;

public class BusinessException extends RuntimeException{

        public BusinessException(String message, String vendedorNaoEncontrado) {
            super(message);
        }
}
