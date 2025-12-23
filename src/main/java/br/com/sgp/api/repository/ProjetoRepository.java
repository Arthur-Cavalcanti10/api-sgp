package br.com.sgp.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.api.enums.ProjetoStatus;
import br.com.sgp.api.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
  List<Projeto> findByStatus(ProjetoStatus status);
  Optional<Projeto> findByDataConclusao (LocalDate dataConclusao);
  Optional<Projeto> findByDataInicio (LocalDate dataInicio);
}
