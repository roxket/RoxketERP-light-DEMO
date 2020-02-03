/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.jdbc;
import java.sql.*;

/**
 *
 * @author Oxker Studio
 */
public class bbddPG {
	
	Connection conn = null;
	
	public bbddPG(){
		String urlDatabase = "jdbc:postgresql://localhost:5432/db_roxket";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(urlDatabase, "postgres", "1234");		
		} catch (SQLException ex) {
			System.out.println("Excepción: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Excepción: " + " no se encontró en driver " + ex.getMessage());
		}
	}
	
	public Connection getConnection(){
		return this.conn;
	}
	public void desconectarDB () throws SQLException {
		System.out.println("Cerrar conexión a base de datos");
		if (conn != null) {
			conn.close();
//			} catch (SQLException ex) {
//				System.out.println("No se realizó la desconexión: " + ex.getMessage());
		}
	}
		
	
}
