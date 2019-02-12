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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bairro.apimotores.entidades.Modelos;
import org.bairro.apimotores.services.ModelosService;
import org.bairro.apimotores.utils.MensagemResposta;

@Path("/modelos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModelosResources {

	@Inject
	ModelosService service;

	@GET
	public Response getTodos() {
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
	@Path("/buscapornome")
	public Response getByLikes(@QueryParam("nome") String nomeModelo) {

		try {

			if (nomeModelo != null && !nomeModelo.isEmpty()) {
				return Response.ok(service.getPorNome(nomeModelo)).build();
			}

			throw new Exception("O parâmetro 'nome' não foi informado para consulta!");

		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	public Response salvarModelo(Modelos entity) {
		try {
			return Response.ok(service.salvarModelo(entity)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	public Response atualizarModelo(Modelos entity) {
		try {
			return Response.ok(service.atualizarModelo(entity)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		try {
			service.deletarModelo(id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

}
