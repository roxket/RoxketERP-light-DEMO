
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.excepciones.ErrorRegistroException;
import java.sql.*;
import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Usuario;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Oxker Studio
 */
public class UsuarioJDBCDAO implements IUsuarioDAO {

	@Override
	public Usuario crearUsuario(Usuario usuario) throws ErrorRegistroException{
	 bbddPG base = new bbddPG();
        try {
            
            String sql = "insert into usuarios (usuario, password) "
                    + "values(?, ?)";
            PreparedStatement ps = base.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
            
            int idGenerado;
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
                usuario.setUsuarioId(idGenerado);
            }
			
			base.desconectarDB();

        } catch(PSQLException excepcionPSQL){
            if (excepcionPSQL.getSQLState().equals("23505")) {
                throw new ErrorRegistroException("Esta direccion de email ya tiene una cuenta registrada");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            if (base.getConnection()!=null) {
               // base.desconectarDB();
            }
        }
        return usuario;
	}

	@Override
	public Usuario validarUsuario(String usuario, String pass) {
		Usuario user = null;
        bbddPG base = new bbddPG();
		  try {
            String sql = "select * from usuarios where usuario = ? AND password = ? LIMIT 1;";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                user = new Usuario();
                long id = rs.getLong("id");
                String nomUsuario = rs.getString("usuario");
                String contra = rs.getString("password");
                
                user.setUsuarioId(id);
                user.setUsuario(nomUsuario);
                user.setPassword(contra);
            }
            
            base.desconectarDB();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el usuario");
            ex.printStackTrace();
        }finally{
            if (base.getConnection()!=null) {
               // base.desconectarDB();
            }
        }
        
        return user;
	}
	
}
