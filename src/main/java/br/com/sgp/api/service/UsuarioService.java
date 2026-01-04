package br.com.sgp.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sgp.api.enums.UsuarioStatus;
import br.com.sgp.api.model.Usuario;
import br.com.sgp.api.repository.UsuarioRepository;
import dto.UsuarioDTO;

@Service
public class UsuarioService {  //aqui implementaremos os metodos que usaremos no controller
   
  @Autowired
   private UsuarioRepository usuarioRepository;
   //created e update
   public Usuario salvarUsuario(Usuario usuario){
    return usuarioRepository.save(usuario);
   }
   
   // select * from usuarios 
   public List<Usuario> consultarUsuario(){
    return usuarioRepository.findAll();
   }
   

   //select * from usuarios where id = ?
   public Optional<Usuario> consultUsuario(Long id){ // options apenas faz tratamento de erro
    return usuarioRepository.findById(id);
   }
   
   //delete
   public void deletarUsuario(Long id){
    usuarioRepository.deleteById(id);
   }

   //metodos criados no repository
   //select * from usuarios where email =?
   public UsuarioDTO buscarUsuarioPeloEmail(String email){
           Optional<Usuario> usuarioExistente=usuarioRepository.findByEmail(email);
      
    if (usuarioExistente.isPresent()){



      Usuario usuario = usuarioExistente.get();

      LocalDate dataNascimento = usuario.getDataNascimento();
      LocalDate dataAtual= LocalDate.now();

      Period periodo = Period.between(dataNascimento, dataAtual);
      Integer idade = periodo.getYears();

      String cpfCadastrado=usuario.getCpf();
      String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);
      String cpfFormatado = tresPrimeirosIndices + ".***.***-**";


      UsuarioStatus status = usuario.getStatus();
      String statusString = status.toString();
      String primeiroCaracter= statusString.substring(0, 1).toUpperCase();
      String demaisCaracteres = statusString.substring(1).toLowerCase();
      String statusFormatado = primeiroCaracter+demaisCaracteres; //Ativo, Inativo

      UsuarioDTO usuarioDTO = new UsuarioDTO();
       
      usuarioDTO.setId(usuario.getId());
      usuarioDTO.setNome(usuario.getNome());
      usuarioDTO.setEmail(usuario.getEmail());
      usuarioDTO.setDataNascimento(dataNascimento);
      usuarioDTO.setIdade(idade);
      usuarioDTO.setCpf(cpfFormatado);
      usuarioDTO.setStatus(statusFormatado);

      return usuarioDTO;
    }

    return null;
   }
   //select * from usuario where email =?
   public UsuarioDTO buscarUsuarioPeloCpf(String cpf){
       Optional<Usuario> usuarioExistente=usuarioRepository.findByCpf(cpf);
      
    if (usuarioExistente.isPresent()){



      Usuario usuario = usuarioExistente.get();

      LocalDate dataNascimento = usuario.getDataNascimento();
      LocalDate dataAtual= LocalDate.now();

      Period periodo = Period.between(dataNascimento, dataAtual);
      Integer idade = periodo.getYears();

      String cpfCadastrado=usuario.getCpf();
      String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);
      String cpfFormatado = tresPrimeirosIndices + ".***.***-**";


      UsuarioStatus status = usuario.getStatus();
      String statusString = status.toString();
      String primeiroCaracter= statusString.substring(0, 1).toUpperCase();
      String demaisCaracteres = statusString.substring(1).toLowerCase();
      String statusFormatado = primeiroCaracter+demaisCaracteres; //Ativo, Inativo

      UsuarioDTO usuarioDTO = new UsuarioDTO();
       
      usuarioDTO.setId(usuario.getId());
      usuarioDTO.setNome(usuario.getNome());
      usuarioDTO.setEmail(usuario.getEmail());
      usuarioDTO.setDataNascimento(dataNascimento);
      usuarioDTO.setIdade(idade);
      usuarioDTO.setCpf(cpfFormatado);
      usuarioDTO.setStatus(statusFormatado);

      return usuarioDTO;
    }

    return null;
   }

   public UsuarioDTO consultarUsuarioPeloId(Long id){
    Optional<Usuario> usuarioExistente=usuarioRepository.findById(id);
      
    if (usuarioExistente.isPresent()){



      Usuario usuario = usuarioExistente.get(); //cria uma variavel usuario, e pega os valores do usuario existente

      LocalDate dataNascimento = usuario.getDataNascimento(); // pega a data de nascimento do usuario 
      LocalDate dataAtual= LocalDate.now(); // pega a data atual, para fazer a lógica da idade 

      Period periodo = Period.between(dataNascimento, dataAtual); // pega a idade do usuario
      Integer idade = periodo.getYears();//transforma a idade do usuario em anos

      String cpfCadastrado=usuario.getCpf(); //pega o cpf do usuario
      String tresPrimeirosIndices = cpfCadastrado.substring(0, 3);// pega os 3 primeiros digitos
      String cpfFormatado =  tresPrimeirosIndices + ".***.***-**"; //formatação para como vai aparecer 


      UsuarioStatus status = usuario.getStatus(); //pega o status do usuario
      String statusString = status.toString(); //transforma o status em uma string
      String primeiroCaracter= statusString.substring(0, 1).toUpperCase(); //pega o primeiro caracter do Status e coloca maiusculo
      String demaisCaracteres = statusString.substring(1).toLowerCase(); //pega o resto e coloca minusculo
      String statusFormatado = primeiroCaracter+demaisCaracteres; //Ativo, Inativo

      UsuarioDTO usuarioDTO = new UsuarioDTO(); // cria uma variavel usuario dto
       
      //aqui nos so atribuimos os valores calculados acima e colocamos eles nos atributos do usuarioDTO
      usuarioDTO.setId(usuario.getId());
      usuarioDTO.setNome(usuario.getNome());
      usuarioDTO.setEmail(usuario.getEmail());
      usuarioDTO.setDataNascimento(dataNascimento);
      usuarioDTO.setIdade(idade);
      usuarioDTO.setCpf(cpfFormatado);
      usuarioDTO.setStatus(statusFormatado);

      return usuarioDTO; // agora é so retornar os atributos do usuarioDTO na formatação que criamos, e pronto.
    }

    return null;
   }

}
