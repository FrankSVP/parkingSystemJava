/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author FrankS
 */
public class CTipoUsuario {
    
       public void ComboTipoUsuario(JComboBox cbtipousuario)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbtipousuario.setModel(modelo);

     sql = "select * from combotipousuario;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
            cbtipousuario.addItem("Tipo de Usuario");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbtipousuario.addItem(nombre);
            }
           cbtipousuario.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
}
