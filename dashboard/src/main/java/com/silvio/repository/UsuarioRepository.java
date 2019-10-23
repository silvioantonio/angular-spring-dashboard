package com.silvio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNome(String nome);
	
	Optional<Usuario> findByLogin(String login);
	
	Optional<Usuario> findByLoginAndSenha(String login, String senha);

}
