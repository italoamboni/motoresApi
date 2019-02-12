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

import org.bairro.apimotores.entidades.Funcionarios;
import org.bairro.apimotores.services.FuncionariosService;
import org.bairro.apimotores.utils.MensagemResposta;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionariosResource {
	
	@Inject
	FuncionariosService service;
	
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
	
	@POST
	public Response salvarFuncionario(Funcionarios entity) {
		try {
			return Response.ok(service.salvarFuncionario(entity)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@PUT
	public Response atualizarFuncionario(Funcionarios entity) {
		try {
			return Response.ok(service.atualizarFuncionario(entity)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	public Response deletarFuncionario() {
		return Response.ok(new MensagemResposta("Você não pode deletar nenhum funcionário!!")).status(Status.BAD_REQUEST).build();			
	}
	
}




