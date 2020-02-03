
package com.roxket.jockerdemo.dao;

import java.sql.*;
import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Categoria;
import com.roxket.jockerdemo.modelos.Producto;
import com.roxket.jockerdemo.modelos.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public class ProductoJDBCDAO implements IProductoDAO{
	@Override
	public List<Producto> listAll(){
		Producto producto;
		List <Producto> listaProductos = new ArrayList<>();
		bbddPG bbdd = new bbddPG();
		try {
			String sql = "SELECT * FROM PRODUCTOS";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long id = rs.getLong("productoid");
				long idProv = rs.getLong("proveedorid");
				long idCat = rs.getLong("categoriaid");
				String desc = rs.getString("descripcion");
				double precio = rs.getDouble("preciounit");
				int existencia = rs.getInt("existencia");
				
				Categoria categoria = new CategoriaJDBCDAO().findById(idCat);
				Proveedor proveedor = new ProveedorJDBCDAO().findById(idProv);
				
				producto = new Producto (id, proveedor, categoria, desc, precio, existencia);
				
				listaProductos.add(producto);
			}
			
			bbdd.desconectarDB();

		} catch (SQLException e) {
			System.out.println("Error en listAll de productos: " + e.getMessage());
		}
		return listaProductos;
	}
	
	@Override
	public String insert(Producto producto){
		String mensaje ="";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "INSERT INTO PRODUCTOS (productoid, proveedorid, categoriaid, descripcion, preciounit, existencia) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setLong(1, producto.getProductoId());
			ps.setLong(2, producto.getProveedor().getProveedorId());
			ps.setLong(3, producto.getCategoria().getCategoriaId());
			ps.setString(4, producto.getDescripcion());
			ps.setDouble(5, producto.getPrecioUnit());
			ps.setInt(6, producto.getExistencia());
			
			ps.executeUpdate();
			
			mensaje = "El producto se ha creado correctamente.";

			bbdd.desconectarDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mensaje;
	}
	
	@Override
	public String update(Producto producto){
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "UPDATE productos SET proveedorid = ?, categoriaid = ?, descripcion = ?, preciounit = ?, existencia = ? WHERE productoid = ?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, producto.getProveedor().getProveedorId());
			ps.setLong(2, producto.getCategoria().getCategoriaId());
			ps.setString(3, producto.getDescripcion());
			ps.setDouble(4, producto.getPrecioUnit());
			ps.setInt(5, producto.getExistencia());
			ps.setLong(6, producto.getProductoId());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			mensaje = "No fue posible actualizar los datos de este producto.";
		}
		
		return mensaje;
	}
	
	@Override
	public String delete(Producto producto){
		String mensaje="";
		bbddPG  bbdd = new bbddPG();
		
		try {
			String sql = "DELETE FROM productos WHERE productoid = ?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, producto.getProductoId());
			ps.executeUpdate();
			mensaje = "Los datos del producto se han eliminado correctamente.";
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible eliminar los datos de este producto: " + e.getMessage();
		}
		
		return mensaje;
	}
	
	@Override
	public Producto findById(long idProducto){
		Producto producto = null;
		bbddPG bbdd = new bbddPG();
		try {
			String sql  = "SELECT * FROM productos WHERE productoid = ? LIMIT 1";
		PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
		ps.setLong(1, idProducto);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			long idProd = rs.getLong("productoid");
			long idProv = rs.getLong("proveedorid");
			long idCat = rs.getLong("categoriaid");
			String desc = rs.getString("descripcion");
			double precio = rs.getDouble("preciounit");
			int existencia = rs.getInt("existencia");
			
			Categoria categoria = new CategoriaJDBCDAO().findById(idCat);
			Proveedor proveedor = new ProveedorJDBCDAO().findById(idProv);
			
			producto = new Producto (idProd, proveedor, categoria, desc, precio, existencia);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return producto;
		
	}
	
	@Override
	public List<Producto> getProductosByCategorias(Categoria categoria) {
		Producto producto;
		List<Producto> listaProductos = new ArrayList<>();
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "SELECT * FROM productos WHERE categoriaid = ?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, categoria.getCategoriaId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long id = rs.getLong("productoid");
				long idProv = rs.getLong("proveedorid");
				long idCat = rs.getLong("categoriaid");
				String desc = rs.getString("descripcion");
				double precio = rs.getDouble("preciounit");
				int existencia = rs.getInt("existencia");
				
				Proveedor prov = new ProveedorJDBCDAO().findById(idProv);
				Categoria cat = new CategoriaJDBCDAO().findById(idCat);

				producto = new Producto (id, prov, cat, desc, precio, existencia);	
				
				listaProductos.add(producto);
			}
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error en listAll de categorias: " + e.getMessage());
		}
		
		return listaProductos;
	}

	@Override
	public List<Producto> busquedaPorCriterio(String param){
        Producto producto;
        List<Producto> listaProductos = new ArrayList<>();
        bbddPG bbdd = new bbddPG();
        try {
            String sql = "select * from productos where descripcion ILIKE ?";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("productoid");
                long idProv = rs.getLong("proveedorid");
                long idCat = rs.getLong("categoriaid");
                String desc = rs.getString("descripcion");
                double precio = rs.getDouble("preciounit");
                int existencia = rs.getInt("existencia");
                
                Categoria cat = new CategoriaJDBCDAO().findById(idCat);
                Proveedor prov = new ProveedorJDBCDAO().findById(idProv);
                
                producto = new Producto(id, prov, cat, desc, precio, existencia);
                
                listaProductos.add(producto);
            }
            
            bbdd.desconectarDB();
            
        } catch (SQLException ex) {
            System.out.println("Error en busquedaPorCriterio de productos: " + 
                    ex.getMessage());
        }
        
        return listaProductos;
    }
	
	
}
