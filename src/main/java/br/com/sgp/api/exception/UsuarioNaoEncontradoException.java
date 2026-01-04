package br.com.sgp.api.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

  public UsuarioNaoEncontradoException(Long id){
    super(String.format("Usuario de ID =%d nao encontrado", id));
  }

  public UsuarioNaoEncontradoException(String cpf){
    super(String.format("Usuario de CPF =%s nao encontrado", cpf));
  }

}
