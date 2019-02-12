package org.bairro.apimotores.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "marca")
@SequenceGenerator(name= "MARCA_SEQ", sequenceName= "MARCA_SEQ", allocationSize = 1)
public class Marcas {
	
	@Id
	@Column(name= "id_marca")
	@GeneratedValue(generator = "MARCA_SEQ", strategy= GenerationType.SEQUENCE)
	private Integer idMarca;
	
	@Column(nullable= false, length= 50)
	private String nome;
	
	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
