package com.silvio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.silvio.model.ItemList;
import com.silvio.repository.ItemListRepository;

@CrossOrigin
@RestController
@RequestMapping("/lista")
public class ItemListController {

	@Autowired
	private ItemListRepository itemListRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ItemList> getItemList(){
		return itemListRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ItemList getItemList(@Valid @PathVariable Long id) {
		Optional<ItemList> itemList = itemListRepository.findById(id);
		if(itemList.get() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do Item não encontrado");
		}
		return itemList.get();
	}
	
	@GetMapping("/nome")
	@ResponseStatus(HttpStatus.OK)
	public ItemList getItemNome(@Valid @RequestBody ItemList itemList) {
		Optional<ItemList> item = itemListRepository.findByNome(itemList.getNome());
		if(item.isPresent()) {
			return item.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Item com esse nome não existe ");
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemList addItem(@RequestBody ItemList itemList) {
		Optional<ItemList> itemExistente = itemListRepository.findByNome(itemList.getClass().getName());
		if(itemExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe um item com esse nome");
		}
		return itemListRepository.save(itemList);
		 
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteItemId(@Valid @PathVariable Long id) {
		try{
			itemListRepository.deleteById(id);
		} catch (IllegalArgumentException i) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " O valor de ID nao pode ser null ", i.getCause());
		} catch (EmptyResultDataAccessException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " O valor do ID deve ser valido ", e.getCause());
		} 
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteItemNome(@Valid @RequestBody ItemList itemList) {
		Optional<ItemList> item = itemListRepository.findByNome(itemList.getNome());
		
		if(item.isPresent())
			itemListRepository.deleteById(item.get().getId());
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, " O valor do nome deve ser valido ");
	}
	
	
}
