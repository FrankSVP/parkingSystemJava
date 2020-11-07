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
public class CTipoPago {
    
       public void MostrarTipoPago(JTable TablaTotalTipoPago)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalTipoPago.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("TipoPago");
    
     
     TablaTotalTipoPago.setModel(modelo);
     
    
     
     sql = "select * from totaltipopago;";
    
    
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
           
           TablaTotalTipoPago.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
       
          public void AgregarTipoPago( JTextField tipopago)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
        
     String consulta =("select insertartipopago(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, tipopago.getText());
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el tipo de Pago, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
          
          
        public void seleccionarTipoPago(JTable totalTipoPago, JTextField codigoTipoPago, JTextField tipoPgo)
    {
    
        int fila = totalTipoPago.getSelectedRow();
        
        if (fila >=0) {
            
            codigoTipoPago.setText(totalTipoPago.getValueAt(fila, 0).toString());
            tipoPgo.setText(totalTipoPago.getValueAt(fila, 1).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
        
        
       public void modificarTipoPago( JTextField codigoTipoPago, JTextField tipopago)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificartipopago(?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTipoPago.getText()));
         cs.setString(2, tipopago.getText());
 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente el tipo de pago, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
       
                public void eliminarTipoPago( JTextField codigoTipoPago)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminartipopago(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTipoPago.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente el tipo de pago, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente el tipo de pago" + ex.toString());
                  
               }
    
    }
                
     public void comboTipoPago(JComboBox cbTipoPago)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbTipoPago.setModel(modelo);

     sql = "select * from combotipopago;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           // cbsexo.addItem("Sexo");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbTipoPago.addItem(nombre);
            }
           cbTipoPago.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
     
        public void MostrarIdPorTipoPago(JComboBox comboTipoPago, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridportipopago(?)");
 
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1,comboTipoPago.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridportipopago"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
}
