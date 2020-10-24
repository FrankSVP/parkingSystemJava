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
 * @author Frank
 */
public class CCliente {
    
     public void AgregarCliente( JTextField nombre, JTextField apellido, JTextField identificacion, JTextField telefono, JTextField direccion, JTextField codigoSexo, JTextField codigoAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
        
     String consulta =("select insertarcliente(?,?,?,?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, nombre.getText());
         cs.setString(2, apellido.getText());
         cs.setString(3, identificacion.getText());
         cs.setInt(4, Integer.parseInt(telefono.getText()));
         cs.setString(5, direccion.getText());
         cs.setInt(6, Integer.parseInt(codigoSexo.getText()));
         cs.setInt(7, Integer.parseInt(codigoAuto.getText()));
        

         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el Cliente, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
     
        public void MostrarTotalClientes(JTable TablaTotalClientes)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalClientes.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Dni");
     modelo.addColumn("Nombre");
     modelo.addColumn("Apellidos");
     modelo.addColumn("Celular");
     modelo.addColumn("Direccion");
     modelo.addColumn("Sexo");
     modelo.addColumn("Matricula");
     modelo.addColumn("Color");
     modelo.addColumn("Marca");
     
    
     
     TablaTotalClientes.setModel(modelo);
     
    
     
     sql = "select * from totalclientes;";
    
    
    String [] datos= new String [10];
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
               datos[4]= rs.getString(5);
               datos[5]= rs.getString(6);
               datos[6]= rs.getString(7);
               datos[7]= rs.getString(8);
               datos[8]= rs.getString(9);
               datos[9]= rs.getString(10);
            
             
                
               modelo.addRow(datos);
               
               
           }
           
           TablaTotalClientes.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
        
            public void seleccionarCliente(JTable totalCliente, JTextField idCliente, JTextField identificacion, JTextField nombre, JTextField apellido, JTextField celular,JTextField direccion, JComboBox sexo)
    {
    
        int fila = totalCliente.getSelectedRow();
        
        if (fila >=0) {
            
            idCliente.setText(totalCliente.getValueAt(fila, 0).toString());
            identificacion.setText(totalCliente.getValueAt(fila, 1).toString());
            nombre.setText(totalCliente.getValueAt(fila, 2).toString());
            apellido.setText(totalCliente.getValueAt(fila, 3).toString());
            celular.setText(totalCliente.getValueAt(fila, 4).toString());
            direccion.setText(totalCliente.getValueAt(fila, 5).toString());
            sexo.setSelectedItem(totalCliente.getValueAt(fila, 6).toString()); 
          
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
            
            public void modificarCliente( JTextField codigoCliente, JTextField nombres, JTextField apellidos, JTextField identificacion, JTextField celular, JTextField direccion, JTextField sexo)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
      
       
     String consulta =("select modificarcliente(?,?,?,?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoCliente.getText()));
         cs.setString(2, nombres.getText());
         cs.setString(3, apellidos.getText());
         cs.setString(4, identificacion.getText());
         cs.setInt(5, Integer.parseInt(celular.getText()));
         cs.setString(6, direccion.getText());
         cs.setInt(7, Integer.parseInt(sexo.getText()));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el cliente, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
            
                          public void eliminarCliente( JTextField codigoCliente)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarCliente(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoCliente.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el cliente de auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente el cliente de auto" + ex.toString());
                  
               }
    
    }
}
