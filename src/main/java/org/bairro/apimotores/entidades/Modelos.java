package org.bairro.apimotores.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "modelo")
@SequenceGenerator(name= "MODELO_SEQ", sequenceName= "MODELO_SEQ", allocationSize = 1)
public class Modelos {
	
	@Id
	@Column(name= "id_modelo")
	@GeneratedValue(generator= "MODELO_SEQ", strategy= GenerationType.SEQUENCE)
	private Integer idModelo;
	
	@Column(nullable= false, length = 50)
	private String modeloCarcaca;

	
	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getModeloCarcaca() {
		return modeloCarcaca;
	}

	public void setModeloCarcaca(String modeloCarcaca) {
		this.modeloCarcaca = modeloCarcaca;
	}



}
