package com.example.mensajeria;

import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.Statement;

public class Ingreso extends AsyncTask<Usuario, Void, Usuario> {

    /**
     * Metodo que realiza la validacion del usuario en la base de datos
     * @param datos
     * @return
     */
    protected Usuario doInBackground(Usuario ... datos){
        Usuario user = new Usuario();
        try{
            Conexion.conexion = Conexion.conectar();
            String sql = "select nickname, password, estado from mensajeria.usuario where nickname = '"+datos[0].getNickname()+"' and password = '"+datos[0].getPassword()+"'";
            Statement st = Conexion.conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                rs.beforeFirst();
                while(rs.next()){
                    user.setNickname(rs.getString("nickname"));
                    user.setPassword(rs.getString("password"));
                    user.setIdEstado(rs.getInt("estado"));
                }
            } else{
                user = null;
            }
        } catch(Exception ex){
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

}
