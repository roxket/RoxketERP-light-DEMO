
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.EmpleadoJDBCDAO;
import com.roxket.jockerdemo.modelos.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oxker Studio
 */
@WebServlet(name = "EmpleadoController", urlPatterns = {"/empleados"})
public class EmpleadoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
		
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
			List<Empleado> listaEmpleados = empleadoDAO.listAll();
			request.setAttribute("empleados", listaEmpleados);
			request.getRequestDispatcher("/WEB-INF/empleados/index.jsp").forward(request, response);
		
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
						insertarEmpleado(request, response);
						break;
						
					case "Borrar":
						borrarEmpleado(request, response);
						break;
						
					case "Actualizar":
						actulizarEmpleado(request, response);
						break;
				}
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			
		}

	}

	private void formNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
		
		List<Empleado> listaEmpleados = empleadoDAO.listAll();
		request.setAttribute("empleados", listaEmpleados);
		
		request.setAttribute("tipoForm", "Crear");
		request.getRequestDispatcher("/WEB-INF/empleados/form.jsp").forward(request, response);
	}

	private void formEditar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
		
		List<Empleado> listaEmpleados = empleadoDAO.listAll();
		request.setAttribute("empleados", listaEmpleados);
		
		long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
		Empleado empleado  = empleadoDAO.findById(idEmpleado);
		
		if(empleado != null){
			request.setAttribute("tipoForm", "Actualizar");
			request.setAttribute("empleado", empleado);
			request.getRequestDispatcher("/WEB-INF/empleados/form.jsp").forward(request, response);
		}
	}

	private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			try {
			long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
			String nombreEmpleado = request.getParameter("nombreEmpleado");
			String apellidoEmpleado = request.getParameter("apellidoEmpleado");
			String fechaNacimiento = request.getParameter("fechaNacimiento");
			int reportaA = Integer.parseInt(request.getParameter("reportaA"));
			int extension = Integer.parseInt(request.getParameter("extension"));
			
			java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
			java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
			
			EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();

			String mensaje = empleadoDAO.insert(new Empleado(idEmpleado, nombreEmpleado, apellidoEmpleado, fechaSQL, reportaA, extension));
			
			request.getSession().setAttribute("operacionEmpleado", mensaje);
			response.sendRedirect(request.getContextPath()+"/empleados");
			
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	private void borrarEmpleado(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
		
		EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
		
		String mensaje = empleadoDAO.delete(new Empleado(idEmpleado));
		request.getSession().setAttribute("operacionEmpleado", mensaje);
		
		response.sendRedirect(request.getContextPath()+"/empleados");
		
	}

	private void actulizarEmpleado(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, ParseException {
		
		try{
		long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
		String nombreEmpleado = request.getParameter("nombreEmpleado");
		String apellidoEmpleado = request.getParameter("apellidoEmpleado");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		int reportaA = Integer.parseInt(request.getParameter("reportaA"));
		int extension = Integer.parseInt(request.getParameter("extension"));
		
		java.util.Date fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse(fechaNacimiento);
		java.sql.Date fechaSQL = new java.sql.Date(fechaNac.getTime());
			
		EmpleadoJDBCDAO empleadoDAO = new EmpleadoJDBCDAO();
			
		String mensaje = empleadoDAO.update(new Empleado(idEmpleado, nombreEmpleado, apellidoEmpleado, fechaSQL, reportaA, extension));
			
		request.getSession().setAttribute("operacionEmpleado", mensaje);
		response.sendRedirect(request.getContextPath()+"/empleados");
		
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
	}
}
