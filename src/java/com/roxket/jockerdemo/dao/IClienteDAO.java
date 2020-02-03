/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.dao;
import com.roxket.jockerdemo.modelos.Cliente;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IClienteDAO {
	
	public List<Cliente> listAll();
	public String insert(Cliente prov);
	public String update(Cliente prov);
	public String delete(Cliente prov);
	public Cliente findById(long idProveedor);
	
}
