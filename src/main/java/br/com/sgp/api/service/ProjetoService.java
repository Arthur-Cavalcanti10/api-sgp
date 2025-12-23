package br.com.sgp.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.model.Projeto;
import br.com.sgp.api.repository.ProjetoRepository;

@Service
public class ProjetoService {
  @Autowired
  private ProjetoRepository projetoRepository;
  //criar
  public Projeto salvarProjeto(Projeto projeto){
   return projetoRepository.save(projeto);
  }
  //delete 
  public void deletarProjeto(Long id){
    projetoRepository.deleteById(id);;
  }
  //consultar
  public List<Projeto> consultarProjetos(){
    return projetoRepository.findAll();
  }

  public List<Projeto> consultarProjetoPeloStatus(ProjetoStatus status){
    return projetoRepository.findByStatus(status);
  }
  
   public Optional<Projeto> consultarProjetoPeloDataConclusao(LocalDate dataConclusao){
    return projetoRepository.findByDataConclusao(dataConclusao);
  }
   
     public Optional<Projeto> consultarProjetoPeloDataCriacao(LocalDate dataInicio){
    return projetoRepository.findByDataInicio(dataInicio);
  }

  public  Optional<Projeto> consultarProjetoPeloId(Long id){
    return projetoRepository.findById(id);
  }

}
