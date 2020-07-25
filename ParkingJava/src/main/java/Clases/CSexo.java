/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.BigDecimal;
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
public class CSexo {
    
            public void ComboSexo(JComboBox cbsexo)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbsexo.setModel(modelo);

     sql = "select * from combosexo;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           // cbsexo.addItem("Sexo");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbsexo.addItem(nombre);
            }
           cbsexo.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
            
   public void MostrarIdPorSexo(JComboBox combosexo, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridporsexo(?)");
 
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,combosexo.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridporsexo"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
