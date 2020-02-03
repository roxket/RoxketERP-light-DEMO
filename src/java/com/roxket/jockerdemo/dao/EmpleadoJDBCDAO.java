
package com.roxket.jockerdemo.dao;

import java.sql.*;
import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Empleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oxker Studio
 */
public class EmpleadoJDBCDAO implements IEmpleadoDAO {

	@Override
	public List<Empleado> listAll() {
		Empleado empleado = null;
		List<Empleado> listaEmpleados = new ArrayList<>();
		bbddPG bbdd = new bbddPG();

		try {
			String sql = "SELECT * FROM EMPLEADOS";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empleado = new Empleado();
				long id = rs.getInt("empleadoid");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				Date fechaNac = rs.getDate("fecha_nac");
				int reportaA = rs.getInt("reporta_a");
				int extension = rs.getInt("extension");

				empleado = new Empleado(id, nombre, apellido, fechaNac, reportaA, extension);

				if (reportaA > 0) {
					Empleado jefe = findById(reportaA);
					empleado.setJefe(jefe.getNombreCompleto());
				}
				
				listaEmpleados.add(empleado);				
			}
			
			System.out.println("listall jdbcdao");
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaEmpleados;
	}

	@Override
	public String insert(Empleado empleado) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "INSERT INTO EMPLEADOS (empleadoid, nombre, apellido, fecha_nac, reporta_a, extension) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, empleado.getEmpleadoId());
			ps.setString(2, empleado.getNombre());
			ps.setString(3, empleado.getApellido());
			ps.setDate(4, (Date) empleado.getFechaNac());
			ps.setInt(5, empleado.getReportaA());
			ps.setInt(6, empleado.getExtension());
			
			ps.executeUpdate();
			
			mensaje = "Empleado creado correctamente.";
			
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible registrar este empleado: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String update(Empleado empleado) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "UPDATE EMPLEADOS SET nombre=? apellido=? fecha_nac=? reporta_a=? extension=? WHERE empleadoid=?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setString(1, empleado.getNombre());
			ps.setString(2, empleado.getApellido());
			ps.setDate(3, (Date) empleado.getFechaNac());
			ps.setInt(4, empleado.getReportaA());
			ps.setInt(5, empleado.getExtension());
			ps.setLong(6, empleado.getEmpleadoId());
			
			ps.executeUpdate();
			
			mensaje = "Los datos del empleado se han actualizado correctamente.";
			
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible actualizar este empleado: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public String delete(Empleado empleado) {
		String mensaje = "";
		bbddPG bbdd = new bbddPG();
		
		try {
			String sql = "DELETE FROM  EMPLEADOS WHERE empleadoid=?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, empleado.getEmpleadoId());
			
			ps.executeUpdate();
			
			mensaje = "Los datos del empleado se han eliminado correctamente.";
			
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible eliminar este empleado: " + e.getMessage();
		}
		return mensaje;
	}

	@Override
	public Empleado findById(long idEmpleado) {
		Empleado empleado = null;
		bbddPG bbdd = new bbddPG();

		try {
			String sql = "SELECT * FROM EMPLEADOS WHERE EMPLEADOID=? LIMIT 1";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, idEmpleado);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empleado = new Empleado();
				long id = rs.getInt("empleadoid");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				Date fechaNac = rs.getDate("fecha_nac");
				int reportaA = rs.getInt("reporta_a");
				int extension = rs.getInt("extension");

				empleado = new Empleado(id, nombre, apellido, fechaNac, reportaA, extension);

				if (reportaA > 0) {
					Empleado jefe = findById(reportaA);
					empleado.setJefe(jefe.getNombreCompleto());
				}
			}
			bbdd.desconectarDB();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleado;

	}
	
}
