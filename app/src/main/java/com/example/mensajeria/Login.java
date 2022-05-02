package com.example.mensajeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
private EditText txtUsuario;
private EditText txtPassword;
private Button btnIngresar;
private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.txtUsuario= findViewById(R.id.txtUsuario);
        this.txtPassword= findViewById(R.id.txtPassword);
        this.btnIngresar=findViewById((R.id.btnIngresar));
        this.btnRegistrar=findViewById((R.id.btnRegistrar));

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Usuario userRequest = new Usuario();
                    userRequest.setNickname(txtUsuario.getText().toString());
                    userRequest.setPassword(txtPassword.getText().toString());
                    Usuario userResponse = new Ingreso().execute(userRequest).get();
                    if(userResponse != null){
                        if(userResponse.getIdEstado() == 0){
                            Toast.makeText(getApplicationContext(), "Usuario Inactivo", Toast.LENGTH_SHORT).show();
                        } else{
                            Intent navegacion = new Intent(Login.this, Menu.class);
                            startActivity(navegacion);
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }

        });

    }
}