/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Frank
 */
public class CTotalEstacionamiento {
    
         public void MostrarTotalEstacionamiento(JTable TablaTotalAutosEstacionamiento)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalAutosEstacionamiento.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Matricula");
     modelo.addColumn("Color");
     modelo.addColumn("Marca");
     modelo.addColumn("HoraEntrada");
     modelo.addColumn("HoraSalida");
     modelo.addColumn("Propietario");
     modelo.addColumn("Identificacion");
      modelo.addColumn("Estado");
 
    
     
     TablaTotalAutosEstacionamiento.setModel(modelo);
     
    
     
     sql = "select * from totalAutosEstacionamiento;";
    
    
    String [] datos= new String [9];
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
               
             
                
               modelo.addRow(datos);
               
               
           }
           
           TablaTotalAutosEstacionamiento.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
}
