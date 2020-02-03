
package com.roxket.jockerdemo.dao;

/**
 *
 * @author Oxker Studio
 */

import com.roxket.jockerdemo.jdbc.bbddPG;
import com.roxket.jockerdemo.modelos.DetalleOrden;
import com.roxket.jockerdemo.modelos.Orden;
import com.roxket.jockerdemo.modelos.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleOrdenJDBCDAO implements IDetalleOrdenJDBCDAO{

    @Override
    public List<DetalleOrden> getDetalles(Orden orden) {
        DetalleOrden detalle;
        List<DetalleOrden> detalles = new ArrayList<>();
        
        ProductoJDBCDAO daoProducto = new ProductoJDBCDAO();
        bbddPG bbdd = new bbddPG();
        
        try {
            String sql = "select * from detalle_ordenes where ordenid = ?";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ps.setLong(1, orden.getOrdenId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                long detalleId = rs.getLong("detalleid");
                long productoId = rs.getLong("productoid");
                double cantidad = rs.getDouble("cantidad");
                double importe = rs.getDouble("importe");
				
                Producto prod = daoProducto.findById(productoId);

                detalle = new DetalleOrden(detalleId, orden, prod, cantidad, importe);
                detalles.add(detalle);
            }
            
            bbdd.desconectarDB();
        } catch (SQLException e) {
            e.printStackTrace();
		}
        return detalles; 
    }

    @Override
    public List<DetalleOrden> getDetalles(long idOrden) {
        return null;
    }

    @Override
    public DetalleOrden insert(DetalleOrden detalle) {
        bbddPG bbdd = new bbddPG();
        try {
            
            String sql = "insert into detalle (ordenid, "
                    + "productoid, cantidad, importe) values (?,?,?,?);";
            PreparedStatement ps = bbdd.getConnection().prepareStatement(sql);
            ps.setLong(1, detalle.getOrden().getOrdenId());
            ps.setLong(2, detalle.getProducto().getProductoId());
            ps.setDouble(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getImporte());
            ps.executeUpdate();
            
            bbdd.desconectarDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return detalle;
    }

    @Override
    public DetalleOrden delete(DetalleOrden detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
