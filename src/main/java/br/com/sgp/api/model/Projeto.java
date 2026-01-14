package br.com.sgp.api.model;

import java.time.LocalDate;
import java.util.List;

import br.com.sgp.api.enums.ProjetoStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

@NotBlank(message = "o campo nome é obrigatório.")
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

 @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

}
