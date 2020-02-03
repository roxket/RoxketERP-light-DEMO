
package com.roxket.jockerdemo.controllers;
import com.roxket.jockerdemo.dao.ClienteJDBCDAO;
import com.roxket.jockerdemo.modelos.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oxker Studio
 */
@WebServlet(name = "ClientesController", urlPatterns = {"/clientes"})
public class ClientesController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			ClienteJDBCDAO clienteDAO = new ClienteJDBCDAO();
		
			if (request.getParameter("accion") != null) {
				String accion = (String) request.getParameter("accion");

				switch(accion){
					case "Nuevo":
						formNuevo(request, response);
						break;
					case "Editar":
						formEditar(request, response);
						break;
				}
			} else {
				List<Cliente> listaCliente = clienteDAO.listAll();
				request.setAttribute("clientes", listaCliente);
				request.getRequestDispatcher("/WEB-INF/clientes/index.jsp").forward(request, response);
			}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			if (request.getParameter("accion") != null) {
			try {
				String accion = (String)request.getParameter("accion");
				
				switch(accion){
					case "Crear":
						insertarCliente(request, response);
						break;
						
					case "Borrar":
						borrarCliente(request, response);
						break;
						
					case "Actualizar":
						actulizarCliente(request, response);
						break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

	}

	private void formNuevo(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		ClienteJDBCDAO clienteJDBCDAO = new ClienteJDBCDAO();
		
		List<Cliente> listaClientes = clienteJDBCDAO.listAll();
		request.setAttribute("clientes", listaClientes);
		
		request.setAttribute("tipoForm", "Crear");
		request.getRequestDispatcher("/WEB-INF/clientes/form.jsp").forward(request, response);
	}

	private void formEditar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		ClienteJDBCDAO clienteJDBCDAO = new ClienteJDBCDAO();
		
		List<Cliente> listaClientes = clienteJDBCDAO.listAll();
		request.setAttribute("clientes", listaClientes);
		
		long idCliente = Long.parseLong(request.getParameter("idCliente"));
		Cliente cliente  = clienteJDBCDAO.findById(idCliente);
		
		if(cliente != null){
			request.setAttribute("tipoForm", "Actualizar");
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("/WEB-INF/clientes/form.jsp").forward(request, response);
		}

	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			long idCliente = Long.parseLong(request.getParameter("idCliente"));
			String cedula = request.getParameter("cedulaRuc");
			String nombreCompania = request.getParameter("nombreCompania");
			String nombreContacto = request.getParameter("nombreContacto");
			String direccionCliente = request.getParameter("direccionCliente");
			String fax = request.getParameter("fax");
			String email = request.getParameter("email");
			String movil = request.getParameter("movil");
			String fijo = request.getParameter("fijo");

			ClienteJDBCDAO clienteJDBCDAO = new ClienteJDBCDAO();

			String mensaje = clienteJDBCDAO.insert(new Cliente(idCliente, cedula, nombreCompania, nombreContacto, direccionCliente, fax, email, movil, fijo));

			request.getSession().setAttribute("operacionCliente", mensaje);
			response.sendRedirect(request.getContextPath() + "/clientes");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void borrarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long idCliente = Long.parseLong(request.getParameter("idCliente"));
		System.out.println("id borrarCliente: " + idCliente);
		ClienteJDBCDAO clienteJDBCDAO = new ClienteJDBCDAO();

		String mensaje = clienteJDBCDAO.delete(new Cliente(idCliente));
		request.getSession().setAttribute("operacionCliente", mensaje);

		response.sendRedirect(request.getContextPath() + "/clientes");
	}

	private void actulizarCliente(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			System.out.println("idActualiarCliente: " + request.getParameter("idCliente"));

		try {
			long idCliente = Long.parseLong(request.getParameter("idCliente"));
			String cedula = request.getParameter("cedulaRuc");
			String nombreCompania = request.getParameter("nombreCompania");
			String nombreContacto = request.getParameter("nombreContacto");
			String direccionCliente = request.getParameter("direccionCliente");
			String fax = request.getParameter("fax");
			String email = request.getParameter("email");
			String movil = request.getParameter("movil");
			String fijo = request.getParameter("fijo");

			ClienteJDBCDAO clienteJDBCDAO = new ClienteJDBCDAO();
			System.out.println("datos: " + idCliente + " " + cedula + " " + nombreCompania + " " + nombreContacto + " " + direccionCliente + " " + fax + " " + email + " " + movil + " " + fijo);
			String mensaje = clienteJDBCDAO.update(new Cliente(idCliente, cedula, nombreCompania, nombreContacto, direccionCliente, fax, email, movil, fijo));

			request.getSession().setAttribute("operacionCliente", mensaje);
			response.sendRedirect(request.getContextPath() + "/clientes");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
