/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.CategoriaJDBCDAO;
import com.roxket.jockerdemo.modelos.Categoria;
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
@WebServlet(name = "CategoriasController", urlPatterns = {"/categorias"})
public class CategoriasController extends HttpServlet {


	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			if(request.getParameter("accion") != null){
				String accion = (String) request.getParameter("accion");
				
				switch(accion){
					case "Nuevo":
						formNuevo(request, response);
						break;
					case "Editar":
						formEditar(request, response);
						break;
				
				}
			} else{
				CategoriaJDBCDAO daoCategoria = new CategoriaJDBCDAO();

				List<Categoria> listaCategorias = daoCategoria.listAll();

				request.setAttribute("categorias", listaCategorias);

				request.getRequestDispatcher("/WEB-INF/categorias/index.jsp").forward(request, response);

			}
		
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getParameter("accion") != null ){
			String accion = (String) request.getParameter("accion");
			
			switch(accion){
				case "Crear":
					insertarCategoria(request, response);
					break;
				case "borrar":
					borrarCategoria(request, response);
					break;
				case "Actualizar":
					actualizarCategoria(request, response);
					break;
				
			}
		}
	}
	

	private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.setAttribute("tipoForm", "Crear");
			request.getRequestDispatcher("/WEB-INF/categorias/form.jsp").forward(request, response);
	}

	private void insertarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException{	
			
			long idCat = Long.parseLong(request.getParameter("idCat"));
			String nombreCat = request.getParameter("nombreCat");
			
			CategoriaJDBCDAO categoriaDAO = new CategoriaJDBCDAO();
			
			String mensaje = categoriaDAO.insert(new Categoria(idCat, nombreCat));
			
			request.getSession().setAttribute("operacionCategoria", mensaje);
			if(!response.isCommitted()){
				response.sendRedirect(request.getContextPath()+"/categorias");
			}
	}

	private void formEditar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			long idCat = Long.parseLong(request.getParameter("idCat"));
			CategoriaJDBCDAO categoriaDAO = new CategoriaJDBCDAO();
			
			Categoria cat = categoriaDAO.findById(idCat);
			if(cat != null){
				request.setAttribute("tipoForm", "Actualizar");
				request.setAttribute("categoria", cat);
				request.getRequestDispatcher("/WEB-INF/categorias/form.jsp").forward(request, response);
				
			}
	}

	private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			long idCat = Long.parseLong(request.getParameter("idCat"));
			String nombreCat = request.getParameter("nombreCat");
			
			CategoriaJDBCDAO categoriaDAO = new CategoriaJDBCDAO();
			
			String mensaje = categoriaDAO.update(new Categoria(idCat, nombreCat));
			
			request.getSession().setAttribute("operacionCategoria", mensaje);
			
			response.sendRedirect(request.getContextPath()+"/categorias");
	}

	private void borrarCategoria(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			long idCat = Long.parseLong(request.getParameter("idCat"));
			CategoriaJDBCDAO categoriaDAO = new CategoriaJDBCDAO();
			
			String mensaje = categoriaDAO.delete(new Categoria(idCat));
			
			request.getSession().setAttribute("operacionCategoria", mensaje);
			
			response.sendRedirect(request.getContextPath()+"/categorias");
			
	}
}
