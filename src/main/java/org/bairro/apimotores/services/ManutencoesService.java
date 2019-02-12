package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bairro.apimotores.entidades.Historicos;
import org.bairro.apimotores.entidades.Manutencoes;

@Stateless
public class ManutencoesService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private HistoricosService historicoService;

	@Inject
	private MotoresService motorService;
	
	public Manutencoes getPorId(Integer id) throws Exception {
		Manutencoes entity = manager.find(Manutencoes.class, id);
		if (entity == null) {
			throw new Exception("Nenhuma manuntenção foi encontrado com este código!!");
		}
		return entity;
	}

	public List<Manutencoes> getTodas() throws Exception {
		List<Manutencoes> listaDeManutencoes = manager.createQuery("select m from Manutencoes m", Manutencoes.class)
				.getResultList();
		return listaDeManutencoes;
	}

	public List<Manutencoes> getPorIdFuncionario(Integer idFuncionario) {
		TypedQuery<Manutencoes> query = manager.createQuery
				("select m from Manutencoes m where m.funcionario.idFuncionario = :idFuncionario", Manutencoes.class);
				query.setParameter("idFuncionario", idFuncionario);
		 return query.getResultList();
	}

	public List<Manutencoes> getPorIdMotor(Integer idMotor) {
		TypedQuery<Manutencoes> query = manager
				.createQuery("select m from Manutencoes m where m.motor.idMotor = :idMotor", Manutencoes.class);
		   query.setParameter("idMotor", idMotor);
		return query.getResultList();
	}

	public void deletarManutencao(Integer idManutencao) throws Exception {
		Manutencoes entity = getPorId(idManutencao);
		manager.remove(entity);
	}
	
	
	public Manutencoes salvarManutencao(Manutencoes entity) throws Exception {
		validarManutencao(entity);
		
		manager.persist(entity);
		
		Historicos historicoAutomatico = new Historicos();
		historicoAutomatico.setDataFato(entity.getDataEntrada());
		historicoAutomatico.setDescricao("Entrada na manutenção: " + entity.getIdManutencao());
		historicoAutomatico.setMotor(entity.getMotor());
		historicoAutomatico.setFuncionario(entity.getFuncionario());
		historicoService.salvarHistorico(historicoAutomatico);

		return entity;
	}

	public Manutencoes atualizarManutencao(Manutencoes entity) throws Exception {
		validarManutencao(entity);
		manager.merge(entity);
		
		if (entity.getDataRetorno() != null) {
			Historicos historicoAutomatico = new Historicos();
			historicoAutomatico.setDataFato(entity.getDataRetorno());
			historicoAutomatico.setDescricao("Retorno da manutenção: " + entity.getIdManutencao());
			historicoAutomatico.setMotor(entity.getMotor());
			historicoAutomatico.setFuncionario(entity.getFuncionario());
			historicoService.salvarHistorico(historicoAutomatico);
		}
		
		return entity;
	}

	private void validarManutencao(Manutencoes entity) throws Exception {
		if (entity.getDescricao() == null || entity.getDescricao().isEmpty()) {
			throw new Exception("Descrição da manutenção é obrigatório infomar!!");
		}
		if (entity.getLocal() == null || entity.getLocal().isEmpty()) {
			throw new Exception("Local da manutenção tem que ser informado!!");
		}
		if (entity.getMotor() == null) {
			throw new Exception("O motor tem que ser informado!!");
		}
		motorService.getPorId(entity.getMotor().getIdMotor());
		if (entity.getDataEntrada() == null) {
			throw new Exception("Data dessa manutenção tem que ser informada!!");
		}
	}

}
