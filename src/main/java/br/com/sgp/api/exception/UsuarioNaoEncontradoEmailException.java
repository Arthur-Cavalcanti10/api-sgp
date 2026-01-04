package br.com.sgp.api.exception;

public class UsuarioNaoEncontradoEmailException extends RuntimeException {
    public UsuarioNaoEncontradoEmailException(String email){
    super(String.format("Usuario de email =%d nao encontrado", email));
  }
}
