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
 * @author FrankS
 */
public class CTarifaDia {
    
          public void MostrarTarifaDia(JTable tablatotaltarfiadia)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       tablatotaltarfiadia.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("PrecioDia");
    
     
     tablatotaltarfiadia.setModel(modelo);
     
    
     
     sql = "select * from totaltarifadia;";
    
    
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
           
           tablatotaltarfiadia.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
          
     public void ComboPrecioDia(JComboBox cbpreciodia)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbpreciodia.setModel(modelo);

     sql = "select * from combopreciodia;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
            cbpreciodia.addItem("Seleccione una tarifa");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbpreciodia.addItem(nombre);
            }
           cbpreciodia.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
     
               public void MostrarIdPorTarifaDia(JComboBox combotarifadia, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
       //   double tarifdiadb = Double.parseDouble(combotarifadia.getSelectedItem().toString());
          
        String consulta =("select mostraridporpreciodia(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,"4.60");
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridporpreciodia"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}


