package com.nonatosantos.planning.api.services.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nonatosantos.planning.api.model.Usuario;
import com.nonatosantos.planning.api.repositories.UsuarioRepository;
import com.nonatosantos.planning.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository UsuarioRepository;

	@Override
	public Usuario salvar(Usuario usuario) {

		return this.UsuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {

		return Optional.ofNullable(this.UsuarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Usuario> buscarPorCpf(String cpf) {

		return Optional.ofNullable(this.UsuarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Usuario> buscarPorId(Long id) {

		return this.UsuarioRepository.findById(id);
	}

	@Override
	public void remover(Long id) {
		this.UsuarioRepository.deleteById(id);

	}

}
