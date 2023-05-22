package ru.mirea.bandurin.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

import ru.mirea.bandurin.securesharedpreferences.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TextView tv;
    private ImageView iv;
    private String poet;
    private String portret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tv=binding.poet;
        iv=binding.portret;
        KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
        String mainKeyAlias = null;
        try {
            mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SharedPreferences secureSharedPreferences = null;
        try {
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("poet", "Пушкин").apply();
            secureSharedPreferences.edit().putString("portret", "pushkin").apply();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        poet = secureSharedPreferences.getString("poet", "неизвестно");
        portret = secureSharedPreferences.getString("portret", "неизвестно");
        tv.setText(poet);
        int id = getResources().getIdentifier(portret, "drawable", getPackageName());
        iv.setImageResource(id);
    }
}