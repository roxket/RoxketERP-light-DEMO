
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.ProveedorJDBCDAO;
import com.roxket.jockerdemo.modelos.Proveedor;
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
@WebServlet(name = "ProveedoresController", urlPatterns = {"/proveedores"})
public class ProveedoresController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();
		
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
				List<Proveedor> listaProveedor = proveedorDAO.listAll();
				request.setAttribute("proveedores", listaProveedor);
				request.getRequestDispatcher("/WEB-INF/proveedores/index.jsp").forward(request, response);

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
						insertarProveedor(request, response);
						break;
						
					case "Borrar":
						borrarProveedor(request, response);
						break;
						
					case "Actualizar":
						actulizarProveedor(request, response);
						break;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}

	}

	private void formNuevo(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
//		ClienteJDBCDAO proveedorDAO = new ClienteJDBCDAO();
//		
//		List<Proveedor> listaProveedores = proveedorDAO.listAll();
//		request.setAttribute("proveedores", listaProveedores);
		
		request.setAttribute("tipoForm", "Crear");
		request.getRequestDispatcher("/WEB-INF/proveedores/form.jsp").forward(request, response);
	}

	private void formEditar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();
		
		List<Proveedor> listaProveedores = proveedorDAO.listAll();
		request.setAttribute("proveedores", listaProveedores);
		
		long idProveedor = Long.parseLong(request.getParameter("idProveedor"));
		Proveedor proveedor  = proveedorDAO.findById(idProveedor);
		
		if(proveedor != null){
			request.setAttribute("tipoForm", "Actualizar");
			request.setAttribute("proveedor", proveedor);
			request.getRequestDispatcher("/WEB-INF/proveedores/form.jsp").forward(request, response);
		}

	}

	private void insertarProveedor(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		try {
			long idProveedor = Long.parseLong(request.getParameter("idProveedor"));
			String nombreProveedor = request.getParameter("nombreProveedor");
			String contacto = request.getParameter("contacto");
			String celuProveedor = request.getParameter("celuProveedor");
			String fijoProveedor = request.getParameter("fijoProveedor");

			ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();

			String mensaje = proveedorDAO.insert(new Proveedor(idProveedor, nombreProveedor, contacto, celuProveedor, fijoProveedor));

			request.getSession().setAttribute("operacionProveedor", mensaje);
			response.sendRedirect(request.getContextPath() + "/proveedores");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void borrarProveedor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long idProveedor = Long.parseLong(request.getParameter("idProveedor"));

		ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();

		String mensaje = proveedorDAO.delete(new Proveedor(idProveedor));
		request.getSession().setAttribute("operacionProveedor", mensaje);

		response.sendRedirect(request.getContextPath() + "/proveedores");
	}

	private void actulizarProveedor(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		try {
			long idProveedor = Long.parseLong(request.getParameter("idProveedor"));
			String nombreProveedor = request.getParameter("nombreProveedor");
			String contacto = request.getParameter("contacto");
			String celuProveedor = request.getParameter("celuProveedor");
			String fijoProveedor = request.getParameter("fijoProveedor");

			ProveedorJDBCDAO proveedorDAO = new ProveedorJDBCDAO();

			String mensaje = proveedorDAO.update(new Proveedor(idProveedor, nombreProveedor, contacto, celuProveedor, fijoProveedor));

			request.getSession().setAttribute("operacionProveedor", mensaje);
			response.sendRedirect(request.getContextPath() + "/proveedores");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
