package controlador.contacomum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ContaComum;
import modelo.PessoaFisica;
import modelo.repositorio.PersistenceConfig;
import modelo.repositorio.PessoaFisicaRepositorio;
import modelo.repositorio.Repositorio;

/**
 * Servlet implementation class CadastrarContaComumServlet
 */
@WebServlet("/contacomum/cadastrar")
public class CadastrarContaComumServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CadastrarContaComumServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		Collection<PessoaFisica> pessoasFisicasAtivas =
				repositorio.recuperarPessoasFisicasPorStatus((byte)1);
		
		if(pessoasFisicasAtivas == null)
		{
			pessoasFisicasAtivas = new ArrayList<PessoaFisica>();
		}
		
		request.setAttribute("pessoasFisicasAtivas", pessoasFisicasAtivas);
		
		request.setAttribute("tituloPagina",
				"Cadastrar Nova Conta Comum Vinculada a Pessoas Físicas");
		
		request.setAttribute("pathView",
				"/WEB-INF/views/contacomum/cadastrar.jsp");
		
		RequestDispatcher rd =
				request.getRequestDispatcher("/WEB-INF/template.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		PessoaFisicaRepositorio repositorioPessoaFisica =
				new PessoaFisicaRepositorio();
		
		// PARTE 1 - Recuperação dos objetos que representam os titulares
		// selecionados pelo cliente.
		
		String[] idsTitularesSelecionados =
				request.getParameterValues("selTitulares");
		
		Collection<PessoaFisica> titulares = new ArrayList<PessoaFisica>();
		
		if(idsTitularesSelecionados != null)
		{
			for(String idTitular : idsTitularesSelecionados)
			{
				int id = 0;
				
				try
				{
					id = Integer.parseInt(idTitular);
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				PessoaFisica pf = 
						repositorioPessoaFisica.recuperarPessoaFisicaPorId(id);
				
				if(pf != null)
					titulares.add(pf);
			}
		}
		
		// PARTE 2 - Instanciar e popular o novo objeto do tipo ContaComum.
		
		ContaComum contaComum = new ContaComum();
		
		if(request.getParameter("selSituacao") != null &&
				! request.getParameter("selSituacao").trim().equals(""))
		{
			contaComum.setSituacao(
					Byte.parseByte(request.getParameter("selSituacao")));
			
			if(contaComum.getSituacao() == 0)
			{
				contaComum.setFechamento(new Date());
			}
			else
			{
				contaComum.setFechamento(null);
			}
		}
		
		if(request.getParameter("pwdSenha") != null &&
				! request.getParameter("pwdSenha").trim().equals(""))
		{
			try
			{
				contaComum.setSenha(
						Integer.parseInt(request.getParameter("pwdSenha").trim()));
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("numSaldo") != null &&
				! request.getParameter("numSaldo").trim().equals(""))
		{
			contaComum.setSaldo(
					Double.parseDouble(
							request.getParameter("numSaldo")
								.trim()
								.replace(',','.')));
		}
		
		// PASSO 3 - Persistir o novo objeto do tipo ContaComum
		
		Repositorio<ContaComum> repositorioContaComum = 
				new Repositorio<ContaComum>();
		
		repositorioContaComum.criar(contaComum);
		
		// Passo 4 - Vincular os titulares
		
		for (PessoaFisica pessoaFisica : titulares)
		{
			pessoaFisica.getContas().add(contaComum);
			
			repositorioPessoaFisica.atualizar(pessoaFisica);
		}
		
		// PASSO 5 - Finalizar
		
		PersistenceConfig.closeEntityManager();
		
		RequestDispatcher rd =
				request.getRequestDispatcher("/contacomum");
		
		rd.forward(request, response);
	}

}

