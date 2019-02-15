package byone4all.connected.social.socialcircle;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.ServerTimestamp;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class report extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button mbut,muplod;
    TextView t5;
    private EditText e1, e10;
    int j=1;
    public static final  int GALARY_INTENT =2;
    String ph9;
    int o=1;

    String type1;
    public StorageReference mstore;
ImageView ik;


    public FirebaseFirestore mfire;
    Spinner spinner;

    private static final int REQUEST_LOCATION = 1;

    String lattitude,longitude;
    LocationManager locationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mfire = FirebaseFirestore.getInstance();
        mstore = FirebaseStorage.getInstance().getReference();
        mbut = findViewById(R.id.button2);
        spinner = findViewById(R.id.spinnerq);
        String [] type ={"Public Places","Work Place ", "College / School","Domestic Violence "};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(report.this ,android.R.layout.simple_spinner_dropdown_item,type);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
        ActivityCompat.requestPermissions
                (this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);



        ph9 =FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

        e1 = findViewById(R.id.klp);
        e10 = findViewById(R.id.editText1);
        t5= findViewById(R.id.textView2);
        ik = findViewById(R.id.imageView890);
        ik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r = new Intent(Intent.ACTION_PICK);
                r.setType("image/*");
                startActivityForResult(r, GALARY_INTENT);


            }
        });


        mbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


    String name1 = e1.getText().toString();
    String Comp = e10.getText().toString();



    String ph_num = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber().trim();
    Map<String, String> userMap = new HashMap<>();



    userMap.put("Name", Comp);
    userMap.put("Complain", name1);



    userMap.put("Type", type1);
    userMap.put("Lati", lattitude);
    userMap.put("Longi", longitude);
    userMap.put("ph_num", ph_num);

    mbut.setText("UPLOADING ONLINE ...");



    mfire.collection("Complaints").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            mbut.setText("uploaded successfully");


        }
    });




            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        t5.setText("Uploading...Please Wait");


        if(requestCode==GALARY_INTENT && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            final int[] i = {0};

            if (i[0] ==0)
            {

                StorageReference filepath  = mstore.child(ph9+"/"+ j);
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        t5.setText("Uploading Complete");
                     j++;

                    }
                });
            }


        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        type1 = text ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(report.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (report.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(report.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);



            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);




            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);



            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }
}
