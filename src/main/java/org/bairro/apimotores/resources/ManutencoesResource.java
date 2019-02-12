package org.bairro.apimotores.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bairro.apimotores.entidades.Manutencoes;
import org.bairro.apimotores.services.ManutencoesService;
import org.bairro.apimotores.utils.MensagemResposta;

@Path("/manutencoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ManutencoesResource {
	
	@Inject
	ManutencoesService service;
	
	@GET
	public Response getTodas()  {
		try {
			return Response.ok(service.getTodas()).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("{id}")
	public Response getPorId(@PathParam("id") Integer id) {
		try {
			return Response.ok(service.getPorId(id)).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("funcionario/{id}")
	public Response getPorIdMarca(@PathParam("id") Integer idFuncionario) {
		try {
			return Response.ok(service.getPorIdFuncionario(idFuncionario)).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Path("motor/{id}")
	public Response getPorIdMotor(@PathParam("id") Integer idMotor) {
		try {
			return Response.ok(service.getPorIdMotor(idMotor)).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	
	@POST
	public Response salvarManutencao(Manutencoes entity) {
		try {
			return Response.ok(service.salvarManutencao(entity)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	public Response atualizarManutencao(Manutencoes entity) {
		try {
			return Response.ok(service.atualizarManutencao(entity)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarManutencao(@PathParam("id") Integer idManutencao) {
		try {
			service.deletarManutencao(idManutencao);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}	
	
}


