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
        
  public void SeleccionarAuto (JTable tablaTotalAuto, JTextField codigo, JTextField Marca){
      
      int fila = tablaTotalAuto.getSelectedRow();
      
      if (fila >=0)
          {
          codigo.setText(tablaTotalAuto.getValueAt(fila,0).toString()); 
          Marca.setText(tablaTotalAuto.getValueAt(fila,3).toString()); 
      }
      else
      {
      JOptionPane.showMessageDialog(null, "Fila no seleccionada");
      }
              
      
  }
     
    
}
