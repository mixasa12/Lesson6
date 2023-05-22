package ru.mirea.bandurin.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;

import ru.mirea.bandurin.internalfilestorage.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EditText edt;
    private Button but;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";
    private String date_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        edt=binding.editTextTextPersonName;
        but=binding.button;
        but.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       date_history=edt.getText().toString();
                                       FileOutputStream outputStream;
                                       try {
                                           outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                                           outputStream.write(date_history.getBytes());
                                           outputStream.close();
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }}
                               }
        );
    }
}