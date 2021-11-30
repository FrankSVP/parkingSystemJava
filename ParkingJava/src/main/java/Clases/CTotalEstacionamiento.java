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
     modelo.addColumn("Fecha/Hora Inicio");
     modelo.addColumn("Fecha/Hora Salida");
     modelo.addColumn("Nombre Dueño");
     modelo.addColumn("Identificacion");
     modelo.addColumn("Tipo Vehiculo");
     modelo.addColumn("Estado Vehiculo");
     modelo.addColumn("Estado Espacio");
     modelo.addColumn("Espacio Fisico");
 
    
     
     TablaTotalAutosEstacionamiento.setModel(modelo);
     
    
     
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
           
           TablaTotalAutosEstacionamiento.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
         
       public void seleccionarParkingParaPagar(JTable totalParkingPagar,
               JTextField idparking, JTextField matricula, JTextField color ,
               JTextField marca, JTextField horaEntrada , JTextField horaSalida,
               JTextField nombres, JTextField identificacion )
    {
    
        int fila = totalParkingPagar.getSelectedRow();
        
        if (fila >=0) {
            
            idparking.setText(totalParkingPagar.getValueAt(fila, 0).toString());
            matricula.setText(totalParkingPagar.getValueAt(fila, 1).toString());
            color.setText(totalParkingPagar.getValueAt(fila, 2).toString());
            marca.setText(totalParkingPagar.getValueAt(fila, 3).toString());
            horaEntrada.setText(totalParkingPagar.getValueAt(fila, 4).toString());
            horaSalida.setText(totalParkingPagar.getValueAt(fila, 5).toString());
            nombres.setText(totalParkingPagar.getValueAt(fila, 6).toString());
            identificacion.setText(totalParkingPagar.getValueAt(fila, 7).toString());
           
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
       
     public void insertarHoraSalida( JTextField codigoTipoAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
     String consulta =("select insertarHoraSalida(?)");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTipoAuto.getText()));
       
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se registró la hora de saluda correctamente, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se registró la hora de salida" + ex.toString());
                  
               }
    
    }
     
        public void seleccionarCodigoParking(JTable totalParkingPagar,
               JTextField idparking )
    {
    
        int fila = totalParkingPagar.getSelectedRow();
        
        if (fila >=0) {
            
            idparking.setText(totalParkingPagar.getValueAt(fila, 0).toString());
   
           
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
}
