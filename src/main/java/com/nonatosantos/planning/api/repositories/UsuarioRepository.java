package com.nonatosantos.planning.api.repositories;

import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nonatosantos.planning.api.model.Usuario;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "UsuarioRepository.findByUsuarioId", 
				query = "SELECT user FROM Usuario user WHERE user.usuario.id = :usuarioId") })
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByEmail(String email);
	Usuario findByNome(String nome);
	Usuario findByCpf(String cpf);
	Optional<Usuario> findById(Long id);

}
