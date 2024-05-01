package br.com.ignite.rocketseat.gestaovagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Vaga não encontrada");
    }
}
