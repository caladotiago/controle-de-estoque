package tech.calado.controle_de_estoque.common.exception;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message, null, true, false);
    }
}
