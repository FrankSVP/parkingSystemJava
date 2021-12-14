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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Frank
 */
public class CTipoEstado {
    
        public void MostrarTipoEstado(JTable tbTipoEstado)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       tbTipoEstado.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("TipoEstado");
    
     
     tbTipoEstado.setModel(modelo);
     
    
     
     sql = "select * from totaltiposestado;";
    
    
    String [] datos= new String [2];
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           
           while (rs.next())
           {
              
               datos[0]= rs.getString(1);
               datos[1]= rs.getString(2);
             
                
               modelo.addRow(datos);
               
               
           }
           
           tbTipoEstado.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
        
             public void AgregarTipoEstado( JTextField tipoEstado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
       
        
     String consulta =("select insertarTipoEstado(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, tipoEstado.getText());
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el  tipo de estado , VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
             
        public void seleccionarTipoEstado(JTable totalTipoEstado, JTextField codigoTipoEstado, JTextField tipoEstado)
    {
    
        int fila = totalTipoEstado.getSelectedRow();
        
        if (fila >=0) {
            
            codigoTipoEstado.setText(totalTipoEstado.getValueAt(fila, 0).toString());
            tipoEstado.setText(totalTipoEstado.getValueAt(fila, 1).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
        
                 public void elimininarTipoEstado( JTextField codigoTipoEstado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarTipoEstado(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTipoEstado.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el tipo de estado, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente el estado de auto" + ex.toString());
                  
               }
    
    }
                 
        public void modificarTipoEstado( JTextField codigoTipoEstado, JTextField tipoEstado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificarTipoEstado(?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTipoEstado.getText()));
         cs.setString(2, tipoEstado.getText());
 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el tipo de estado, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
        
        public void ComboTipoEstado(JComboBox cbTipoEstado)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbTipoEstado.setModel(modelo);

     sql = "select * from combotipoestado;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           // cbsexo.addItem("Sexo");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbTipoEstado.addItem(nombre);
            }
           cbTipoEstado.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
        
    public void MostrarIdPorTipoEstado(JComboBox comboTipoEstado, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostrarIdPorTipoEstado(?)");
 
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,comboTipoEstado.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostrarIdPorTipoEstado"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
     public void MostrarIdPorTipoEstadoTexto(JTextField tipoEstado, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostrarIdPorTipoEstado(?)");
 
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,tipoEstado.getText());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostrarIdPorTipoEstado"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
