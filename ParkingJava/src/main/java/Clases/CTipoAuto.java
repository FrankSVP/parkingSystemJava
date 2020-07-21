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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author FrankS
 */
public class CTipoAuto {
    
           public void MostrarTipoAuto(JTable tablatotaltatipoauto)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       tablatotaltatipoauto.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("TipoAuto");
    
     
     tablatotaltatipoauto.setModel(modelo);
     
    
     
     sql = "select * from totaltipoauto;";
    
    
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
           
           tablatotaltatipoauto.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
           
         public void ComboTipoAuto(JComboBox cbtipoauto)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbtipoauto.setModel(modelo);

     sql = "select * from combotipoauto;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           // cbtipoauto.addItem("Tipo de auto");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbtipoauto.addItem(nombre);
            }
           cbtipoauto.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
}
