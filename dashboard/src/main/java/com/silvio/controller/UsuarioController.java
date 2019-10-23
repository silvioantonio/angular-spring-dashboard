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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.silvio.model.Usuario;
import com.silvio.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public boolean getAcesso(@RequestBody Usuario usuario){
		Optional<Usuario> user = usuarioRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());
		if(!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario nao encontrado ");
		} else {
			return true;
		}
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario getUsuarioId(@Valid @PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById( id);
		
		if(usuario.get() == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID do Usuario não encontrado");
		}
		return usuario.get();
	}
	
	@GetMapping("/nome")
	@ResponseStatus(HttpStatus.OK)
	public Usuario getUsuarioNome(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> opUsuario = usuarioRepository.findByNome(usuario.getNome());
		
		if(opUsuario.isPresent()) {
			return opUsuario.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario com esse nome não existe ");
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionarUsuario(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByNome(usuario.getNome());
		if(usuarioExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Já existe um usuario com esse nome");
		}
		return usuarioRepository.save(usuario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario alterarUsuario(@RequestBody Usuario novoUsuario, @Valid @PathVariable Long id) {
		try {
				return usuarioRepository.findById(id)
						.map(usuario -> {
					    	usuario.setNome(novoUsuario.getNome());
					        return usuarioRepository.save(usuario);
					      }).get();
		}catch(java.util.NoSuchElementException nsee) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe usuario com esse ID");
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deletarUsuarioId(@Valid @PathVariable Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch( IllegalArgumentException ie) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID não deve estar vazio ");
		} catch(EmptyResultDataAccessException er) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID deve estar cadastrado ");
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deletarUsuarioNome(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> opUsuario = usuarioRepository.findByNome(usuario.getNome());
		
		if(opUsuario.isPresent()) {
			 usuarioRepository.delete(opUsuario.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Usuario não existe ");
		}
		
	}
	
	
	
}
