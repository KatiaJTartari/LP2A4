package webapi.LP2Aula09_ControleBancarioWebAPI;

import java.util.Collection;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.ContaComum;
import modelo.PessoaFisica;
import modelo.repositorio.PessoaFisicaRepositorio;
import modelo.repositorio.Repositorio;

@Path("pessoafisica")
public class PessoaFisicaResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<PessoaFisica> getAll()
	{
		PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		Collection<PessoaFisica> pessoasFisicas = repositorio.recuperarPessoasFisicas();
		
		return pessoasFisicas;
	}
	
	
	@GET
	@Path("{id}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id)
	{
		try
		{
        PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		PessoaFisica p = repositorio.recuperarPessoaFisicaPorId(id);
		
		if(p != null)
			return Response.ok(p, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(PessoaFisica p) 
	{
		try
		{
		
		   Repositorio<PessoaFisica> repositorio = new Repositorio<PessoaFisica>();
		
		   repositorio.criar(p);
		
		   return Response.ok(p, MediaType.APPLICATION_JSON).build();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}") // URL/pessoafisica/{id}
	public Response update(@PathParam("id") int id, PessoaFisica p) 
	{
		try
		{
		PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		PessoaFisica entidade = repositorio.recuperarPessoaFisicaPorId(id); 
		
		entidade.setCep(p.getCep());
		entidade.setCpf(p.getCpf());
		entidade.setEmail(p.getEmail());
		entidade.setEndereco(p.getEndereco());
		entidade.setNascto(p.getNascto());
		entidade.setNome(p.getNome());
		entidade.setRenda(p.getRenda());
		entidade.setSenha(p.getSenha());
		entidade.setSituacao(p.getSituacao());
		entidade.setTelefone(p.getTelefone());
		
		entidade.getContas().clear();
		
		for (ContaComum conta : p.getContas()) 
		{
			entidade.getContas().add(conta);
		}
		
		if(repositorio.atualizar(entidade))
			return Response.ok(entidade, MediaType.APPLICATION_JSON).build();
		else
			return Response.notModified().build();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
	
	
	@DELETE
	@Path("{id}") 
	public Response delete(@PathParam("id") int id)
	{
		try
		{
        PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
        
        PessoaFisica entidade = repositorio.recuperarPessoaFisicaPorId(id); 
        
        if(repositorio.excluir(entidade))
			return Response.ok().build();
		else
			return Response.notModified().build();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			return Response.serverError().build();
		}
	}
}
