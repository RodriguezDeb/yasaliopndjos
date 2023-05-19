package com.example.xtypee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class foro extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foro);

        webView = findViewById(R.id.webview);

        // Habilitar JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Configurar WebViewClient para que se cargue la URL dentro del WebView
        webView.setWebViewClient(new WebViewClient());

        // Cargar una URL específica
        webView.loadUrl("https://ananoemimgg.discussion.community/?authtoken=24dfd2cb32ab923638927492b7598ec1&rememberMe=1");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_foro);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_propiedades:
                    startActivity(new Intent(getApplicationContext(), propiedades.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_foro:
                    return true;
                case R.id.bottom_profile:
                    startActivity(new Intent(getApplicationContext(), perfil.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_about:
                    startActivity(new Intent(getApplicationContext(), about.class));
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

    }
}