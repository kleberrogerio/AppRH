/*package com.AppRH.AppRH.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CustomErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
        return new CustomErrorResponse(HttpStatus.FORBIDDEN.value(), "Acesso negado",
                "Desculpe, você não possui permissão para acessar este recurso.");
    }

    // Outros handlers de exceção podem ser adicionados aqui

    // Classe de modelo para representar a resposta de erro personalizada
    private static class CustomErrorResponse {
        private int statusCode;
        private String error;
        private String message;

        public CustomErrorResponse(int statusCode, String error, String message) {
            this.statusCode = statusCode;
            this.error = error;
            this.message = message;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}*/