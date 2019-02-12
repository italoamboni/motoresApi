 package org.bairro.apimotores.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "motor")
@SequenceGenerator(name= "MOTOR_SEQ", sequenceName= "MOTOR_SEQ", allocationSize= 1)
public class Motores {
	
	@Id
	@Column(name= "id_motor")
	@GeneratedValue(generator= "MOTOR_SEQ", strategy= GenerationType.SEQUENCE)
	private Integer idMotor;
	
	@Column(nullable= false, length= 10)
	private String potencia;
	
	@Column(nullable= false, length= 20)
	private String tensao;
	
	@Column(nullable= false, length= 20)
	private String rpm;
	
	@Column(nullable= false, length= 20)
	private String corrente;
	
	@Column(nullable= false, length= 100)
	private String localizacao;
	
	@ManyToOne
	@JoinColumn(name= "id_marca")
	private Marcas marca; 
	
	@ManyToOne
	@JoinColumn(name= "id_modelo")
	private Modelos modelo;

	public Integer getIdMotor() {
		return idMotor;
	}

	public void setIdMotor(Integer idMotor) {
		this.idMotor = idMotor;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public String getTensao() {
		return tensao;
	}

	public void setTensao(String tensao) {
		this.tensao = tensao;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getCorrente() {
		return corrente;
	}

	public void setCorrente(String corrente) {
		this.corrente = corrente;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}

	public Modelos getModelo() {
		return modelo;
	}

	public void setModelo(Modelos modelo) {
		this.modelo = modelo;
	}

	
}
