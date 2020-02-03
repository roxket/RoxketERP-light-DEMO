
package com.roxket.jockerdemo.dao;

import com.roxket.jockerdemo.modelos.Empleado;
import java.util.List;

/**
 *
 * @author Oxker Studio
 */
public interface IEmpleadoDAO {
	public List<Empleado> listAll();
	public String insert(Empleado empleado);
	public String update(Empleado empleado);
	public String delete(Empleado empleado);
	public Empleado findById(long idEmpleado);
}
