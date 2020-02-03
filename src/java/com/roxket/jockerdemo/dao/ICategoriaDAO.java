/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.Categoria;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface ICategoriaDAO {
	
	public List<Categoria> listAll();
	public String insert(Categoria cat);
	public Categoria findById(long id);
	public String update(Categoria categoria);
	public String delete(Categoria categoria);
	public List<Categoria> busquedaPorCriterio(String param);
}
