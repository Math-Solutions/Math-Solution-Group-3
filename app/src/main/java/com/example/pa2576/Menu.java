

        package com.example.pa2576;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
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




    protected void onCreate(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, (android.view.Menu) menu);


       drawerLayout = findViewById(R.id.drawer);
       toolbar= findViewById(R.id.toolbar);
       navigationView = findViewById(R.id.navigationView);

       //setSupportActionBar(toolbar);
       //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
       //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
       drawerLayout.addDrawerListener(toggle);
       toggle.syncState();
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.homepage:
                       startActivity(new Intent(Menu.this, Homepage.class));
                       Toast.makeText(Menu.this, "Homepage selected", Toast.LENGTH_SHORT).show();

                       break;
                   case R.id.profile:
                       Toast.makeText(Menu.this, "Profile selected", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(Menu.this, ProfilePage.class));
                       break;
                   case R.id.info:
                       Toast.makeText(Menu.this, "Info selected", Toast.LENGTH_SHORT).show();
                       //startActivity(new Intent(Menu.this, .class));
                       break;
                   case R.id.pic:
                       Toast.makeText(Menu.this, "My Solutions was selected", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.rates:
                       Toast.makeText(Menu.this, "My ratings was selected", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.logout:
                       Toast.makeText(Menu.this, "Good Bye!", Toast.LENGTH_SHORT).show();
                       break;
               }
               return false;
           }
       });

    }

    @Override
    public  boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.homepage:
                Toast.makeText(this, "Homepage selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.info:
                Toast.makeText(Menu.this, "Info selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic:
                Toast.makeText(Menu.this, "My Solutions was selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rates:
                Toast.makeText(Menu.this, "My ratings was selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(Menu.this, "Good Bye!", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}

