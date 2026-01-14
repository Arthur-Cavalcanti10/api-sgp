package br.com.sgp.api.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
public ProjetoNaoEncontradoException(Long id){
  super(String.format(" projeto de id =%d nao encontrado", id));
}
}
