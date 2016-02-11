package br.edu.ifpb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;

import br.edu.ifpb.dao.UsuarioDAO;
import br.edu.ifpb.entidade.Usuario;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
				
		UsuarioDAO usuario = new UsuarioDAO();
		Usuario user = new Usuario();
		String home = null;
		
		//home = new String("boasVindas.jsp");
		//Recuperando valores do formulário
		user.setEmail(request.getParameter("email"));
		user.setSenha(request.getParameter("senha"));
		
		// "Catucando" o usuario no BD
		
		if(usuario.consultaUser(user)){
			HttpSession session = request.getSession();
			session.setAttribute("email", user.getEmail());
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("index.html");
			}
	}
}