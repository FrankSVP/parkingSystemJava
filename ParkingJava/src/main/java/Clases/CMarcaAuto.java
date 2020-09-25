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
public class CMarcaAuto {
    
     public void MostrarMarcaAuto(JTable TablaTotalMarcaAuto)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalMarcaAuto.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("MarcaAuto");
    
     
     TablaTotalMarcaAuto.setModel(modelo);
     
    
     
     sql = "select * from totalmarcaauto;";
    
    
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
           
           TablaTotalMarcaAuto.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
     
           public void ComboMarca(JComboBox cbmarca)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbmarca.setModel(modelo);

     sql = "select * from combomarca;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
          //  cbmarca.addItem("Marca");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbmarca.addItem(nombre);
            }
           cbmarca.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
           
           
        public void AgregarMarcaAuto( JTextField marcaauto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
       
        
     String consulta =("select insertartmarcaauto(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, marcaauto.getText());
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente la marca de auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
        
       public void MostrarIdPorMarca(JComboBox comboMarcaAuto, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
       //   double tarifdiadb = Double.parseDouble(combotarifadia.getSelectedItem().toString());
          
        String consulta =("select mostraridporsmarcaauto(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,comboMarcaAuto.getSelectedItem().toString());
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridporsmarcaauto"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
       
       public void seleccionarMarcaAuto(JTable totalMarcaAuto, JTextField codigoMarcaAuto, JTextField marcaAuto)
    {
    
        int fila = totalMarcaAuto.getSelectedRow();
        
        if (fila >=0) {
            
            codigoMarcaAuto.setText(totalMarcaAuto.getValueAt(fila, 0).toString());
            marcaAuto.setText(totalMarcaAuto.getValueAt(fila, 1).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
       
       
       public void modificarMarcaAuto( JTextField codigoMarcaAuto, JTextField marcaAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificarmarcaauto(?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoMarcaAuto.getText()));
         cs.setString(2, marcaAuto.getText());
 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente la marca de auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
         public void eliminarMarcaAuto( JTextField codigoMarcaAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminarmarcaauto(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoMarcaAuto.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente la marca de auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente la marca de auto" + ex.toString());
                  
               }
    
    }
}
