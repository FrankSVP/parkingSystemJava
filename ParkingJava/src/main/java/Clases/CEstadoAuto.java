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
 * @author FrankS
 */
public class CEstadoAuto {
        public void MostrarEstadoAuto(JTable TablaTotalEstadoAuto)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalEstadoAuto.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("EstadoAuto");
    
     
     TablaTotalEstadoAuto.setModel(modelo);
     
    
     
     sql = "select * from totalestadoauto;";
    
    
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
           
           TablaTotalEstadoAuto.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
        
         public void ComboEstadoAuto(JComboBox cbestadoauto)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbestadoauto.setModel(modelo);

     sql = "select * from comboestadoauto;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
            //cbestadoauto.addItem("Estado");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbestadoauto.addItem(nombre);
            }
           cbestadoauto.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
         
     public void AgregarEstadoAuto( JTextField estadoauto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
       
        
     String consulta =("select insertarestadoauto(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, estadoauto.getText());
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el estado del auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
     
        public void MostrarIdPorEstadoAuto(JComboBox comboEstadoAuto, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
       //   double tarifdiadb = Double.parseDouble(combotarifadia.getSelectedItem().toString());
          
        String consulta =("select mostraridporestadoauto(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,comboEstadoAuto.getSelectedItem().toString());
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridporestadoauto"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
