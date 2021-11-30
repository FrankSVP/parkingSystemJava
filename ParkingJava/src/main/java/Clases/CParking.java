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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author blood
 */
public class CParking {
    
    
       public void AgregarAutosParking( JTextField codcliente, JLabel codCuenta , JTextField codEspacio)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
       
        
     String consulta =("select insertarParking(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codcliente.getText()));
         cs.setInt(2, Integer.parseInt(codCuenta.getText()));
         cs.setInt(3, Integer.parseInt(codEspacio.getText()));
         
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se registró correctamente el vehículo al Parking, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
       
       
         public void MostrarTotalVehiculosParking(JTable tablaTotalVehiculosParking)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       tablaTotalVehiculosParking.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Matricula");
     modelo.addColumn("Color");
     modelo.addColumn("Marca");
     modelo.addColumn("Fecha/Hora Inicio");
     modelo.addColumn("Fecha/Hora Salida");
     modelo.addColumn("Nombre Dueño");
     modelo.addColumn("Identificacion");
     modelo.addColumn("Tipo Vehiculo");
     modelo.addColumn("Estado Vehiculo");
     modelo.addColumn("Estado Espacio");
     modelo.addColumn("Espacio Fisico");
     
     
 
     
     tablaTotalVehiculosParking.setModel(modelo);
     
    
     
     sql = "select * from totalAutosEstacionamiento;";
    
    
    String [] datos= new String [12];
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
               datos[10]= rs.getString(11);
               datos[11]= rs.getString(12);
           
             
                
               modelo.addRow(datos);
               
               
           }
           
           tablaTotalVehiculosParking.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
     
}
