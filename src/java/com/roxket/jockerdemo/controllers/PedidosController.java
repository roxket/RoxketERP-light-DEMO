
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.ClienteJDBCDAO;
import com.roxket.jockerdemo.dao.DetalleOrdenJDBCDAO;
import com.roxket.jockerdemo.dao.EmpleadoJDBCDAO;
import com.roxket.jockerdemo.dao.ProductoJDBCDAO;
import com.roxket.jockerdemo.dao.OrdenJDBCDAO;
import com.roxket.jockerdemo.modelos.Cliente;
import com.roxket.jockerdemo.modelos.DetalleOrden;
import com.roxket.jockerdemo.modelos.Empleado;
import com.roxket.jockerdemo.modelos.Orden;
import com.roxket.jockerdemo.modelos.Producto;
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
@WebServlet(name = "PedidosController", urlPatterns = {"/pedidos"})
public class PedidosController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			 if (request.getParameter("accion")!= null) {
            String accion = request.getParameter("accion");
            switch(accion){
                case "verPedidos":
                    verPedidos(request, response);
                    break;
                case "hacerPedido":
                    hacerPedido(request, response);
                    break;
                case "verPedido":
                    verPedido(request, response);
                    break;
              }
        }else{
           request.getRequestDispatcher("/WEB-INF/pedidos/dashboard.jsp").forward(request, response);
        }
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    if (request.getParameter("accion")!= null) {
				String accion = request.getParameter("accion");
            switch(accion){
                case "addProducto":
                    addProducto(request, response);
                    break;
                case "terminarPedido":
                    terminarPedido(request, response);
                    break;
            }
        }
	}

	private void verPedidos(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
        OrdenJDBCDAO daoOrden = new OrdenJDBCDAO();
        List<Orden> ordenes = daoOrden.listAll();
        request.setAttribute("ordenes", ordenes);
        request.getRequestDispatcher("/WEB-INF/pedidos/index.jsp").forward(request, response);
	}

	private void hacerPedido(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException{
        
        Orden orden = (Orden)request.getSession().getAttribute("orden");
        
        ClienteJDBCDAO daoClie = new ClienteJDBCDAO();
        EmpleadoJDBCDAO daoEmp = new EmpleadoJDBCDAO();
        ProductoJDBCDAO daoProd = new ProductoJDBCDAO();
        
        List<Empleado> empleados = daoEmp.listAll();
        List<Cliente> clientes = daoClie.listAll();
        List<Producto> productos = daoProd.listAll();
        
        request.setAttribute("empleados", empleados); 
        request.setAttribute("clientes", clientes);
        request.setAttribute("productos", productos);
        
        if (orden == null) {
            orden = new Orden();
            orden.setImporte(0.0);
            orden.setFechaOrden(new java.sql.Date(new java.util.Date().getTime()));
        }
        else{
            double importeOrden = 0.0;
            for (DetalleOrden detalle : orden.getDetalles()) {
                importeOrden += detalle.getImporte();
            }
            orden.setImporte(importeOrden);
        }
        
        request.getSession().setAttribute("orden", orden);
        
        request.getRequestDispatcher("/WEB-INF/pedidos/new.jsp").forward(request, response);
	}

	private void verPedido(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
        
        long id = Long.parseLong(request.getParameter("idPedido"));
        OrdenJDBCDAO daoOrden =  new OrdenJDBCDAO();
        DetalleOrdenJDBCDAO daoDetalles = new DetalleOrdenJDBCDAO();
         
        Orden orden = daoOrden.findById(id);
        List<DetalleOrden> detalles = daoDetalles.getDetalles(orden);
        orden.setDetalles(detalles);
        
        request.setAttribute("orden", orden);
        request.getRequestDispatcher("/WEB-INF/pedidos/show.jsp").forward(request, response);
	}

	private void addProducto(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException{
        
        long idProducto = Long.parseLong(request.getParameter("idProd"));
        double cantidad = Double.parseDouble(request.getParameter("cantProd"));
        
        ProductoJDBCDAO productoDao = new ProductoJDBCDAO();
        Producto producto = productoDao.findById(idProducto);
        double importe = producto.getPrecioUnit() * cantidad;
        
        Orden orden = (Orden)request.getSession().getAttribute("orden");
        
        DetalleOrden detalle = new DetalleOrden();
        detalle.setCantidad(cantidad);
        detalle.setProducto(producto);
        detalle.setOrden(orden);
        detalle.setImporte(importe);
        
        if (orden == null) {
            orden = new Orden();
            request.getSession().setAttribute("orden", orden);
        }
        
        orden.getDetalles().add(detalle);
        response.sendRedirect(request.getContextPath()+"/pedidos?accion=hacerPedido");
      
	}

	private void terminarPedido(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException{
        long idCliente = Long.parseLong(request.getParameter("idCliente"));
        long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
        
        ClienteJDBCDAO daoClie = new ClienteJDBCDAO();
        EmpleadoJDBCDAO daoEmp = new EmpleadoJDBCDAO();
        
        Cliente cliente = daoClie.findById(idCliente);
        Empleado empleado = daoEmp.findById(idEmpleado);
        
        Orden orden = (Orden)request.getSession().getAttribute("orden");
        
        orden.setCliente(cliente);
        orden.setEmpleado(empleado);
        
        OrdenJDBCDAO daoOrden = new OrdenJDBCDAO();
        
        Orden ordenCreada = daoOrden.insert(orden);
        
        if (ordenCreada != null) {
            request.getSession().setAttribute("ordenCreada", ordenCreada);
            response.sendRedirect(request.getContextPath()+"/pedidos?accion=verPedidos");
        }
	}
}
