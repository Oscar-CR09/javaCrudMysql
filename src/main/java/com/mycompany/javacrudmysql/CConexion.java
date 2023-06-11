/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javacrudmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Oscar CR
 */
public class CConexion {
    
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "1989cero";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableConnection(){
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            
            //JOptionPane.showMessageDialog(null,"Se realizo la conexion");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectar a la base de datos error:"+e.toString());
        }
        
        return conectar;
        
    }
    
}
