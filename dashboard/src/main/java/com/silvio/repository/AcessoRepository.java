package com.silvio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvio.model.Acesso;

public interface AcessoRepository extends JpaRepository<Acesso, Long>{
	Optional<Acesso> findByLogin(String login);
}
