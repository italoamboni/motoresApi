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
@Table(name= "historico")
@SequenceGenerator(name= "HISTORICO_SEQ", sequenceName= "HISTORICO_SEQ", allocationSize= 1)
public class Historicos {
	
	@Id
	@Column(name= "id_historico")
	@GeneratedValue(generator= "HISTORICO_SEQ", strategy= GenerationType.SEQUENCE)
	private Integer idHistorico;
	
	@Column(nullable= false, length= 400)
	private String descricao;
	
	@Column(name= "dt_fato", nullable= false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFato;
	
	@ManyToOne
	@JoinColumn(name= "id_funcionario")
	private Funcionarios funcionario;	
	
	@ManyToOne
	@JoinColumn(name= "id_motor")
	private Motores motor;

	public Integer getIdHistorico() {
		return idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataFato() {
		return dataFato;
	}
	public void setDataFato(Date dataFato) {
		this.dataFato = dataFato;
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
