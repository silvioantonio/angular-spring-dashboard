package com.silvio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silvio.model.ItemList;

public interface ItemListRepository extends JpaRepository<ItemList, Long>{
	Optional<ItemList> findByNome(String nome);
}
