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
public class CTarifaPorAuto {
    
            public void MostrarIdPorTipoAuto(JComboBox combotipoauto, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridportipopauto(?)");
       
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, combotipoauto.getSelectedItem().toString());
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridportipopauto"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
            
         public void MostrarTotalDeTarifaPorAuto(JTable TablaTotalTarifaPorAuto)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalTarifaPorAuto.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("TipoAuto");
     modelo.addColumn("PrecioH/F");
     modelo.addColumn("PrecioDia");
    
     
     TablaTotalTarifaPorAuto.setModel(modelo);
     
    
     
     sql = "select * from totaltarifaauto;";
    
    
    String [] datos= new String [4];
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
             
                
               modelo.addRow(datos);
               
               
           }
           
           TablaTotalTarifaPorAuto.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
         
    public void AgregarTarifaAuto( JTextField idTipoAuto, JTextField idPrecioHora, JTextField idPrecioDia)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
        
     String consulta =("select insertartarifaporauto(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(idTipoAuto.getText()));
         cs.setInt(2, Integer.parseInt(idPrecioHora.getText()));
         cs.setInt(3, Integer.parseInt(idPrecioDia.getText()));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente la tarifa del auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
    
       public void seleccionarTarifaPorAuto(JTable totalTarifaPorAuto, JTextField codigoTarifaPorAuto, JComboBox tipoAuto , JComboBox PrecioHora, JComboBox PrecioDia)
    {
    
        int fila = totalTarifaPorAuto.getSelectedRow();
        
        if (fila >=0) {
            
            codigoTarifaPorAuto.setText(totalTarifaPorAuto.getValueAt(fila, 0).toString());
            tipoAuto.setSelectedItem(totalTarifaPorAuto.getValueAt(fila, 1).toString());
            PrecioHora.setSelectedItem(totalTarifaPorAuto.getValueAt(fila, 2).toString());
            PrecioDia.setSelectedItem(totalTarifaPorAuto.getValueAt(fila, 3).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
       
      public void modificarTarifaPorAuto( JTextField codTarifaPorAuto, JTextField codTipoAuto, JTextField codPrecioHora, JTextField codPrecioDia)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
   
       
     String consulta =("select modificartarifaporauto(?,?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codTarifaPorAuto.getText()));
         cs.setInt(2, Integer.parseInt(codTipoAuto.getText()));
         cs.setInt(3, Integer.parseInt(codPrecioHora.getText()));
         cs.setInt(4, Integer.parseInt(codPrecioDia.getText()));
       
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente la tarifa por Auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
      
        public void eliminarTarifaPorAuto( JTextField codigoTarifaPorAuto)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       
       
     String consulta =("select eliminartarifaporauto(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTarifaPorAuto.getText()));

 
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se eliminó correctamente la tarifa por auto, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se eliminó correctamente la la tarifa por auto" + ex.toString());
                  
               }
    
    }
}
