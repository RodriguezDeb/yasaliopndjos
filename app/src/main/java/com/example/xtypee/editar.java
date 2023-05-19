package com.example.xtypee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
