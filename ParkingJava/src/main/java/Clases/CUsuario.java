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
public class CUsuario {
    
            public void AgregarUsuario( JTextField usuario, JTextField contra, JTextField idtipousuario)
    {
       Conexion.CConexion conexion = new Conexion.CConexion();
 
        
     String consulta =("select insertarusuario(?,?,?);");
    
               try {
              
         CallableStatement cs = conexion.ConexionBD().prepareCall(consulta);
         cs.setString(1, usuario.getText());
         cs.setString(2, contra.getText());
         cs.setInt(3,Integer.parseInt(idtipousuario.getText()));
         
      
         cs.execute();
     
         
         JOptionPane.showMessageDialog(null, "Insertó correctamente el usuario, VERIFIQUE");
                                                  
               } 
               catch (SQLException ex) 
               {
                   JOptionPane.showMessageDialog(null, "No se guardó correctamente los datos" + ex.toString());
                  
               }
    
    }
            
          public void MostrarTotalDeUsuarios(JTable TablaTotalUsuarios)
    {
      
        Conexion.CConexion conexion = new Conexion.CConexion();
        
           
     DefaultTableModel modelo = new DefaultTableModel();
     
       TableRowSorter<TableModel>OrdenaTabla = new TableRowSorter<TableModel>(modelo);
       TablaTotalUsuarios.setRowSorter(OrdenaTabla);
               
        String sql="";
        
       
       
     modelo.addColumn("Id");
     modelo.addColumn("Nombres");
     modelo.addColumn("Contraseña");
     modelo.addColumn("TipoDeUsuario");
    
     
     TablaTotalUsuarios.setModel(modelo);
     
    
     
     sql = "select * from totalusuarios;";
    
    
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
           
           TablaTotalUsuarios.setModel(modelo);
           
          
           
       } 
       catch (SQLException ex) 
       {
           System.out.print("Error: " +ex.toString());
       }
       
       
    }
}
