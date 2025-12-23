package br.com.sgp.api.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.api.enums.PrioridadeTarefa;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
  List<Tarefa> findByPrioridade (PrioridadeTarefa prioridade); //achar tarefa pela prioridade
  List<Tarefa> findByStatus (TarefaStatus status);  //achar tarefa pelo status 
  //retorno       nome           entrada 
}
