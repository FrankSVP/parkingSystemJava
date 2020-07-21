/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
           // cbtipousuario.addItem("Tipo de Usuario");
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
       
            public void MostrarIdPorTipoUsuario(JComboBox combotipousuario, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridportipousuario(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, combotipousuario.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridportipousuario"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
