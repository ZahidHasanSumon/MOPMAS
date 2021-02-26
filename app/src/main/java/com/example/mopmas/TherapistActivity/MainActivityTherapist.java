package com.example.mopmas.TherapistActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.mopmas.R;
import com.example.mopmas.UserHealthFragment;
import com.example.mopmas.UserHomeFragment;
import com.example.mopmas.UserProfileFragment;
import com.example.mopmas.UserReminderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivityTherapist extends AppCompatActivity {
    private BottomNavigationView nav;
    private FrameLayout frame;

    private HomeFragment home;
    private ProgressFragment progress;
    private AddPatientFragment add;
    private ProfileFragment profile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_therapist);

        nav=findViewById(R.id.bottom2);
        frame=findViewById(R.id.fr3);

        home=new HomeFragment();
        progress=new ProgressFragment();
        add=new AddPatientFragment();
        profile=new ProfileFragment();

        nav.setSelectedItemId(R.id.nav_home);
        setFragment(home);


        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home :
                        setFragment(home);

                        return true;

                    case R.id.nav_progressp:
                        setFragment(progress);
                        return true;

                    case R.id.nav_add:
                        setFragment(add);
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
