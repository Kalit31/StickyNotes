package com.example.stickynotes;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.db.entity.Note;
import com.example.stickynotes.fragments.Add_Note;
import com.example.stickynotes.fragments.View_Note;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Activity context;
    public NotesAppDatabase notesAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesAppDatabase = Room.databaseBuilder(getApplicationContext(),NotesAppDatabase.class,"NotesDB").allowMainThreadQueries().build();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new View_Note());
        ft.commit();
        drawerLayout=findViewById(R.id.drawer);
        ImageView hamburger=findViewById(R.id.hamburger);

        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView=findViewById(R.id.nav_view);
        context=this;
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {

        drawerLayout.closeDrawer(GravityCompat.START);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(menuItem.getItemId())
        {
            case R.id.view_note:
                Toast.makeText(context,"View Notes", Toast.LENGTH_SHORT).show();
                ft.replace(R.id.your_placeholder, new View_Note());
                ft.commit();
                break;
            case R.id.add_note:
                Toast.makeText(context,"Add Notes",Toast.LENGTH_SHORT).show();
                ft.replace(R.id.your_placeholder, new Add_Note());
                ft.commit();
                break;
        }
        return true;
    }
}
