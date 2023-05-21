package com.example.xtypee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class propiedades extends AppCompatActivity {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<ListData> dataList;
    ListAdapter adapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedades);

        Intent intent = getIntent();
        String nameUser2 = intent.getStringExtra("nombrei");
        String emailUser = intent.getStringExtra("correoi");
        String usernameUser = intent.getStringExtra("usuarioi");
        String passwordUser = intent.getStringExtra("contrasenai");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_propiedades);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    intenthome.putExtra("nombrei", nameUser2);
                    intenthome.putExtra("correoi", emailUser);
                    intenthome.putExtra("usuarioi", usernameUser);
                    intenthome.putExtra("contrasenai", passwordUser);  // Agrega los extras que desees enviar
                    startActivity(intenthome);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_propiedades:
                    return true;
                case R.id.bottom_foro:
                    Intent intentforo = new Intent(getApplicationContext(), foro.class);
                    intentforo.putExtra("nombrei", nameUser2);
                    intentforo.putExtra("correoi", emailUser);
                    intentforo.putExtra("usuarioi", usernameUser);
                    intentforo.putExtra("contrasenai", passwordUser);  // Agrega los extras que desees enviar
                    startActivity(intentforo);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    Intent intentperfil = new Intent(getApplicationContext(), perfil.class);
                    intentperfil.putExtra("nombrei", nameUser2);
                    intentperfil.putExtra("correoi", emailUser);
                    intentperfil.putExtra("usuarioi", usernameUser);
                    intentperfil.putExtra("contrasenai", passwordUser);  // Agrega los extras que desees enviar
                    startActivity(intentperfil);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_about:
                    Intent intentabout = new Intent(getApplicationContext(), about.class);
                    intentabout.putExtra("nombrei", nameUser2);
                    intentabout.putExtra("correoi", emailUser);
                    intentabout.putExtra("usuarioi", usernameUser);
                    intentabout.putExtra("contrasenai", passwordUser);  // Agrega los extras que desees enviar
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

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(propiedades.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(propiedades.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        dataList = new ArrayList<>();
        adapter = new ListAdapter(propiedades.this, dataList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    ListData dataClass = itemSnapshot.getValue(ListData.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(propiedades.this, UploadActivity.class);
                startActivity(intent);
            }
        });
    }
    public void searchList(String text){
        ArrayList<ListData> searchList = new ArrayList<>();
        for (ListData dataClass: dataList){
            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}
