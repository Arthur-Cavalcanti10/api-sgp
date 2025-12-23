package br.com.sgp.api.model;

import java.time.LocalDate;

import br.com.sgp.api.enums.PrioridadeTarefa;
import br.com.sgp.api.enums.TarefaStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Tarefas")//como toda tabela e uma entidade, ao fazer isso eu digo q isso e uma entidade ou seja, uma tabela

public class Tarefa {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false)
private Long id;

@NotBlank
@Column(nullable = false)
private String titulo;

@Column(columnDefinition = "TEXT")
private String descricao;

@NotNull
@Column(name = "data_criacao", nullable = false)
private LocalDate dataCriacao;

@NotNull
@Column(name = "data_conclusao", nullable = false)
private LocalDate dataConclusao;

@NotBlank
@Column(nullable = false)
@Enumerated(value = EnumType.STRING)
private TarefaStatus status;

@NotNull
@Column(nullable = false)
@Enumerated(value = EnumType.STRING)
private PrioridadeTarefa prioridade;

@ManyToOne //varias tarefas podem ser atribuidas para um projeto
@JoinColumn(nullable = false) //como é uma relação nao usamos o column, mas sim o JoinColumn
private Projeto projeto;

@ManyToOne
public Usuario usuario;
}
