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
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Frank
 */
public class CEstadoParking {
    
            public void AgregarEstadoParking( JTextField idEstado, JTextField espacio)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
       
        
     String consulta =("select insertarEspacios(?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(espacio.getText()));
         cs.setInt(2, Integer.parseInt(idEstado.getText()));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el estado de Parking, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
            
          public void seleccionarEstadoParking(JTable totalEstadoParking, JTextField codigoEstado, JComboBox TipoEstado)
    {
    
        int fila = totalEstadoParking.getSelectedRow();
        
        if (fila >=0) {
            
            codigoEstado.setText(totalEstadoParking.getValueAt(fila, 0).toString());
            TipoEstado.setSelectedItem(totalEstadoParking.getValueAt(fila, 2).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
         
  
          public void modificarEstadoParking( JTextField codigoestado, JTextField espacio, JTextField codtipoestado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificarEspacios(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoestado.getText()));
         cs.setInt(2, Integer.parseInt(espacio.getText()));
         cs.setInt(3, Integer.parseInt(codtipoestado.getText()));
        
 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el estado del Parking, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
          
                   public void eliminarEstadoParking( JTextField codigoEstadoParking)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarEspacio(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoEstadoParking.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el estado de parking, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente " + ex.toString());
                  
               }
    
    }
                   
        public void MostrarIdDeEstadoParkingPorIdTipoEstado(JComboBox comboTipoEstado, JTextField codigoEstadoParking)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostrarIdspacePorCodigoEstado(?)");
 
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,comboTipoEstado.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigoEstadoParking.setText(rs.getString("mostrarIdspacePorCodigoEstado"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
        
        
        public void seleccionarEstadoParking(JTable totalestados, JTextField idEstado, JTextField estado, JTextField cantidad)
    {
    
        int fila = totalestados.getSelectedRow();
        
        if (fila >=0) {
            
            idEstado.setText(totalestados.getValueAt(fila, 0).toString());
            estado.setText(totalestados.getValueAt(fila, 1).toString());
            cantidad.setText(totalestados.getValueAt(fila, 2).toString());
          
          
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    } 
    }
                          
  
        
        
        
    

