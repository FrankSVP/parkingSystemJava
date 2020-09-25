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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author FrankS
 */
public class CUsuario {
    
            public void AgregarUsuario( JTextField usuario, JTextField contra, JTextField idtipousuario)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
     String consulta =("select insertarusuario(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, usuario.getText());
         cs.setString(2, contra.getText());
         cs.setInt(3,Integer.parseInt(idtipousuario.getText()));
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el usuario, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
            
          public void MostrarTotalDeUsuarios(JTable TablaTotalUsuarios)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalUsuarios.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Nombres");
     modelo.addColumn("Contraseña");
     modelo.addColumn("TipoDeUsuario");
    
     
     TablaTotalUsuarios.setModel(modelo);
     
    
     
     sql = "select * from totalusuarios;";
    
    
    String [] datos= new String [4];
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           
           while (rs.next())
           {
              
               datos[0]= rs.getString(1);
               datos[1]= rs.getString(2);
               datos[2]= rs.getString(3);
               datos[3]= rs.getString(4);
             
                
               modelo.addRow(datos);
               
               
           }
           
           TablaTotalUsuarios.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
          
         public void modificarUsuario( JTextField codigoUsuario, JTextField idusuario, JTextField contrausuario, JTextField codigoTipoUsuario)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select modificarUsuario(?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoUsuario.getText()));
         cs.setString(2, idusuario.getText());
         cs.setString(3, idusuario.getText());
         cs.setInt(4, Integer.parseInt(codigoTipoUsuario.getText()));
       
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el usuario, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente el usuario" + ex.toString());
                  
               }
    
    }
         
      public void seleccionarUsuario(JTable totalEstadoAuto, JTextField codigoUsuario, JTextField idusuario,JTextField contrausuario, JComboBox codigoTipoUsuario)
    {
    
        int fila = totalEstadoAuto.getSelectedRow();
        
        if (fila >=0) {
            
            codigoUsuario.setText(totalEstadoAuto.getValueAt(fila, 0).toString());
            idusuario.setText(totalEstadoAuto.getValueAt(fila, 1).toString());
            contrausuario.setText(totalEstadoAuto.getValueAt(fila, 2).toString());
            codigoTipoUsuario.setSelectedItem(totalEstadoAuto.getValueAt(fila, 3).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
                    public void eliminarUsuario( JTextField codigoUsuario)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarusuario(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoUsuario.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente el usuario" + ex.toString());
                  
               }
    
    }
}
