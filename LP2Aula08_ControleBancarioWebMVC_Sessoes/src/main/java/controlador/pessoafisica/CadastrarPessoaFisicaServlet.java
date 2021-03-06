package controlador.pessoafisica;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.PessoaFisica;
import modelo.repositorio.PersistenceConfig;
import modelo.repositorio.Repositorio;

/**
 * Servlet implementation class CadastrarPessoaFisicaServlet
 */
@WebServlet("/pessoafisica/cadastrar")
public class CadastrarPessoaFisicaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CadastrarPessoaFisicaServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("tituloPagina",
				"Cadastrar Nova Pessoa F?sica");
		
		request.setAttribute("pathView",
				"/WEB-INF/views/pessoafisica/cadastrar.jsp");
		
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
		PessoaFisica pf = new PessoaFisica();
		
		if(request.getParameter("numCpf") != null &&
				!request.getParameter("numCpf").trim().equals(""))
		{
			pf.setCpf(Long.parseLong(request.getParameter("numCpf")));
		}
		
		if(request.getParameter("txtNome") != null &&
				! request.getParameter("txtNome").trim().equals(""))
		{
			pf.setNome(request.getParameter("txtNome").trim());
		}
		
		if(request.getParameter("datNascto") != null &&
				! request.getParameter("datNascto").trim().equals(""))
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date nascto;
			
			try
			{
				nascto = dateFormat.parse(request.getParameter("datNascto").trim());
				pf.setNascto(nascto);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("txtEndereco") != null &&
				! request.getParameter("txtEndereco").trim().equals(""))
		{
			pf.setEndereco(request.getParameter("txtEndereco").trim());
		}
		
		if(request.getParameter("numCep") != null &&
				! request.getParameter("numCep").trim().equals(""))
		{
			pf.setCep(Long.parseLong(request.getParameter("numCep").trim()));
		}
		
		if(request.getParameter("txtTelefone") != null &&
				! request.getParameter("txtTelefone").trim().equals(""))
		{
			pf.setTelefone(request.getParameter("txtTelefone").trim());
		}
		
		if(request.getParameter("numRenda") != null &&
				! request.getParameter("numRenda").trim().equals(""))
		{
			pf.setRenda(Float.parseFloat(
					request.getParameter("numRenda").trim().replace(',', '.')
					));
		}
		
		if(request.getParameter("selSituacao") != null &&
				! request.getParameter("selSituacao").trim().equals(""))
		{
			pf.setSituacao(Byte.parseByte(request.getParameter("selSituacao")));
		}
		
		if(request.getParameter("txtEmail") != null &&
				! request.getParameter("txtEmail").trim().equals(""))
		{
			pf.setEmail(request.getParameter("txtEmail").trim());
		}
		
		if(request.getParameter("pwdSenha") != null &&
				! request.getParameter("pwdSenha").trim().equals(""))
		{
			pf.setSenha(request.getParameter("pwdSenha").trim());
		}
		
		Repositorio<PessoaFisica> repositorio = new Repositorio<PessoaFisica>();
		
		repositorio.criar(pf);
		
		PersistenceConfig.closeEntityManager();
		
		RequestDispatcher rd = request.getRequestDispatcher("/pessoafisica");
		
		rd.forward(request, response);
	}
}