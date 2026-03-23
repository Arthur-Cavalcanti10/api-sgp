package br.com.sgp.api.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

  public TarefaNaoEncontradaException(Long id){
    super(String.format("Tarefa de ID = %d nao encontrada", id));
  }
}
