package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.apimotores.entidades.Marcas;

@Stateless
public class MarcasService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private MotoresService motoresService;

	public Marcas getPorId(Integer id) throws Exception {
		Marcas entity = manager.find(Marcas.class, id);
		if (entity == null) {
			throw new Exception("Nenhuma marca encontrada com esse código!!");
		}
		return entity;
	}

	public List<Marcas> getTodas() throws Exception {
		List<Marcas> listaDeMarcas = manager.createQuery("select m from Marcas m", Marcas.class).getResultList();
		return listaDeMarcas;
	}

	public void deletarMarca(Integer idMarca) throws Exception {
		if (!motoresService.getPorIdMarca(idMarca).isEmpty()) {
			throw new Exception("A marca não pode ser excluída pois existe(m) em outro(s) motor(es)!!");
		}
		Marcas entity = getPorId(idMarca);
		manager.remove(entity);
	}

	public Marcas inserirMarca(Marcas entity) throws Exception {
		validarMarca(entity);
		manager.persist(entity);
		return entity;
	}

	public Marcas atualizarMarca(Marcas entity) throws Exception {
		validarMarca(entity);
		manager.merge(entity);
		return entity;
	}

	public void validarMarca(Marcas entity) throws Exception {
		if (entity.getNome() == null || entity.getNome().isEmpty()) {
			throw new Exception("O campo nome da marca é obrigatório informar!!");
		}
	}

}
