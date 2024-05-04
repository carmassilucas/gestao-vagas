package br.com.ignite.rocketseat.gestaovagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Empresa não encontrada");
    }
}
