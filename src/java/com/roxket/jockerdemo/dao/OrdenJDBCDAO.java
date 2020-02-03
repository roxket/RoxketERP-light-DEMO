
package com.roxket.jockerdemo.dao;
import java.sql.*;
import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.Cliente;
import com.roxket.jockerdemo.modelos.DetalleOrden;
import com.roxket.jockerdemo.modelos.Empleado;
import com.roxket.jockerdemo.modelos.Orden;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oxker Studio
 */
public class OrdenJDBCDAO implements IOrdenJDBCDAO{

    @Override
    public List<Orden> listAll() {
        Orden orden = null;
        Empleado empleado = null;
        Cliente cliente = null;
        List<Orden> ordenes = new ArrayList<>();
        
        bbddPG bbdd = new bbddPG();
        
        EmpleadoJDBCDAO daoEmp = new EmpleadoJDBCDAO();
        ClienteJDBCDAO daoClie = new ClienteJDBCDAO();
        
        try {
            String sql = "select * from ordenes";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long id = rs.getLong("ordenid");
                long idEmpleado = rs.getLong("empleadoid");
                long idCliente = rs.getLong("clienteid");
                java.sql.Date fecha = rs.getDate("fechaorden");
                int descuento = rs.getInt("descuento");
				Double importe = rs.getDouble("importe");
                
                empleado = daoEmp.findById(idEmpleado);
                cliente = daoClie.findById(idCliente);
                
                if (empleado != null || cliente != null) {
                    orden = new Orden(id, cliente, empleado, fecha, descuento, importe);
                    ordenes.add(orden);
                }
            }
            
            bbdd.desconectarDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ordenes;
    }

    @Override
    public List<DetalleOrden> detalles(Orden orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden findById(long idOrden) {
        Orden orden = null;
        Empleado empleado = null;
        Cliente cliente = null;
        bbddPG bbdd = new bbddPG();
        
        try {
            String sql = "select * from ordenes where ordenid=? LIMIT 1";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ps.setLong(1, idOrden);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long idEmpleado = rs.getLong("empleadoid");
                long idCliente = rs.getLong("clienteid");
                java.sql.Date fecha = rs.getDate("fechaorden");
                int descuento = rs.getInt("descuento");
                Double importe = rs.getDouble("importe");
                
                IEmpleadoDAO daoEmp = new EmpleadoJDBCDAO();
                IClienteDAO daoClie = new ClienteJDBCDAO();
                empleado = daoEmp.findById(idEmpleado);
                cliente = daoClie.findById(idCliente);
                orden = new Orden(idOrden, cliente, empleado, fecha, descuento, importe.doubleValue());
            }
            
            bbdd.desconectarDB();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orden;
    }

    @Override
    public Orden insert(Orden orden) {
        String mensaje="";
        bbddPG bbdd = new bbddPG();
        try {
            int idGenerado = 0;
            bbdd.getConnection().setAutoCommit(false);
            String sql = "insert into ordenes(clienteid, empleadoid, fechaorden, descuento, importe) "
                    + "values(?,?,?,?,?)";
            
            PreparedStatement ps = bbdd.getConnection().
                    prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, (int)orden.getCliente().getClienteId());
            ps.setInt(2, (int)orden.getEmpleado().getEmpleadoId());
            ps.setDate(3, (Date) orden.getFechaOrden());
            ps.setDouble(4, orden.getDescuento());
            ps.setBigDecimal(5, new BigDecimal(orden.getImporte()));
            
            ps.executeUpdate();
            
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
            }
            
            PreparedStatement ps2;
            
            for (DetalleOrden detalle : orden.getDetalles()) {
                String sqlDetalles = "insert into detalle_ordenes "
                        + "(ordenid, productoid, cantidad, importe) "
                        + "values(?,?,?,?)";
                
                ps2 = bbdd.getConnection().prepareStatement(sqlDetalles);
                ps2.setInt(1, idGenerado);
                ps2.setInt(2, (int)detalle.getProducto().getProductoId());
                ps2.setDouble(3, detalle.getCantidad());
                ps2.setBigDecimal(4, new BigDecimal(detalle.getImporte()));
                
                ps2.executeUpdate();
                
            }
            
            mensaje = "El pedido se ha creado correctamente";
            bbdd.getConnection().commit();
            
            orden.setOrdenId(idGenerado);
            
        } catch (SQLException ex) {
            if (bbdd.getConnection() != null) {
                try {
                    System.err.print("La transacción no pudo realizarse");
                    bbdd.getConnection().rollback();
                } catch(SQLException excep) {
                    System.err.println("no pudo hacerse el rollback de la transacción");
                }
                
            }
            ex.printStackTrace();
            mensaje = "No fue posible registrar producto: " + ex.getMessage();
			try {
				bbdd.desconectarDB();
			} catch (SQLException ex1) {
				Logger.getLogger(OrdenJDBCDAO.class.getName()).log(Level.SEVERE, null, ex1);
			}
        }
        return orden;
    }

    @Override
    public Orden delete(Orden orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
