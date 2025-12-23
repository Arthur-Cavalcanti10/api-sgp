package br.com.sgp.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sgp.api.model.Usuario;

@Repository                                              //nome da classe e tipo da chave primaria
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {  //aqui criamos metodos abstratos especificos para atributos para usarmos no service

  Optional<Usuario> findByCpf (String cpf); //achar o usuario pelo cpf 

  Optional<Usuario> findByEmail(String email); //achar o usuario pelo email

  Optional<Usuario> findById (Long id);
  
}
