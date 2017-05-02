package com.mmadapps.firebaseexample.matches;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mmadapps.firebaseexample.R;
import com.mmadapps.firebaseexample.news.CricketNewsFragment;

import static com.mmadapps.firebaseexample.R.id.f_content;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    CricketMatchFragment cricketMatchFragment=new CricketMatchFragment();
                    getSupportFragmentManager().beginTransaction().replace(f_content,cricketMatchFragment).commit();
                    return true;
                case R.id.navigation_news:
                    CricketNewsFragment cricketNewsFragment=new CricketNewsFragment();
                    getSupportFragmentManager().beginTransaction().replace(f_content,cricketNewsFragment).commit();
                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CricketMatchFragment cricketMatchFragment=new CricketMatchFragment();
        getSupportFragmentManager().beginTransaction().replace(f_content,cricketMatchFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
