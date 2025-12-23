package br.com.sgp.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.model.Projeto;
import br.com.sgp.api.service.ProjetoService;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {

    @Autowired
   private ProjetoService projetoService;

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {
        return ResponseEntity.ok().body(projetoService.consultarProjetos());
    }

    @GetMapping(value = "/busca/status")
    public ResponseEntity<List<Projeto>> listarProjetoPeloStatus(@RequestParam ProjetoStatus status) {
        return ResponseEntity.ok().body(projetoService.consultarProjetoPeloStatus(status));
    }

    @GetMapping(value = "/busca/dataconclusao")
    public ResponseEntity<Optional<Projeto>> listarProjetosPelaDataDeConclusao(@RequestParam LocalDate dataConclusao) {
        return ResponseEntity.ok().body(projetoService.consultarProjetoPeloDataConclusao(dataConclusao));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Projeto>> buscarProjetoPeloId(@PathVariable Long id) {
        Optional<Projeto> projetoExsite = projetoService.consultarProjetoPeloId(id);
        if (projetoExsite.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(projetoService.consultarProjetoPeloId(id));
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Projeto> adicionarProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.ok().body(projetoService.salvarProjeto(projeto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerProjeto(@PathVariable Long id) {
        Optional<Projeto> projetoExiste = projetoService.consultarProjetoPeloId(id);
        if (projetoExiste.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
      public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto){
      Optional<Projeto> projetoExiste=projetoService.consultarProjetoPeloId(id);
      if(projetoExiste.isEmpty()){
        return ResponseEntity.notFound().build();
      }
      projeto.setId(id);

      return ResponseEntity.ok().body(projetoService.salvarProjeto(projeto));
      }
}