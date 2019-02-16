package byone4all.connected.social.socialcircle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);
        int n=0;
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //permission
        int permissioncheck0 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        int permissioncheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissioncheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);


            if (permissioncheck0 == PackageManager.PERMISSION_GRANTED) {



            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
                if (permissioncheck0 == PackageManager.PERMISSION_GRANTED) {
                    if (permissioncheck1 == PackageManager.PERMISSION_GRANTED) {

                    }
                    else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 3);





                    }

                }






            }










        findViewById(R.id.cv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, My_contacts.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.cv1s).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, Survey_Local.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.cv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this,heatmap.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.cv1d).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, nearby.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.cv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, report.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.cvw3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, chatbot.class);
                startActivity(intent);
            }
        });





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.MC) {Intent d = new Intent(home2.this,My_contacts.class);
            startActivity(d);
            // Handle the camera action
        } else if (id == R.id.NH) {Intent d = new Intent(home2.this,nearby.class);
            startActivity(d);

        } else if (id == R.id.CS) {Intent d = new Intent(home2.this,Survey_Local.class);
            startActivity(d);

        } else if (id == R.id.RO) {Intent d = new Intent(home2.this,report.class);
            startActivity(d);

        }
        else if (id == R.id.w4) {Intent d = new Intent(home2.this,education.class);
            startActivity(d);

        }
        else if (id == R.id.w3) {

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.co.in/maps/search/police"));
            startActivity(intent);

        } else if (id == R.id.LO) {
            FirebaseAuth.getInstance().signOut();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            if(currentUser==null){

                Intent k = new Intent(home2.this, Intro.class );
                startActivity(k);
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){

            Intent k = new Intent(home2.this, Intro.class );
            startActivity(k);
            finish();
        }



}}

