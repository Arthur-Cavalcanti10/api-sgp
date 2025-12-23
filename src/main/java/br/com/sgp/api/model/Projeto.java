package br.com.sgp.api.model;

import java.time.LocalDate;

import br.com.sgp.api.enums.ProjetoStatus;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Projetos") //como toda tabela e uma entidade, ao fazer isso eu digo q isso e uma entidade ou seja, uma tabela

public class Projeto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank
@Column(nullable= false)
private String nome;

@Column(columnDefinition = "TEXT")
private String descricao;

@NotNull
@Column(name = "data_inicio", nullable = false)
private LocalDate dataInicio;

@NotNull
@Column(name = "data_conclusao", nullable = false)
private LocalDate dataConclusao;

@NotNull
@Column(nullable = false)
@Enumerated(value = EnumType.STRING)
private ProjetoStatus status;

@ManyToOne //varios projetos para um usuario || aqui ele mapeia qual a chave principal de usuario
@JoinColumn(nullable = false) //como é uma relação nao usamos o column, mas sim o JoinColumn
private Usuario responsavel; 

}
