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
public class CAuto {
    
   public void AgregarAuto( JTextField matricula, JTextField color, JTextField fkmarca, JTextField fktipo, JTextField fkestado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
        
     String consulta =("select insertarauto(?,?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, matricula.getText());
         cs.setString(2, color.getText());
         cs.setInt(3, Integer.parseInt(fkmarca.getText()));
         cs.setInt(4, Integer.parseInt(fktipo.getText()));
         cs.setInt(5, Integer.parseInt(fkestado.getText()));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el Auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
   
   
        public void MostrarAutos(JTable tablaTotalAutos)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       tablaTotalAutos.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Matricula");
      modelo.addColumn("Color");
     modelo.addColumn("Marca");
      modelo.addColumn("Tipo");
     modelo.addColumn("Estado");
     
     tablaTotalAutos.setModel(modelo);
     
    
     
     sql = "select * from totalautos;";
    
    
    String [] datos= new String [6];
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
             
                
               modelo.addRow(datos);
               
               
           }
           
           tablaTotalAutos.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
        
  public void SeleccionarAuto (JTable tablaTotalAuto, JTextField codigo, JTextField Matricula, JTextField Color, JTextField Marca){{
      
      int fila = tablaTotalAuto.getSelectedRow();
      
      if (fila >=0)
          {
          codigo.setText(tablaTotalAuto.getValueAt(fila,0).toString()); 
          Matricula.setText(tablaTotalAuto.getValueAt(fila,1).toString()); 
          Color.setText(tablaTotalAuto.getValueAt(fila,2).toString()); 
          Marca.setText(tablaTotalAuto.getValueAt(fila,3).toString());
          
      }
      else
      {
      JOptionPane.showMessageDialog(null, "Fila no seleccionada");
      }
              
      
  }
  
 
  }
  
     public void SeleccionarAutoTotal (JTable tablaTotalAuto, JTextField codigoAuto,
            JTextField Matricula, JTextField Color, JComboBox Marca, JComboBox Tipo, JComboBox Estado){
      
      int fila = tablaTotalAuto.getSelectedRow();
      
      if (fila >=0)
          {
          codigoAuto.setText(tablaTotalAuto.getValueAt(fila,0).toString()); 
          Matricula.setText(tablaTotalAuto.getValueAt(fila,1).toString()); 
          Color.setText(tablaTotalAuto.getValueAt(fila,2).toString());
          Marca.setSelectedItem(tablaTotalAuto.getValueAt(fila, 3).toString());
          Tipo.setSelectedItem(tablaTotalAuto.getValueAt(fila, 4).toString());
          Estado.setSelectedItem(tablaTotalAuto.getValueAt(fila, 5).toString());
          
          
      }
      else
      {
      JOptionPane.showMessageDialog(null, "Fila no seleccionada");
      }
              
      
  }
     
             public void eliminarAuto( JTextField codigoAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarAuto(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoAuto.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente la marca de auto" + ex.toString());
                  
               }
    
    }
             
                  public void modificarAuto( JTextField codigoAuto, JTextField matricula, JTextField color, JTextField codMarca, JTextField codTipo, JTextField codEstado)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificarAuto(?,?,?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoAuto.getText()));
         cs.setString(2, matricula.getText());
         cs.setString(3, color.getText());
         cs.setInt(4, Integer.parseInt(codMarca.getText()));
         cs.setInt(5, Integer.parseInt(codTipo.getText()));
         cs.setInt(6, Integer.parseInt(codEstado.getText()));
 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
    
}
