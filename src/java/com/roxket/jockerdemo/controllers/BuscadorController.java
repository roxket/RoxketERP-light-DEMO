/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.CategoriaJDBCDAO;
import com.roxket.jockerdemo.dao.ICategoriaDAO;
import com.roxket.jockerdemo.dao.IProductoDAO;
import com.roxket.jockerdemo.dao.ProductoJDBCDAO;
import com.roxket.jockerdemo.modelos.Categoria;
import com.roxket.jockerdemo.modelos.Producto;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "BuscadorController", urlPatterns = {"/buscar"})
public class BuscadorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			            throws ServletException, IOException {
        String parametro = request.getParameter("param");
        ICategoriaDAO daoCategoria = new CategoriaJDBCDAO();
        IProductoDAO daoProducto = new ProductoJDBCDAO();
        List<Categoria> categorias = daoCategoria.busquedaPorCriterio(parametro);
        List<Producto> productos = daoProducto.busquedaPorCriterio(parametro);
        System.out.println("categorias: " + categorias);
        
        request.setAttribute("categorias", categorias);
        request.setAttribute("productos", productos);
        
        request.getRequestDispatcher("/WEB-INF/buscador/buscador.jsp").forward(request, response);
	}

}
