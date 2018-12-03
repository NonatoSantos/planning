package com.nonatosantos.planning.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nonatosantos.planning.api.model.enums.PerfilUsuarioEnum;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 7095337807485036575L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String rg;
	private String cargo;
	private PerfilUsuarioEnum perfil;

	public Usuario() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}

	@Column(nullable = false, unique = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cargo=" + cargo
				+ "]";
	}

}
