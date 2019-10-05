package com.silvio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByNome(String nome);
}
