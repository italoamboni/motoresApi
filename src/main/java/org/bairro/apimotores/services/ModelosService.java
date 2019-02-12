package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.apimotores.entidades.Modelos;

@Stateless
public class ModelosService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private MotoresService motoresService;
	
	public Modelos getPorId(Integer idModelo) throws Exception {
		Modelos entity = manager.find(Modelos.class, idModelo);
		if (entity == null) {
			throw new Exception("Modelo de motor não encontrado!!");
		}
		return entity;
	}

	public List<Modelos> getTodos() {
		return manager.createQuery("select m from Modelos m", Modelos.class).getResultList();
	}

	public List<Modelos> getPorNome(String nomeModelo) throws Exception {
		List<Modelos> listaPorNome = manager
				.createQuery("select m from Modelos m where UPPER(m.modeloCarcaca) LIKE :modeloCarcaca ", Modelos.class)
				.setParameter("modeloCarcaca", "%" + nomeModelo.toUpperCase() + "%").getResultList();
		return listaPorNome;
	}

	public void deletarModelo(Integer idModelo) throws Exception {	
		if (!motoresService.getPorIdModelo(idModelo).isEmpty()) {
			throw new Exception("Esse modelo não pode ser excluído pois existe(m) registro(s) de outro motor(es)!!");
		}		
		Modelos entity = getPorId(idModelo);
		manager.remove(entity);
	}
	
	public Modelos salvarModelo(Modelos entity) throws Exception {
		validarModelo(entity);
		manager.persist(entity);
		return entity;
	}
	
	public Modelos atualizarModelo(Modelos entity) throws Exception {
		validarModelo(entity);
		manager.merge(entity);
		return entity;
	}

	private void validarModelo(Modelos entity) throws Exception {
		if (entity.getModeloCarcaca() == null || entity.getModeloCarcaca().isEmpty()) {
			throw new Exception("O campo modelo de carcaça é obrigatório informar!!");
		}
	}

}
