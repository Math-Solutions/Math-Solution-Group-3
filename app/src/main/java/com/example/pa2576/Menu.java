

        package com.example.pa2576;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.Toast;

        import androidx.appcompat.widget.Toolbar;

        import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

  DrawerLayout drawerLayout;
  Toolbar toolbar;
  NavigationView navigationView;
  ActionBarDrawerToggle toggle;




    public void onCreate(final Context context, Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, (android.view.Menu) menu);
/*
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); */

       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()) {
                   case R.id.homepage:
                       Toast.makeText(context, "Homepage selected", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(context, Homepage.class));
                       break;
                   case R.id.profile:
                       Toast.makeText(context, "Profile selected", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(context, ProfilePage.class));
                       break;
                   case R.id.info:
                       Toast.makeText(context, "Info selected", Toast.LENGTH_SHORT).show();
                       //startActivity(new Intent(Menu.this, .class));
                       break;
                   case R.id.pic:
                       Toast.makeText(context, "My Solutions was selected", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rates:
                       Toast.makeText(context, "My ratings was selected", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.logout:
                       Toast.makeText(context, "Good Bye!", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(context, MainActivity.class));
                       break;

                   default:
                       return Menu.super.onOptionsItemSelected(menuItem);
               }
               return true;
           }
       });
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.homepage:
                startActivity(new Intent(this, Homepage.class));
                Toast.makeText(this, "Homepage selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                startActivity(new Intent(this, ProfilePage.class));
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.info:

                Toast.makeText(this, "Info selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.pic:
                startActivity(new Intent(this, Homepage.class));
                Toast.makeText(this, "My Solutions was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.rates:
                startActivity(new Intent(this, Homepage.class));
                Toast.makeText(this, "My ratings was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "Good Bye!", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}

