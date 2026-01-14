package br.com.sgp.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.sgp.api.enums.PrioridadeTarefa;
import br.com.sgp.api.enums.TarefaStatus;
import br.com.sgp.api.model.Tarefa;
import br.com.sgp.api.service.TarefaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
  @Autowired
  private TarefaService tarefaService;

  @GetMapping
  public List<Tarefa> listarTarefas(){
    return tarefaService.consultarTarefas();
  }
//POR STATUS
@GetMapping("/busca/status")
public ResponseEntity<List<Tarefa>> buscarTarefaPeloStatus(@RequestParam TarefaStatus status) {
    return ResponseEntity.ok().body(tarefaService.consultarTarefaPorStatus(status));
}
//POR ID
@GetMapping("/{id}")
public ResponseEntity<Optional<Tarefa>> buscarTarefaPeloId(@PathVariable Long id){
  Optional<Tarefa> tarefaExiste = tarefaService.consultarTarefasPeloId(id);
  if(tarefaExiste.isEmpty()){
    return ResponseEntity.notFound().build();
  }
  return ResponseEntity.ok().body(tarefaService.consultarTarefasPeloId(id));
}
//por prioridade
@GetMapping("/busca/prioridade")
public ResponseEntity<List<Tarefa>> buscarPelaPrioridade(@RequestParam PrioridadeTarefa prioridade){
  return ResponseEntity.ok().body(tarefaService.consultarTarefaPorPrioridade(prioridade));
}

//ADICIONAR TAREFA
@PostMapping(value = "/cadastrar")
public ResponseEntity<Tarefa> adicionarTarefa(@Valid @RequestBody Tarefa tarefa){
return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefa));
}

//DELETAR TAREFA
@DeleteMapping("/{id}")
public ResponseEntity<Void> removerTarefa(@PathVariable Long id){
  Optional<Tarefa> tarefaExiste = tarefaService.consultarTarefasPeloId(id);
  if (tarefaExiste.isEmpty()){
    return ResponseEntity.notFound().build();
  }
  tarefaService.deletarTarefa(id);
  return ResponseEntity.noContent().build();
}

//ATUALIZAR TAREFA
@PutMapping("/{id}")
public ResponseEntity<Tarefa> atualizarTarefa(@RequestBody Tarefa tarefa, @PathVariable Long id){
  Optional<Tarefa> tarefaExiste = tarefaService.consultarTarefasPeloId(id);
  if(tarefaExiste.isEmpty()){
    return ResponseEntity.notFound().build();
  }
  tarefa.setId(id);
  return ResponseEntity.ok().body(tarefaService.salvarTarefa(tarefa));
}
}