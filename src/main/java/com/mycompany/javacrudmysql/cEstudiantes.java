/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudmysql;

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
 * @author Oscar CR
 */
public class cEstudiantes {
    int codigo;
    String nombreEstudiante;
    String apellidosEstudiantes;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidosEstudiantes() {
        return apellidosEstudiantes;
    }

    public void setApellidosEstudiantes(String apellidosEstudiantes) {
        this.apellidosEstudiantes = apellidosEstudiantes;
    }
    
    public void insertarEstudiante(JTextField paramNombre, JTextField paramApellidos){
        
        setNombreEstudiante(paramNombre.getText());
        setApellidosEstudiantes(paramApellidos.getText());
        
        CConexion objCConexion = new CConexion();
        
        String consulta = "insert into alumnos (nombre,apellido) values (?,?);";
        
        try {
            CallableStatement cs =objCConexion.estableConnection().prepareCall(consulta);
            
            cs.setString(1, getNombreEstudiante());
            cs.setString(2, getApellidosEstudiantes());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"error:" +e.toString());
            
            
        }
        
        
    }
    
    public void mostrarEstudiantes(JTable paramTablaTotalEstudiantes){
         CConexion objCConexion = new CConexion();
        DefaultTableModel modelo = new  DefaultTableModel();
        
        TableRowSorter<TableModel>ordeTableRowSorter= new  TableRowSorter<TableModel>(modelo);
        paramTablaTotalEstudiantes.setRowSorter(ordeTableRowSorter);
        
        String sql="";
        
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        
        paramTablaTotalEstudiantes.setModel(modelo);
        
        sql ="select *from alumnos;";
        
        String[] datos = new String[3];
        Statement st;
        
        try {
            st = objCConexion.estableConnection().createStatement();
            
            ResultSet rs =st.executeQuery(sql);
            
            while (rs.next()) {                
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);
                
            }
            
            paramTablaTotalEstudiantes.setModel(modelo);
            
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error: "+e.toString());
            
        }
    }
    
    public void seleccionarEstudiante(JTable paramTablaEstudiante, JTextField paramid,JTextField paramNombre,JTextField paramApellidos){
        
      
        try {
            int fila = paramTablaEstudiante.getSelectedRow();
            
            if (fila >=0) {
                
                paramid.setText(paramTablaEstudiante.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTablaEstudiante.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaEstudiante.getValueAt(fila, 2).toString());
                               
            }else{
                JOptionPane.showMessageDialog(null,"Fila no seleccionada"); 
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error de seleccion"+e.toString());
            
            
        }
     
        
    }
    
    public void modificarEstudiantes (JTextField paramCodigo,JTextField paramNombre,JTextField paramApellidos){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreEstudiante(paramNombre.getText());
        setApellidosEstudiantes(paramApellidos.getText());
        
        CConexion objetCConexion = new CConexion();
        String consulta ="update alumnos set alumnos.nombre = ?, alumnos.apellido=? where alumnos.id=?; ";
        
        try {
            
            CallableStatement cs = objetCConexion.estableConnection().prepareCall(consulta);
            
            cs.setString(1, getNombreEstudiante());
            cs.setString(2, getApellidosEstudiantes());
            cs.setInt(3, getCodigo());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"modificacion exitosa");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Error de seleccion"+e.toString());
        }
                
       
    }
    
    public void eliminarEstudiante(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objeCConexion =new CConexion();
        
        String consulta ="delete from alumnos where alumnos.id=?;";
        
        try {
            
            CallableStatement cs = objeCConexion.estableConnection().prepareCall(consulta);
            
            cs.setInt(1, getCodigo());
           
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"eliminacion exitosa");
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"Erro:"+e.toString());
            
        }
        
    }
}
