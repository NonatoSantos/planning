package com.nonatosantos.planning.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Meta implements Serializable {

	private static final long serialVersionUID = -634516790505899130L;

	private Long id;
	private BigDecimal valorMinimo;
	private BigDecimal valorIdeal;
	private Date dataInicial;
	private Date dataFinal;
	private Usuario funcionario;

	public Meta() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorIdeal() {
		return valorIdeal;
	}

	public void setValorIdeal(BigDecimal valorIdeal) {
		this.valorIdeal = valorIdeal;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Usuario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Usuario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Meta [id=" + id + ", valorMinimo=" + valorMinimo + ", valorIdeal=" + valorIdeal + ", dataInicial="
				+ dataInicial + ", dataFinal=" + dataFinal + ", funcionario=" + funcionario + "]";
	}

}
