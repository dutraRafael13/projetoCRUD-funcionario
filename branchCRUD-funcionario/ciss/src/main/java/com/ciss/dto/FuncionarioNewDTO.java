package com.ciss.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ciss.services.validation.FuncionarioInsert;

@FuncionarioInsert
public class FuncionarioNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 30, message = "O tamanho deve ser entre 2 e 30 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 50, message = "O tamanho deve ser entre 2 e 50 caracteres")
	private String sobrenome;
	
	@Email(message = "E-mail inválido")
	private String email;
	
	@Digits(fraction = 0, integer = 11, message = "Número PIS inválido")
	private Long numeroPIS;
	
	public FuncionarioNewDTO() {
		this.id = 0;
		this.nome = "";
		this.sobrenome = "";
		this.email = "";
		this.numeroPIS = 0L;
	}
	
	public FuncionarioNewDTO(Integer id, String nome, String sobrenome, String email, Long numeroPIS) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.numeroPIS = numeroPIS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumeroPIS() {
		return numeroPIS;
	}

	public void setNumeroPIS(Long numeroPIS) {
		this.numeroPIS = numeroPIS;
	}
	
}