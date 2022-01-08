package controlador.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() { }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("usuarioAutenticado") != null && 
				"OK".equals(session.getAttribute("usuarioAutenticado")))
		{
			request.setAttribute("tituloPagina", "Sair");
			request.setAttribute("pathView", "/WEB-INF/views/auth/logout.jsp");
		}
		else
		{
			request.setAttribute("tituloPagina",
					"Sistema de Controle Bancário Web - Página Inicial");
			request.setAttribute("pathView", "/WEB-INF/index.jsp");
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
		
		if(session.getAttribute("usuarioAutenticado") != null && 
				"OK".equals(session.getAttribute("usuarioAutenticado")))
		{
			session.removeAttribute("usuarioAutenticado");
			session.removeAttribute("usuario");
		}
		
		request.setAttribute("tituloPagina",
				"Sistema de Controle Bancário Web - Página Inicial");
		request.setAttribute("pathView", "/WEB-INF/index.jsp");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/template.jsp");
		rd.forward(request, response);
	}

}
