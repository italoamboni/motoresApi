package org.bairro.apimotores.resources;

import java.util.List;

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

import org.bairro.apimotores.entidades.Motores;
import org.bairro.apimotores.services.MotoresService;
import org.bairro.apimotores.utils.MensagemResposta;

@Path("/motores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MotoresResources {

	@Inject
	MotoresService service;

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
	@Path("marca/{id}")
	public Response getPorIdMarca(@PathParam("id") Integer idMarca) {
		try {
			return Response.ok(service.getPorIdMarca(idMarca)).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("modelo/{id}")
	public Response getPoridModelo(@PathParam("id") Integer idModelo) {
		try {
			List<Motores> listaPorModelo = service.getPorIdModelo(idModelo);
			if (listaPorModelo == null || listaPorModelo.isEmpty()) {
				throw new Exception("Nenhum modelo de carcaça encontrada com este nome!!");
			}
			return Response.ok(listaPorModelo).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Path("/buscarpor")
	public Response getPorPontecia(@QueryParam("potencia") String potencia) {
		try {
			if (potencia != null && !potencia.isEmpty()) {
				return Response.ok(service.getPorPotencia(potencia)).build();
			}
			throw new Exception("Os parâmetros 'potência' não foram informados para consulta!!");

		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	public Response salvarMotor(Motores entity) {
		try {
			return Response.ok(service.salvarMotor(entity)).status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}

	@PUT
	public Response atualizarMotor(Motores entity) {
		try {
			return Response.ok(service.atualizarMotor(entity)).status(Status.OK).build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response deletarMotor(@PathParam("id") Integer idMotor) {
		try {
			service.deletarMotor(idMotor);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.ok(new MensagemResposta(e.getMessage())).status(Status.BAD_REQUEST).build();
		}
	}	
}
