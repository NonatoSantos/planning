package com.nonatosantos.planning.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nonatosantos.planning.api.controllers.excepions.UsuarioNotFoundException;
import com.nonatosantos.planning.api.model.Usuario;
import com.nonatosantos.planning.api.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioController() {

	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Usuario usuario) {
		Usuario savedUser = usuarioRepository.save(usuario);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}

	@GetMapping("/users/{id}")
	public Usuario retrieveUser(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent())
			throw new UsuarioNotFoundException("O Seguinte ID informado não existe: " + id);

		return usuario.get();
	}

	@GetMapping(value = "/users")
	public List<Usuario> listarUsuarios() {
		return this.usuarioRepository.findAll();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Usuario user, @PathVariable Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);

		usuarioRepository.save(user);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteStudent(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}

}
