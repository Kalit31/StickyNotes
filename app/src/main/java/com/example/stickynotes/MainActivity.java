package com.example.stickynotes;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
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

import com.example.stickynotes.databinding.ActivityMainBinding;
import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.fragments.AddNote;
import com.example.stickynotes.fragments.ViewNote;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Activity context;
    ImageView hamburger;
    public NotesAppDatabase notesAppDatabase;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesAppDatabase = Room.databaseBuilder(getApplicationContext(),NotesAppDatabase.class,"NotesDB").allowMainThreadQueries().build();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new ViewNote());
        ft.commit();
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        drawerLayout = activityMainBinding.drawer;

        hamburger = activityMainBinding.contentMain.hamburger;

        navigationView=activityMainBinding.navView;
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
                ft.replace(R.id.your_placeholder, new ViewNote());
                ft.commit();
                break;
            case R.id.add_note:
                Toast.makeText(context,"Add Notes",Toast.LENGTH_SHORT).show();
                ft.replace(R.id.your_placeholder, new AddNote());
                ft.commit();
                break;
        }
        return true;
    }
    public class HamburgerClickHandler{
        Context context;

        public HamburgerClickHandler(Context context) {
            this.context = context;
        }

        public void HamClicked(View view){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
