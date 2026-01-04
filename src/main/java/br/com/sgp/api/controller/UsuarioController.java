package br.com.sgp.api.controller;

import java.util.List;
import java.util.Objects;


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

import br.com.sgp.api.exception.UsuarioNaoEncontradoEmailException;
import br.com.sgp.api.exception.UsuarioNaoEncontradoException;
import br.com.sgp.api.model.Usuario;
import br.com.sgp.api.service.UsuarioService;
import dto.UsuarioDTO;
import jakarta.validation.Valid;
//endpoint para consultar 
@RestController//aqui vamos criar os endpoints para fazer o crud
@RequestMapping(value = "/usuarios") //http://8080/usuario... || aqui estamos mostrando que o url que todos esses metodos serão usados e o /usuarios

public class UsuarioController {
  @Autowired //isso serve para que o usuarioService herde os metodos do UsuarioService, se não ele conta como null
  private UsuarioService usuarioService;

  //metodos para fazer get nos usuarios 
  @GetMapping(value="/{id}")//{} as chaves indicam que queremos passar um valor||estamos indicando que queremos buscar pelo id || estamos especificando que esse caminho e um get
  public ResponseEntity<UsuarioDTO> buscarUsuarioPeloId(@PathVariable Long id){ //o PathVariable indica que o id q vamos passar eh o id q esta entre chaves e que ele sera extraido do url
  UsuarioDTO usuarioDTO = usuarioService.consultarUsuarioPeloId(id);
    if (Objects.isNull(usuarioDTO)){
      throw new UsuarioNaoEncontradoException(id);
    }  

    return ResponseEntity.ok().body(usuarioService.consultarUsuarioPeloId(id));
  }
  
  @GetMapping //mostrando que isso e um get||pegar todos os usuarios 
  public List<Usuario> listarUsuarios(){
    return usuarioService.consultarUsuario();
  }

@GetMapping(value = "/busca/cpf")
  public ResponseEntity<UsuarioDTO> consultarUsuarioPeloCPF(@RequestParam String cpf){ //o request param dis que o valor do cpf sera injetado do url
       UsuarioDTO usuarioExistente =usuarioService.buscarUsuarioPeloCpf(cpf);
      if(Objects.isNull(usuarioExistente)){
      throw new UsuarioNaoEncontradoException(cpf); 
    }
    
    return ResponseEntity.ok().body(usuarioExistente);//o get serve para pegar o valor dentro do optional e mostrar no corpo

  }

  @GetMapping(value = "/busca/email")
  public ResponseEntity<UsuarioDTO> consultarUsuarioPeloEmail(@RequestParam String email){
       UsuarioDTO usuarioExistente =usuarioService.buscarUsuarioPeloEmail(email);
      if(Objects.isNull(usuarioExistente)){
      throw new UsuarioNaoEncontradoEmailException(email); 
    }
    
    return ResponseEntity.ok().body(usuarioExistente);

  }
   

  //metodo para cadastrar usuarios 
  @PostMapping(value = "/cadastrar")                   //o usuario sera passado no corpo da requisição
  public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario){ 
   return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuario));
          //ResponseEnity serve para podermos alterar o status
  }
  
  //metodo para atualizar usuarios
  @PutMapping(value ="/{id}")                        //aqui passamos as novas inf|| e aqui o id do usuario que iremos mudar 
  public ResponseEntity<Usuario> atualizarUsuarios(@RequestBody Usuario usuario, @PathVariable Long id){
    UsuarioDTO usuarioExistente =usuarioService.consultarUsuarioPeloId(id);

    if(Objects.isNull(usuarioExistente)){//se o usuario for vazio, ele nao existe 
      return ResponseEntity.notFound().build(); //retornaremos o erro de usuario nao encontrado, o build mostra que nao possui corpo, apenas um erro
    }

    usuario.setId(id);//caso o id venha errado ou sem nenhum campo, para garantir que seja apenas uma atualização e nao um cadastramento 

    return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuario));
}

//metodo para deletar usuario
@DeleteMapping(value = "/{id}")
public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){
   UsuarioDTO usuarioExistente =usuarioService.consultarUsuarioPeloId(id);
      if(Objects.isNull(usuarioExistente)){
      return ResponseEntity.notFound().build(); 
    }
    
    usuarioService.deletarUsuario(id);
    return ResponseEntity.noContent().build(); //o build mostra que nao possui corpo, apenas um erro

}
}