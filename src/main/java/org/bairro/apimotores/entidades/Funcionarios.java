	package org.bairro.apimotores.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "funcionario")
@SequenceGenerator(name = "FUNCIONARIO_SEQ", sequenceName = "FUNCIONARIO_SEQ", allocationSize = 1)
public class Funcionarios {

	@Id
	@Column(name= "id_funcionario")
	@GeneratedValue(generator= "FUNCIONARIO_SEQ", strategy = GenerationType.SEQUENCE)
	private Integer idFuncionario;

	@Column(nullable= false, length= 75)
	private String nome;

	@Column(nullable= false, length= 12)
	private String telefone;
	
	@Column(nullable= false, length= 50)
	private String cargo;
	
	@Column(length= 80)
	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
