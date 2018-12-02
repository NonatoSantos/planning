package com.nonatosantos.planning.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

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

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController() {

	}

	@PostMapping
	public ResponseEntity<Response<UsuarioDto>> cadastrar(@Valid @RequestBody UsuarioDto usuarioDto,
			BindingResult result) throws NoSuchAlgorithmException {
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		validarUsuario(usuarioDto, result);
		Usuario usuario = this.converterDtoUsuario(usuarioDto, result);

		if (result.hasErrors()) {

			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.salvar(usuario);

		response.setData(this.converterCadastroPFDto(usuario));
		return ResponseEntity.ok(response);

	}

	@GetMapping(value = "/usuarios/{id}")
	public ResponseEntity<Response<UsuarioDto>> buscaPorCnpj(@PathVariable("id") Long id) {

		Response<UsuarioDto> response = new Response<UsuarioDto>();
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);
		if (!usuario.isPresent()) {

			response.getErrors().add("Usuario não encontrado para o ID " + id);
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

	private UsuarioDto converterCadastroPFDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setSenha(usuario.getSenha());
		usuarioDto.setCpf(usuario.getCpf());
		usuarioDto.setPerfil(usuario.getPerfil());
		usuarioDto.setCargo(usuario.getCargo());

		return usuarioDto;

	}
}
