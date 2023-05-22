package ru.mirea.bandurin.employeedb;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = App.getInstance().getDatabase();
        SuperheroDao superheroDao = db.employeeDao();
        Superhero superhero = new Superhero();
        superhero.id = 1;
        superhero.name = "Ironman";
        superhero.power = 500;
        superhero.intellect=999;
        superhero.speed = 400;
        superheroDao.insert(superhero);
        List<Superhero> superheroes = superheroDao.getAll();
        superhero = superheroDao.getById(1);
        superhero.power = 700;
        superheroDao.update(superhero);
        Log.d(TAG, superhero.name + " " + superhero.power + " " + superhero.speed+ " " + superhero.intellect);
    }
}