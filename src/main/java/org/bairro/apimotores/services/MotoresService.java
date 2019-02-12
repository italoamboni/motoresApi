package org.bairro.apimotores.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bairro.apimotores.entidades.Historicos;
import org.bairro.apimotores.entidades.Manutencoes;
import org.bairro.apimotores.entidades.Motores;

@Stateless
public class MotoresService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private HistoricosService historicoService;

	@Inject
	private ManutencoesService manutencaoService;

	public Motores getPorId(Integer id) throws Exception {
		Motores entity = manager.find(Motores.class, id);
		if (entity == null) {
			throw new Exception("Nenhum motor foi encontrado com este código!!");
		}
		return entity;
	}

	public List<Motores> getTodos() throws Exception {
		List<Motores> listaDeMotores = manager.createQuery("select m from Motores m", Motores.class).getResultList();
		return listaDeMotores;
	}

	public List<Motores> getPorIdMarca(Integer idMarca) {
		TypedQuery<Motores> query = manager.createQuery("select m from Motores m where m.marca.idMarca = :idMarca",
				Motores.class);
		query.setParameter("idMarca", idMarca);
		return query.getResultList();
	}

	public List<Motores> getPorIdModelo(Integer idModelo) throws Exception {
		List<Motores> listaPorModelo = manager
				.createQuery("select m from Motores m where m.modelo.idModelo = :idModelo", Motores.class)
				.setParameter("idModelo", idModelo).getResultList();
		return listaPorModelo;
	}

	public List<Motores> getPorPotencia(String potencia) throws Exception {
		List<Motores> listaPorPotencia = manager
				.createQuery("select m from Motores m where UPPER(m.potencia) LIKE :potencia ", Motores.class)
				.setParameter("potencia", "%" + potencia.toUpperCase() + "%").getResultList();
		if (listaPorPotencia == null || listaPorPotencia.isEmpty()) {
			throw new Exception("Nenhum motor foi encontrado com essa potência!!");
		}
		return listaPorPotencia;
	}

	public void deletarMotor(Integer idMotor) throws Exception {
		Motores entity = getPorId(idMotor);

		List<Historicos> listaHistoricos = historicoService.getPorIdMotor(idMotor);
		if (listaHistoricos != null && !listaHistoricos.isEmpty()) {
			throw new Exception("O motor não pode ser deletado pois existe(m) registro(s) de histórico(s)!!");
		}

		List<Manutencoes> listaManutencoes = manutencaoService.getPorIdMotor(idMotor);
		if (listaManutencoes != null && !listaManutencoes.isEmpty()) {
			throw new Exception("O motor não pode ser deletado pois existe(m) registro(s) dele em manutenção(ões)!!");
		}
		manager.remove(entity);
	}

	public Motores salvarMotor(Motores entity) throws Exception {
		validarMotor(entity);
		manager.persist(entity);
		return entity;
	}

	public Motores atualizarMotor(Motores entity) throws Exception {
		validarMotor(entity);
		manager.merge(entity);
		return entity;
	}

	private void validarMotor(Motores entity) throws Exception {
		if (entity.getPotencia() == null || entity.getPotencia().isEmpty()) {
			throw new Exception("Potência do motor deve ser informada!!");
		}
		if (entity.getTensao() == null || entity.getTensao().isEmpty()) {
			throw new Exception("Tensão do motor deve ser informada!!");
		}

		if (entity.getRpm() == null || entity.getRpm().isEmpty()) {
			throw new Exception("Rotação do motor deve ser informada!!");
		}

		if (entity.getCorrente() == null || entity.getCorrente().isEmpty()) {
			throw new Exception("Corrente nominal do motor deve ser informada!!");
		}
		if (entity.getLocalizacao() == null || entity.getLocalizacao().isEmpty()) {
			throw new Exception("A localização de aonde está esse motor deve ser informada!");
		}
	}
}
