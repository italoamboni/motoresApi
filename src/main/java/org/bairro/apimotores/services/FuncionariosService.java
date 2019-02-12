package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bairro.apimotores.entidades.Funcionarios;

@Stateless
public class FuncionariosService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	public Funcionarios getPorId(Integer id) throws Exception {
		Funcionarios entity = manager.find(Funcionarios.class, id);
		if (entity == null) {
			throw new Exception("Nenhum funcionário foi encontrado com este código!!");
		}
		return entity;
	}

	public List<Funcionarios> getTodos() throws Exception {
		List<Funcionarios> listaDeFuncionarios = manager.createQuery("select f from Funcionarios f", Funcionarios.class)
				.getResultList();
		return listaDeFuncionarios;
	}

	public Funcionarios salvarFuncionario(Funcionarios entity) throws Exception {
		validarFuncionario(entity);
		manager.persist(entity);
		return entity;
	}

	public Funcionarios atualizarFuncionario(Funcionarios entity) throws Exception {
		validarFuncionario(entity);
		manager.merge(entity);
		return entity;
	}

	public void validarFuncionario(Funcionarios entity) throws Exception {
		if (entity.getNome() == null || entity.getNome().isEmpty()) {
			throw new Exception("Nome do funcionário é obrigatório informar!!");
		}
		if (entity.getTelefone() == null || entity.getTelefone().isEmpty()) {
			throw new Exception("Número do telefone é obrigatório informar!!");
		}
		if (entity.getCargo() == null || entity.getCargo().isEmpty()) {
			throw new Exception("O cargo do funcionário é obrigatório informar!!");
		}

	}

}
