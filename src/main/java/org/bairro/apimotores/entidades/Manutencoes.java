package org.bairro.apimotores.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "manutencao")
@SequenceGenerator(name= "MANUTENCAO_SEQ", sequenceName= "MANUTENCAO_SEQ", allocationSize= 1)
public class Manutencoes {
	
	@Id
	@Column(name= "id_manutencao")
	@GeneratedValue(generator= "MANUTENCAO_SEQ", strategy= GenerationType.SEQUENCE)
	private Integer idManutencao;
	
	@Column(nullable= false, length= 300)
	private String descricao;
	
	@Column(nullable= false, length= 50)
	private String local;
	
	@Column(name= "DT_ENTRADA", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrada;
	
	@Column(name= "DT_RETORNO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRetorno;
	
	@Column(length= 20)
	private double valor;

	@Column(length= 200)
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name= "id_funcionario")
	private Funcionarios funcionario;
	
	@ManyToOne
	@JoinColumn(name="id_motor")
	private Motores motor;

	
	public Integer getIdManutencao() {
		return idManutencao;
	}

	public void setIdManutencao(Integer idManutencao) {
		this.idManutencao = idManutencao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataRetorno() {
		return dataRetorno;
	}

	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Funcionarios getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionarios funcionario) {
		this.funcionario = funcionario;
	}

	public Motores getMotor() {
		return motor;
	}

	public void setMotor(Motores motor) {
		this.motor = motor;
	}
	
	

	
}
