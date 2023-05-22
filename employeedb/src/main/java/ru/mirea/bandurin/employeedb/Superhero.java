package ru.mirea.bandurin.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Superhero {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public int power;
    public int speed;
    public int intellect;
}
