/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author blood
 */
public class CPago {
    
           public void AgregarPago( JLabel montoPago , JTextField codigoParking, JTextField codigoTipoPago)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        double montoFinal = Double.parseDouble(montoPago.getText());
        
     String consulta =("select insertarPagoFinal(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setBigDecimal(1, BigDecimal.valueOf(montoFinal));
         cs.setInt(2, Integer.parseInt(codigoParking.getText()));
         cs.setInt(3, Integer.parseInt(codigoTipoPago.getText()));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el Pago, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
          
    
}
