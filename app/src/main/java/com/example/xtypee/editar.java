package com.example.xtypee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class editar extends AppCompatActivity {
    EditText editName, editEmail, editUsername, editPassword;
    Button saveButton;
    String nameUser, emailUser, usernameUser, passwordUser;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        reference = FirebaseDatabase.getInstance().getReference("users");
        editName = findViewById(R.id.editprofilename);
        editEmail = findViewById(R.id.editprofilecorreo);
        editUsername = findViewById(R.id.editprofileuser);
        editPassword = findViewById(R.id.editprofilecontra);
        saveButton = findViewById(R.id.saveBut);
        showData();

        Intent intent = getIntent();
        String nameUser3 = intent.getStringExtra("nombrei");
        String emailUser3 = intent.getStringExtra("correoi");
        String usernameUser3 = intent.getStringExtra("usuarioi");
        String passwordUser3 = intent.getStringExtra("contrasenai");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    intenthome.putExtra("nombrei", nameUser3);
                    intenthome.putExtra("correoi", emailUser3);
                    intenthome.putExtra("usuarioi", usernameUser3);
                    intenthome.putExtra("contrasenai", passwordUser3);  // Agrega los extras que desees enviar
                    startActivity(intenthome);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_propiedades:
                    Intent intentpro = new Intent(getApplicationContext(), propiedades.class);
                    intentpro.putExtra("nombrei", nameUser3);
                    intentpro.putExtra("correoi", emailUser3);
                    intentpro.putExtra("usuarioi", usernameUser3);
                    intentpro.putExtra("contrasenai", passwordUser3);  // Agrega los extras que desees enviar
                    startActivity(intentpro);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_foro:
                    Intent intentforo = new Intent(getApplicationContext(), foro.class);
                    intentforo.putExtra("nombrei", nameUser3);
                    intentforo.putExtra("correoi", emailUser3);
                    intentforo.putExtra("usuarioi", usernameUser3);
                    intentforo.putExtra("contrasenai", passwordUser3);  // Agrega los extras que desees enviar
                    startActivity(intentforo);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    Intent intentperfil = new Intent(getApplicationContext(), perfil.class);
                    intentperfil.putExtra("nombrei", nameUser3);
                    intentperfil.putExtra("correoi", emailUser3);
                    intentperfil.putExtra("usuarioi", usernameUser3);
                    intentperfil.putExtra("contrasenai", passwordUser3);  // Agrega los extras que desees enviar
                    startActivity(intentperfil);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_about:
                    Intent intentabout = new Intent(getApplicationContext(), about.class);
                    intentabout.putExtra("nombrei", nameUser3);
                    intentabout.putExtra("correoi", emailUser3);
                    intentabout.putExtra("usuarioi", usernameUser3);
                    intentabout.putExtra("contrasenai", passwordUser3);  // Agrega los extras que desees enviar
                    startActivity(intentabout);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                /*case R.id.bottom_logout:
                    startActivity(new Intent(getApplicationContext(), perfil.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;*/
            }
            return false;
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isPasswordChanged() || isEmailChanged()){
                    Toast.makeText(editar.this, "Guardados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(editar.this, "Sin cambios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isNameChanged() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(usernameUser).child("nombrei").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(usernameUser).child("correoi").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(usernameUser).child("contrasenai").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();
        nameUser = intent.getStringExtra("nombrei");
        emailUser = intent.getStringExtra("correoi");
        usernameUser = intent.getStringExtra("usuarioi");
        passwordUser = intent.getStringExtra("contrasenai");
        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }
}
