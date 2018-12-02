package com.nonatosantos.planning.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.nonatosantos.planning.api.model.enums.PerfilUsuarioEnum;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String rg;
	private String cargo;
	private PerfilUsuarioEnum perfil;

	public UsuarioDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Campo não pode ser vazio")
	@Length(min = 5, max = 200, message = "Nome deve conter entre 5 e 200 carácteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message="Campo não pode ser vazio")
	@Email(message="Email Inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message="Campo Senha deve ser preenchido")
	@Length(min=5, max=10, message="Senha deve conter entre 5 e 10 carácteres")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@NotEmpty(message="Campo CPF deve ser preenchido")
	@CPF(message="CPF Inválido")
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}

}
