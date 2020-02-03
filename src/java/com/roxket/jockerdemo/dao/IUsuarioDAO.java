
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.excepciones.ErrorRegistroException;
import com.roxket.jockerdemo.modelos.Usuario;

/**
 *
 * @author Oxker Studio
 */
public interface IUsuarioDAO {
	Usuario crearUsuario(Usuario usuario) throws ErrorRegistroException;
	Usuario validarUsuario(String usuario, String pass);
	
}
