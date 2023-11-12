package br.com.ignite.rocketseat.gestaovagas.exceptions;

public class CandidateFoundException extends RuntimeException {
    public CandidateFoundException() {
        super("Usuário já existe");
    }
}
