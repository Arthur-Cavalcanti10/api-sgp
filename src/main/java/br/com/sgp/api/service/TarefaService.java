package br.com.sgp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.enums.PrioridadeTarefa;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;
import br.com.sgp.api.repository.TarefaRepository;

@Service
public class TarefaService {
   @Autowired
   private TarefaRepository tarefaRepository;
   //criar
   public Tarefa salvarTarefa(Tarefa tarefa){
    return tarefaRepository.save(tarefa);
   }
   //deletar
   public void deletarTarefa(Long id){
    tarefaRepository.deleteById(id);
   }
   //consultar
   public List<Tarefa> consultarTarefas(){
    return tarefaRepository.findAll();
   }

   public Optional<Tarefa> consultarTarefasPeloId(Long id){
   return tarefaRepository.findById(id);
   }

   public List<Tarefa> consultarTarefaPorStatus(TarefaStatus status){
    return tarefaRepository.findByStatus(status);
   }

   public List<Tarefa> consultarTarefaPorPrioridade(PrioridadeTarefa prioridade){
    return tarefaRepository.findByPrioridade(prioridade);
   }



}
