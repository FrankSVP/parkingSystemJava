/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author FrankS
 */
public class CTarifaPorAuto {
    
            public void MostrarIdPorTipoAuto(JComboBox combotipoauto, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridportipopauto(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, combotipoauto.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridportipopauto"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
