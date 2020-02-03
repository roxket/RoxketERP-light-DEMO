

package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Proveedor;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Oxker Studio
 */
public class ProveedorJDBCDAO implements IProveedorDAO{

	@Override
	public List<Proveedor> listAll() {
		Proveedor proveedor;
		List <Proveedor> listaProveedores = new ArrayList<>();
		bbddPG bbdd = new bbddPG();
		try {
			String sql = "select * from proveedores";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long idProveedor = rs.getInt("proveedorid");
				String nombreProveedor = rs.getString("nombreprov");
				String contacto = rs.getString("contacto");
				String movil = rs.getString("celuprov");
				String fijo = rs.getString("fijoprov");
				
				proveedor = new Proveedor(idProveedor, nombreProveedor, contacto, movil, fijo);
				listaProveedores.add(proveedor);
			}
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			System.out.println("Error en listAll de proveedores: " + e.getMessage());
			
			
		}
		return listaProveedores;
	}

	@Override
	public String insert(Proveedor proveedor) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "insert into proveedores (proveedorid, nombreprov, contacto, celuprov, fijoprov) values(?, ?, ?, ?, ?)";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setLong(1, proveedor.getProveedorId());
			ps.setString(2, proveedor.getNombreProv());
			ps.setString(3, proveedor.getContacto());
			ps.setString(4, proveedor.getCeluProv());
			ps.setString(5, proveedor.getFijoProv());
			
			ps.executeUpdate();
			mensaje = "El proveedor se ha creado correctamente.";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible registrar este proveedor: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String update(Proveedor proveedor) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "UPDATE proveedores SET nombreprov=? contacto=? celuprov=? fijoprov=? WHERE proveedorid=?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setString(1, proveedor.getNombreProv());
			ps.setString(2, proveedor.getContacto());
			ps.setString(3, proveedor.getCeluProv());
			ps.setString(4, proveedor.getFijoProv());
			ps.setLong(5, proveedor.getProveedorId());
			
			ps.executeUpdate();
			mensaje = "Los datos del cliente se han actualizado correctamente.";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible actualizar este cliente: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String delete(Proveedor proveedor) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "DELETE from proveedores WHERE proveedorid =?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setLong(1, proveedor.getProveedorId());
			
			ps.executeUpdate();
			mensaje = "Los datos del proveedor se han eliminado correctamente.";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible eliminar este proveedor: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public Proveedor findById(long proveedorid) {
		Proveedor proveedor = null;
		bbddPG bbdd = new bbddPG();
		try {
			String sql = "SELECT * FROM proveedores WHERE proveedorid=? LIMIT 1";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, proveedorid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long idProveedor = rs.getInt("proveedorid");
				String nombreProveedor = rs.getString("nombreprov");
				String contacto = rs.getString("contacto");
				String movil = rs.getString("celuprov");
				String fijo = rs.getString("fijoprov");

				proveedor = new Proveedor(idProveedor, nombreProveedor, contacto, movil, fijo);
			}
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			System.out.println("Error en findById de clientes: " + e.getMessage());
		}
		return proveedor;
	}

}
