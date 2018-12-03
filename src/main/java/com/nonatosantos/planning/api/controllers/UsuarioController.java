package com.nonatosantos.planning.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nonatosantos.planning.api.dtos.UsuarioDto;
import com.nonatosantos.planning.api.model.Usuario;
import com.nonatosantos.planning.api.response.Response;
import com.nonatosantos.planning.api.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private static Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController() {

	}

	@PostMapping()
	public ResponseEntity<Response<UsuarioDto>> cadastrarUsuario(@Valid @RequestBody UsuarioDto usuarioDto,
			BindingResult result) throws NoSuchAlgorithmException {
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		validarUsuario(usuarioDto, result);
		Usuario usuario = this.converterDtoUsuario(usuarioDto, result);

		if (result.hasErrors()) {

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.salvar(usuario);

		response.setData(this.converterUsuarioDto(usuario));
		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UsuarioDto>> buscaPorId(@PathVariable("id") Long id) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);
		if (!usuario.isPresent()) {
			log.info("Usuario não encontrado para o ID: {}", id);
			response.getErrors().add("Usuario não encontrado para o ID " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterUsuarioDto(usuario.get()));
		return ResponseEntity.ok(response);

	}
	
	@GetMapping(value = "/usuario/{email}")
	public ResponseEntity<Response<UsuarioDto>> buscaPorNome(@PathVariable("id") String email) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(email);
		if (!usuario.isPresent()) {

			response.getErrors().add("Usuario não encontrado para o Email " + email);
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);

	}

	private void validarUsuario(UsuarioDto usuarioDto, BindingResult result) {
		this.usuarioService.buscarPorCpf(usuarioDto.getCpf())
				.ifPresent(func -> result.addError(new ObjectError("usuario", "CPF já existe")));

		this.usuarioService.buscarPorEmail(usuarioDto.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("usuario", "Email já existe")));

	}

	private Usuario converterDtoUsuario(UsuarioDto usuarioDto, BindingResult result) throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setPerfil(usuarioDto.getPerfil());
		usuario.setSenha(usuarioDto.getSenha());
		usuario.setCargo(usuarioDto.getCargo());

		return usuario;
	}

	private UsuarioDto converterUsuarioDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(Optional.of(usuario.getId()));
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setSenha(usuario.getSenha());
		usuarioDto.setCpf(usuario.getCpf());
		usuarioDto.setPerfil(usuario.getPerfil());
		usuarioDto.setCargo(usuario.getCargo());

		return usuarioDto;

	}
}
