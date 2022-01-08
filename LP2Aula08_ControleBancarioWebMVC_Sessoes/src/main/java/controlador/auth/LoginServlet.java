package controlador.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.PessoaFisica;
import modelo.repositorio.PessoaFisicaRepositorio;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   public LoginServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("usuarioAutenticado") != null && 
				"OK".equals(session.getAttribute("usuarioAutenticado")))
				
		{
			PessoaFisica p = (PessoaFisica) session.getAttribute("usuario");
			
			request.setAttribute("tituloPagina",
					"Sistema de Controle Bancário Web - Página Inicial<br>" +
			        "Bem-vindo " + p.getNome() + "!");
			request.setAttribute("pathView", "/WEB-INF/index.jsp");
		}
		else
		{
		   request.setAttribute("tituloPagina", "Autenticação no Sistema");
		   request.setAttribute("pathView", "/WEB-INF/views/auth/login.jsp");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/template.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		boolean sucesso = false;
		
		String email = request.getParameter("txtEmail").trim();
		String senha = request.getParameter("pwdSenha").trim();
		
		PessoaFisicaRepositorio repositorio = new PessoaFisicaRepositorio();
		
		PessoaFisica p = repositorio.recuperarPessoaFisicaPorEmail(email);
		
		if(p != null && p.getSituacao() == 1) 
		{
			if(p.getSenha().equals(senha))
			{
				session.setAttribute("usuarioAutenticado", "OK");
				session.setAttribute("usuario", p);
				
				request.setAttribute("tituloPagina",
						"Sistema de Controle Bancário Web - Página Inicial<br>" +
				        "Bem-vindo " + p.getNome() + "!");
				request.setAttribute("pathView", "/WEB-INF/index.jsp");
				
				sucesso = true;
			}
		}
		
		if(!sucesso)
		{
			request.setAttribute("tituloPagina",
					"Sistema de Controle Bancário Web - Página Inicial");
			request.setAttribute("pathView", "/WEB-INF/index.jsp");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/template.jsp");
		rd.forward(request, response);
	}

}
