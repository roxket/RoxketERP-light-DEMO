/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.Proveedor;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IProveedorDAO {
	
	public List<Proveedor> listAll();
	public String insert(Proveedor prov);
	public String update(Proveedor prov);
	public String delete(Proveedor prov);
	public Proveedor findById(long idProveedor);
	
}
