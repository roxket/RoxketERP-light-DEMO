
package com.roxket.jockerdemo.dao;

import java.sql.*;
import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Categoria;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Oxker Studio
 */
public class CategoriaJDBCDAO implements ICategoriaDAO {

	@Override
	public List<Categoria> listAll() {
		
		Categoria cat;
		List <Categoria> listaCategorias = new ArrayList<>();
		try {
			bbddPG bbdd = new bbddPG();
			String sql = "select * from categorias";
			PreparedStatement ps = bbdd.getConnection().prepareCall(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				cat = new Categoria();
				cat.setCategoriaId(rs.getInt("categoriaid"));
				cat.setNombreCat(rs.getString("nombrecat"));
				
				listaCategorias.add(cat);
			}
		} catch (SQLException e) {
			System.out.println("Error en listAll de categorias: " + e.getMessage());
		}
		
		return listaCategorias;
	}

	@Override
	public String insert(Categoria cat) {
		String mensaje = "";
		try {
			bbddPG bbdd = new bbddPG();
			String sql = "insert into categorias(categoriaid, nombrecat)" + "values(?,?)";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, cat.getCategoriaId());
			ps.setString(2, cat.getNombreCat());
			ps.execute();
			mensaje = "Categoria creada correctamente.";
			
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "Imposible crear categoria: " + e.getMessage();
		}
		
		return mensaje;
		
	}

	@Override
	public Categoria findById(long id) {		
		Categoria cat = null;
		
		try {
			bbddPG bbdd = new bbddPG();
			String sql = "SELECT * FROM CATEGORIAS WHERE CATEGORIAID=? LIMIT 1";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cat = new Categoria();
				cat.setCategoriaId(rs.getInt("categoriaid"));
				cat.setNombreCat(rs.getString("nombrecat"));
				
			}
			bbdd.desconectarDB();
			
		} catch (SQLException e) {
		}
		return cat;
	}

	@Override
	public String update(Categoria cat) {
		String mensaje = "";
		try {
			bbddPG bbdd = new bbddPG();
			String sql = "UPDATE CATEGORIAS SET nombreCat = ?" + "WHERE categoriaid = ?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setString(1, cat.getNombreCat());
			ps.setLong(2, cat.getCategoriaId());
			ps.executeUpdate();
			mensaje = "Se actualizó la categoria correctamente.";
			bbdd.desconectarDB();
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible actualizar la categoria: " + e.getMessage();
		}
		
		return mensaje;
	}

	@Override
	public String delete(Categoria cat) {
		String mensaje = "";
		try {
			bbddPG bbdd = new bbddPG();
			String sql = "DELETE from CATEGORIAS where categoriaid =?";
			PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
			ps.setLong(1, cat.getCategoriaId());
			ps.executeUpdate();
			mensaje = "Se ha eliminado la categoria correctamente.";
			bbdd.desconectarDB();
		} catch (SQLException e) {
			e.printStackTrace();
			mensaje = "No fue posible eliminar la categoria: " + e.getMessage();
		}
		
		return mensaje;
	}
	
	@Override
	    public List<Categoria> busquedaPorCriterio(String param) {
        bbddPG bbdd = new bbddPG();
        Categoria cat;
        List<Categoria> listaCategorias = new ArrayList<>();
        try {
            
            String sql = "select * from categorias where nombrecat ILIKE ?";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                cat = new Categoria();
                cat.setCategoriaId(rs.getInt("categoriaid"));
                cat.setNombreCat(rs.getString("nombrecat"));
                listaCategorias.add(cat);
            }
            
            bbdd.desconectarDB();
            
        } catch (SQLException ex) {
            System.out.println("Error en la busqueda de categorias: " + 
                    ex.getMessage());
        }
        
        return listaCategorias;
    }
	
}
