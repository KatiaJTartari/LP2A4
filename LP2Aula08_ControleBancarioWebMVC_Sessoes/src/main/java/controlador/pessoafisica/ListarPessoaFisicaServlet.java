package controlador.pessoafisica;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.PessoaFisica;
import modelo.repositorio.PersistenceConfig;
import modelo.repositorio.PessoaFisicaRepositorio;

/**
 * Servlet implementation class ListarPessoaFisicaServlet
 */
@WebServlet({ "/pessoafisica/listar", "/pessoafisica/todas", "/pessoafisica" })
public class ListarPessoaFisicaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ListarPessoaFisicaServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
        HttpSession session = request.getSession();
		
		if(session.getAttribute("usuarioAutenticado") != null && 
				"OK".equals(session.getAttribute("usuarioAutenticado")))
		{
		PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		Collection<PessoaFisica> pessoasFisicas =
				repositorio.recuperarPessoasFisicas();
		
		PersistenceConfig.closeEntityManager();
		
		request.setAttribute("pessoasFisicas", pessoasFisicas);
		
		request.setAttribute("tituloPagina",
				"Pessoas Físicas Cadastradas");
		
		request.setAttribute("pathView",
				"/WEB-INF/views/pessoafisica/listar.jsp");
		}
		else
		{ 
			request.setAttribute("tituloPagina",
					"Acesso Negado");
			
			request.setAttribute("pathView",
					"/WEB-INF/unauthorized.jsp");
		}
		
		RequestDispatcher rd =
				request.getRequestDispatcher("/WEB-INF/template.jsp");
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
