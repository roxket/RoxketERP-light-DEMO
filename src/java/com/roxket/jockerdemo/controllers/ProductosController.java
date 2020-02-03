
package com.roxket.jockerdemo.controllers;

import com.roxket.jockerdemo.dao.CategoriaJDBCDAO;
import com.roxket.jockerdemo.dao.ProductoJDBCDAO;
import com.roxket.jockerdemo.dao.ProveedorJDBCDAO;
import com.roxket.jockerdemo.modelos.Categoria;
import com.roxket.jockerdemo.modelos.Producto;
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
@WebServlet(name = "ProductosController", urlPatterns = {"/productos"})
public class ProductosController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        ProductoJDBCDAO productoDAO = new ProductoJDBCDAO();
        
        if (request.getParameter("accion") != null) {
            
            String accion = (String) request.getParameter("accion");
            
            switch (accion) {
                case "Nuevo":
                    formNuevo(request, response);
                    break;
                case "Editar":
                    formEditar(request, response);
                    break;
            }
        }
        
        else{
            List<Producto> listaProductos = productoDAO.listAll();
            request.setAttribute("productos", listaProductos);
        
            request.getRequestDispatcher("/WEB-INF/productos/index.jsp")
                    .forward(request, response);
        }
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("accion")!=null) {
            String accion = (String)request.getParameter("accion");
            switch(accion){
                case "Crear":
                    insertarProducto(request, response);
                    break;
                case "Borrar":
                    borrarProducto(request, response);
                    break;
                case "Actualizar":
                    actualizarProducto(request, response);
                   break;
            }
        }

	}

	private void formNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Proveedor> proveedores = new ProveedorJDBCDAO().listAll();
        List<Categoria> categorias = new CategoriaJDBCDAO().listAll();
        
        request.setAttribute("categorias", categorias);
        request.setAttribute("proveedores", proveedores);
        request.setAttribute("tipoForm", "Crear");

        request.getRequestDispatcher("/WEB-INF/productos/form.jsp")
                .forward(request, response);
	}

	private void formEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoJDBCDAO productoDAO = new ProductoJDBCDAO();
        
        long idProd = Long.parseLong(request.getParameter("idProducto"));
        Producto producto = productoDAO.findById(idProd);
        
        if (producto != null) {
            List<Proveedor> proveedores = new ProveedorJDBCDAO().listAll();
            List<Categoria> categorias = new CategoriaJDBCDAO().listAll();

            request.setAttribute("categorias", categorias);
            request.setAttribute("proveedores", proveedores);            
            request.setAttribute("tipoForm", "Actualizar");
            request.setAttribute("producto", producto);
            request.getRequestDispatcher("/WEB-INF/productos/form.jsp").forward(request, response);
        }
	}

	private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long claveProd = Long.parseLong(request.getParameter("idProducto"));
        long claveProv = Long.parseLong(request.getParameter("idProveedor"));
        long claveCat = Long.parseLong(request.getParameter("idCategoria"));
        String descripcion = request.getParameter("descripcion");
        double precioU = Double.parseDouble(request.getParameter("precioUnit"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));

        ProductoJDBCDAO productoDAO = new ProductoJDBCDAO();
        Proveedor prov = new ProveedorJDBCDAO().findById(claveProv);
        Categoria cat = new CategoriaJDBCDAO().findById(claveCat);
        String mensaje = productoDAO.insert(new Producto(claveProd, prov, cat, descripcion, precioU, existencia));

        request.getSession().setAttribute("operacionProducto", mensaje);

        response.sendRedirect(request.getContextPath() + "/productos");
	}

	private void borrarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
		long claveProd = Long.parseLong(request.getParameter("idProducto"));
        ProductoJDBCDAO productoDAO = new ProductoJDBCDAO();
        String mensaje = productoDAO.delete(new Producto(claveProd));

        request.getSession().setAttribute("operacionProducto", mensaje);
        response.sendRedirect(request.getContextPath() +"/productos");
	}

	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException {
		long claveProd = Long.parseLong(request.getParameter("idProducto"));
        long claveProv = Long.parseLong(request.getParameter("idProveedor"));
        long claveCat = Long.parseLong(request.getParameter("idCategoria"));
        String descripcion = request.getParameter("descripcion");
        double precioU = Double.parseDouble(request.getParameter("precioUnit"));
        int existencia = Integer.parseInt(request.getParameter("existencia"));

        ProductoJDBCDAO productoDAO = new ProductoJDBCDAO();
        Proveedor prov = new ProveedorJDBCDAO().findById(claveProv);
        Categoria cat = new CategoriaJDBCDAO().findById(claveCat);
        
        String mensaje = productoDAO.update(new Producto(claveProd, prov, cat, descripcion, precioU, existencia));

        request.getSession().setAttribute("operacionProducto", mensaje);

        response.sendRedirect(request.getContextPath() + "/productos");
	}

}
