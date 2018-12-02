package com.nonatosantos.planning.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nonatosantos.planning.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
	Usuario findByNome(String nome);
	Usuario findByCpf(String cpf);
	Optional<Usuario> findById(Long id);

}
