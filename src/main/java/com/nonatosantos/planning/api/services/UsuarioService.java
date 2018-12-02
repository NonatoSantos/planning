package com.nonatosantos.planning.api.services;

import java.util.Optional;

import com.nonatosantos.planning.api.model.Usuario;

public interface UsuarioService  {
	
	Usuario salvar(Usuario funcionario);

	Optional<Usuario> buscarPorEmail(String email);

	Optional<Usuario> buscarPorCpf(String cpf);

	Optional<Usuario> buscarPorId(Long id);
	
	void remover(Long id);
	
	



}
