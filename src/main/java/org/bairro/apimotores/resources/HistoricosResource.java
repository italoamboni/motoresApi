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

import org.bairro.apimotores.entidades.Historicos;
import org.bairro.apimotores.services.HistoricosService;
import org.bairro.apimotores.utils.MensagemResposta;

@Path("/historicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricosResource {
	
	@Inject
	HistoricosService service;
	
	@GET
	public Response getTodos()  {
		try {
			return Response.ok(service.getTodos()).build();
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
	public Response getPorIdFuncionario(@PathParam("id") Integer idFuncionario) {
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
	public Response salvarHistorico(Historicos entity) {
		try {
			return Response.ok(service.salvarHistorico(entity)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	public Response atualizarHistorico(Historicos entity) {
		try {
			return Response.ok(service.atualizarHistorico(entity)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarHistorico(@PathParam("id") Integer idHistorico) {
		try {
			service.deletarHistorico(idHistorico);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	

}
