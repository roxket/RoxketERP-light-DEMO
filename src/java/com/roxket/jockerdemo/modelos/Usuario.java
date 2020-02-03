
package com.roxket.jockerdemo.modelos;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Oxker Studio
 */
public class Usuario implements Serializable{

    private long usuarioId;
    private String usuario;
    private String password;
    
    public Usuario(){}

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long id) {
        this.usuarioId = id;
    }
    
    public boolean validarEmail(){
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

 
        Matcher matcher = pattern.matcher(this.usuario);
        
        return matcher.find();
    }
	
}
