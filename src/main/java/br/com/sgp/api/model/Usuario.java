package br.com.sgp.api.model;

import java.time.LocalDate;
import java.util.List;

import br.com.sgp.api.enums.UsuarioStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //faz todos os getter and setters 
@NoArgsConstructor //faz os construtores sem argumento
@AllArgsConstructor //faz o construtor com argumentos 
@Entity(name="Usuarios") //como toda tabela e uma entidade, ao fazer isso eu digo q isso e uma entidade ou seja, uma tabela
public class Usuario {
  
  @Id  //CHAVE PRIMARIA
  @GeneratedValue(strategy =GenerationType.IDENTITY) //estrategia de autogeração
  private Long id;
  
  @NotBlank (message = "O campo 'nome' é obrigatório!")
  @Column(nullable = false) //tornando a coluna obrigatoria 
  private String nome;
  
  @NotBlank (message = "o campo 'cpf' é obrigatório e deve ser único")
  @Column(nullable = false, unique = true) //tornando obrigatorio e único 
  private String cpf;
  
  @NotBlank(message = "o campo 'email' é obrigatório e deve ser único") @Email
  @Column(nullable = false, unique = true) //tornando obrigatorio e único 
  private String email;
  
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{1,19}$")
  @NotBlank (message = "o campo 'senha' é obrigatório e deve conter no máximo 19 caracteres, uma letra maiúscula e um caracter especial")@Size(max = 19)
  @Column(nullable = false, length = 19) //tamanho da senha 
  private String senha;
   
  @NotNull
  @Column(nullable = false)
  private LocalDate dataNascimento;
  
  @NotNull
  @Column(nullable = false)
  @Enumerated(value = EnumType.STRING) //os valores da lista enum sao string 
  private UsuarioStatus status;

 @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

}
