package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.IUsuarioDAO;
import com.roxket.jockerdemo.dao.UsuarioJDBCDAO;
import com.roxket.jockerdemo.excepciones.ErrorLoginException;
import com.roxket.jockerdemo.excepciones.ErrorRegistroException;
import com.roxket.jockerdemo.modelos.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Oxker Studio
 */
@WebServlet(name = "UsuariosController", urlPatterns = {"/usuarios"})
public class UsuariosController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("accion") != null) {
			String accion = request.getParameter("accion");
			switch (accion) {
				case "cerrarSesion":
					cerrarSesion(request, response);
					break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("accion") != null) {

			String accion = request.getParameter("accion");
			System.out.println("la accion es: " + accion);
			switch (accion) {
				case "crearUsuario":
					crearUsuario(request, response);
					break;
				case "validarUsuario":
					validarUsuario(request, response);
					break;
//				case "cerrarSesion":
//					cerrarSesion(request, response);
//					break;
			}
		}
	}

	public String encriptarTexto(String texto) {
		return DigestUtils.md5Hex(texto);
	}

	private void crearUsuario(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String user = request.getParameter("email");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		if (pass.equals(pass2)) {
			String passEncript = encriptarTexto(pass);
			UsuarioJDBCDAO daoUsuario = new UsuarioJDBCDAO();
			Usuario usuario = new Usuario();
			usuario.setUsuario(user);
			usuario.setPassword(passEncript);
			boolean validarEmail = usuario.validarEmail();
			if (!validarEmail) {
				throw new ServletException(new ErrorRegistroException("Proporcione un email válido"));
			}
			Usuario usuarioSesion;
			try {
				usuarioSesion = daoUsuario.crearUsuario(usuario);
				request.getSession().setAttribute("user", usuarioSesion);
				response.sendRedirect(request.getContextPath() + "/productos");
			} catch (ErrorRegistroException ex) {
				throw new ServletException(ex);
			}

		} else {
			throw new ServletException(new ErrorRegistroException("Las contraseñas no coinciden"));
		}
	}

	private void validarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("email");
		String pass = request.getParameter("pass");
		String passwordEncriptado = encriptarPassword(pass);

		Usuario usuarioAux = new Usuario();
		usuarioAux.setUsuario(user);
		usuarioAux.setPassword(passwordEncriptado);
		boolean validarDireccionEmail = usuarioAux.validarEmail();

		if (!validarDireccionEmail) {
			throw new ServletException(
					new ErrorLoginException("Proporcione una dirección email válida"));
		}

		IUsuarioDAO usuarioDao = new UsuarioJDBCDAO();
		Usuario usuarioSesion = usuarioDao.validarUsuario(user, passwordEncriptado);
		if (usuarioSesion != null) {
			request.getSession().setAttribute("user", usuarioSesion);
			response.sendRedirect(request.getContextPath() + "/productos");
		} else {
			throw new ServletException(
					new ErrorLoginException("No existe ningun usuario con estas credenciales"));
		}
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getSession().removeAttribute("user");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		System.out.println("Sesion cerrada");
	}

	private String encriptarPassword(String pass) {
		return DigestUtils.md5Hex(pass);
	}
}
