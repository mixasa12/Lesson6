package ru.mirea.bandurin.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.mirea.bandurin.lesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String group;
    private int num;
    private String film;
    private EditText e_group;
    private EditText e_num;
    private EditText e_film;
    private Button b_sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        e_group = binding.group;
        e_num = binding.num;
        e_film = binding.film;
        b_sub = binding.sub;
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        group = sharedPref.getString("GROUP", "unknown");
        film = sharedPref.getString("FILM", "unknown");
        num = sharedPref.getInt("NUM", 0);
        e_group.setText(group);
        e_num.setText(String.valueOf(num));
        e_film.setText(film);
        b_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("GROUP", e_group.getText().toString());
                editor.putInt("NUM", Integer.valueOf(e_num.getText().toString()));
                editor.putString("FILM", e_film.getText().toString());
                editor.apply();
            }
        });

    }
}