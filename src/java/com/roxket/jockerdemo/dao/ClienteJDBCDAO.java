

package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author Oxker Studio
 */
public class ClienteJDBCDAO implements IClienteDAO{

	@Override
	public List<Cliente> listAll() {
		Cliente cliente;
		List <Cliente> listaClientes = new ArrayList<>();
		bbddPG bbdd = new bbddPG();
		try {
			String sql = "select * from clientes";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long idCliente = rs.getInt("clienteid");
				String cedula = rs.getString("cedularuc");
				String nombreCompania = rs.getString("nombrecia");
				String nombreContacto = rs.getString("nombrecontacto");
				String direccionCliente = rs.getString("direccioncli");
				String fax = rs.getString("fax");
				String email = rs.getString("email");
				String movil = rs.getString("celular");
				String fijo = rs.getString("fijo");
				
				cliente = new Cliente(idCliente, cedula, nombreCompania, nombreContacto, direccionCliente, fax, email, movil, fijo);
				listaClientes.add(cliente);
			}
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			System.out.println("Error en listAll de clientes: " + e.getMessage());
			
			
		}
		return listaClientes;
		
	}

	@Override
	public String insert(Cliente cliente) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "insert into clientes (clienteid, cedularuc, nombrecia, nombrecontacto, direccioncli, fax, email, celular, fijo) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setLong(1, cliente.getClienteId());
			ps.setString(2, cliente.getCedulaRuc());
			ps.setString(3, cliente.getNombreCia());
			ps.setString(4, cliente.getNombreContacto());
			ps.setString(5, cliente.getDireccionCli());
			ps.setString(6, cliente.getFax());
			ps.setString(7, cliente.getEmail());
			ps.setString(8, cliente.getCelular());
			ps.setString(9, cliente.getFijo());
			
			
			ps.executeUpdate();
			mensaje = "El cliente se ha creado correctamente.";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible registrar este cliente: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String update(Cliente cliente) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			
			String sql = "UPDATE CLIENTES SET cedularuc = ?, nombrecia = ?, nombrecontacto = ?, direccioncli = ?, fax = ?, email = ?, celular = ?, fijo = ?" + "WHERE clienteid = ?";
			
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setString(1, cliente.getCedulaRuc());
			ps.setString(2, cliente.getNombreCia());
			ps.setString(3, cliente.getNombreContacto());
			ps.setString(4, cliente.getDireccionCli());
			ps.setString(5, cliente.getFax());
			ps.setString(6, cliente.getEmail());
			ps.setString(7, cliente.getCelular());
			ps.setString(8, cliente.getFijo());
			ps.setLong(9, cliente.getClienteId());
			ps.executeUpdate();
			mensaje = "Los datos del cliente se han actualizado correctamente.";
			bbdd.desconectarDB();
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible actualizar este cliente: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String delete(Cliente cliente) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "DELETE from clientes WHERE clienteid =?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			
			ps.setLong(1, cliente.getClienteId());
			
			ps.executeUpdate();
			mensaje = "Los datos del cliente se han eliminado correctamente.";
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible eliminar este cliente: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public Cliente findById(long clienteid) {
		Cliente cliente = null;
		bbddPG bbdd = new bbddPG();
		try {
			String sql = "SELECT * FROM clientes WHERE clienteid=? LIMIT 1";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, clienteid);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				long idCliente = rs.getInt("clienteid");
				String cedula = rs.getString("cedularuc");
				String nombreCompania = rs.getString("nombrecia");
				String nombreContacto = rs.getString("nombrecontacto");
				String direccionCliente = rs.getString("direccioncli");
				String fax = rs.getString("fax");
				String email = rs.getString("email");
				String movil = rs.getString("celular");
				String fijo = rs.getString("fijo");

				cliente = new Cliente(idCliente, cedula, nombreCompania, nombreContacto, direccionCliente, fax, email, movil, fijo);
			}
			
			bbdd.desconectarDB();
		} catch (SQLException e) {
			System.out.println("Error en findById de clientes: " + e.getMessage());
		}
		return cliente;
	}

}
