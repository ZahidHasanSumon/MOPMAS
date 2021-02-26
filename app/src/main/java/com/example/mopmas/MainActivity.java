package com.example.mopmas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nav;
    private FrameLayout frame;

    private UserHomeFragment home;
    private UserHealthFragment health;
    private UserReminderFragment progress;
    private UserProfileFragment profile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav=findViewById(R.id.bottom2);
        frame=findViewById(R.id.fr3);

        home=new UserHomeFragment();
        health=new UserHealthFragment();
        progress=new UserReminderFragment();
        profile=new UserProfileFragment();

        nav.setSelectedItemId(R.id.nav_home);
        setFragment(home);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home :
                        setFragment(home);

                        return true;

                    case R.id.nav_health:

                        setFragment(health);
                        return true;

                    case R.id.nav_progress:


                        setFragment(progress);
                        return true;

                    case R.id.nav_me:
                        setFragment(profile);
                        return true;

                    default:
                        return false;


                }
            }
        });
    }


    private void setFragment(Fragment fragment){
        FragmentTransaction frag=getSupportFragmentManager().beginTransaction();
        frag.replace(R.id.fr3,fragment);
        frag.commit();
    }


}
