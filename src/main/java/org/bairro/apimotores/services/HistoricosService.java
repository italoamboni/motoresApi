package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.apimotores.entidades.Historicos;

@Stateless
public class HistoricosService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Historicos getPorId(Integer id) throws Exception {
		Historicos entity = manager.find(Historicos.class, id);
		if (entity == null) {
			throw new Exception("Nenhum histórico foi encontrado com este código!!");
		}
		return entity;
	}

	public List<Historicos> getTodos() throws Exception {
		List<Historicos> listaDeHistoricos = manager.createQuery("select h from Historicos h", Historicos.class)
				.getResultList();
		return listaDeHistoricos;
	}

	public List<Historicos> getPorIdFuncionario(Integer idFuncionario) throws Exception {
		return manager.createQuery("select h from Historicos h where h.funcionario.idFuncionario = :idFuncionario",
				Historicos.class).setParameter("idFuncionario", idFuncionario).getResultList();
	}

	public List<Historicos> getPorIdMotor(Integer idMotor) throws Exception {
		return manager.createQuery("select h from Historicos h where h.motor.idMotor = :idMotor", Historicos.class)
				.setParameter("idMotor", idMotor).getResultList();
	}

	public void deletarHistorico(Integer idHistorico) throws Exception {
		Historicos entity = getPorId(idHistorico);
		manager.remove(entity);
	}

	public Historicos salvarHistorico(Historicos entity) throws Exception {
		validarHistorico(entity);
		manager.persist(entity);
		return entity;
	}

	public Historicos atualizarHistorico(Historicos entity) throws Exception {
		validarHistorico(entity);
		manager.merge(entity);
		return entity;
	}

	public void validarHistorico(Historicos entity) throws Exception {
		if (entity.getDescricao() == null || entity.getDescricao().isEmpty()) {
			throw new Exception("Uma descrição é obrigatório informar!!");
		}
		if (entity.getDataFato() == null) {
			throw new Exception("A data é obrigatório informar!!");
		}
	}
}
