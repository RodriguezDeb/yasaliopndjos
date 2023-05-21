package com.example.xtypee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registro extends AppCompatActivity {
    EditText signupName, signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton, button5, pick;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        signupName = findViewById(R.id.nombre);
        signupEmail = findViewById(R.id.email);
        signupUsername = findViewById(R.id.usuario);
        signupPassword = findViewById(R.id.pswd);
        pick = findViewById(R.id.pick);
        signupButton = findViewById(R.id.button4);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(registro.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(registro.this, login.class);
                startActivity(intent);
            }
        });
    }
}