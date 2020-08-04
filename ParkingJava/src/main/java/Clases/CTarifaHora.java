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
public class CTarifaHora {
    
    public void MostrarTarifaHora(JTable TablaTotalTarifaHora)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalTarifaHora.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("PrecioHora");
    
     
     TablaTotalTarifaHora.setModel(modelo);
     
    
     
     sql = "select * from totaltarifahora;";
    
    
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
           
           TablaTotalTarifaHora.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
    
    public void ComboPrecioHora(JComboBox cbpreciohora)
    {
      Conexion.CConexion conexion = new Conexion.CConexion();
      
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
         
        String sql="";

     cbpreciohora.setModel(modelo);

     sql = "select * from combopreciohora;";
 
    Statement st;
    
       try {
            st = conexion.ConexionBD().createStatement(); 
            
            ResultSet rs =  st.executeQuery(sql);
           // cbpreciohora.addItem("Seleccione una tarifa");
           while(rs.next())
            {
               
                String nombre=rs.getString(1);

                cbpreciohora.addItem(nombre);
            }
           cbpreciohora.setModel(modelo);
           
   } 
       catch (SQLException ex) 
       {
           System.out.println("Error:+ " +ex.toString());
                   
       }
       
       
    }
    
    
        public void MostrarIdPorTarifaHora(JComboBox combotarifahora, JTextField codigo)               
        {
            
            Conexion.CConexion conexion = new Conexion.CConexion();
         
        String consulta =("select mostraridporpreciohora(?)");
       double valorComboHora = Double.parseDouble(combotarifahora.getSelectedItem().toString());
               try {
              
         CallableStatement cs =conexion.ConexionBD().prepareCall(consulta);
         cs.setBigDecimal(1,BigDecimal.valueOf(valorComboHora));
     
         cs.execute();
         ResultSet rs = cs.executeQuery();
                 
         
                if(rs.next())
                {
                    
                    codigo.setText(rs.getString("mostraridporpreciohora"));
                   
                }
              
               } 
               catch (SQLException ex) 
               {
                    JOptionPane.showMessageDialog(null,"Error:" + ex.toString());
               }
        
        }
        
        
     public void AgregarTarifaHora( JTextField tarifahora)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        double valorTarifaDia = Double.parseDouble(tarifahora.getText());
        
     String consulta =("select insertartarifahora(?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setBigDecimal(1, BigDecimal.valueOf(valorTarifaDia));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente la tarifa de hora, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
     
     public void seleccionarTarifaHora(JTable totalTarifaHora, JTextField codigoTarifaHora, JTextField tarifaHora)
    {
    
        int fila = totalTarifaHora.getSelectedRow();
        
        if (fila >=0) {
            
            codigoTarifaHora.setText(totalTarifaHora.getValueAt(fila, 0).toString());
            tarifaHora.setText(totalTarifaHora.getValueAt(fila, 1).toString());
  
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fila no Seleccionada");
        }
        
    }
     
         public void modificarTarifaHora( JTextField codigoTarifaHora, JTextField tarifaHora)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
    
       double dtarifadia = Double.parseDouble(tarifaHora.getText());
       
     String consulta =("select modificartarifahora(?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setInt(1, Integer.parseInt(codigoTarifaHora.getText()));
         cs.setBigDecimal(2, BigDecimal.valueOf(dtarifadia));
     
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Se modificó correctamente la tarifa de hora, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se modificó correctamente los datos" + ex.toString());
                  
               }
    
    }
    
    
}
