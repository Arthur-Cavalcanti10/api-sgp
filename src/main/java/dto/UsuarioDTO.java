package dto;

import java.time.LocalDate; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO { // DATA TRANSFER OBJECT, AQUI NOS PODEMOS MUDAR O QUE QUEREMOS MOSTRAR, POR EXEMPLO QUANDO EU DER UM PRINT EM UM USUARIO, DESEJO MOSTRAR TAMBEM A IDADE, MAS ELA NAO É UM ATRIBUTO DE USUARIO
  private Long id;
  private String nome;
  private String email;
  private String cpf;
  private LocalDate dataNascimento;
  private Integer idade;
  private String status;
}
