package io.agora.openlive;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class scan extends AppCompatActivity{

    TextView firstNameS,lastNameS;
    FirebaseAuth fAuth;
    String userID;
    FirebaseFirestore fStore;

    Button qrbtn;
    Button btnT,btnF;


    public static TextView qrtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        qrbtn = findViewById(R.id.qrbtn);

        firstNameS = findViewById(R.id.firstNameS);
        lastNameS = findViewById(R.id.lastNameS);

        btnT = findViewById(R.id.btnT);
        btnF = findViewById(R.id.btnF);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();







        DocumentReference documentReference = fStore.collection("cleaners").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                firstNameS.setText(value.getString("first_name"));
//                lastNameS.setText(value.getString("last_name"));
            }
        });
        qrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),qrscanner.class));
            }
        });

        Button btnTrue = (Button) findViewById(R.id.btnT);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Toast.makeText(getBaseContext(), "Scan Time in Complete" , Toast.LENGTH_SHORT ).show();
                insertimein();

            }
        });
        Button btnFalse = (Button) findViewById(R.id.btnF);
        btnFalse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Toast.makeText(getBaseContext(), "Scan Time out Complete" , Toast.LENGTH_SHORT ).show();
                insertimeout();

            }

        });

        Button btnUnmatched = (Button) findViewById(R.id.unmatch);
        btnUnmatched.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Toast.makeText(getBaseContext(), "QR Code unidentified" , Toast.LENGTH_SHORT ).show();
            }
        });




    }

    private void insertimeout() {

        Calendar calendar = Calendar.getInstance();
        Timestamp currentDate = Timestamp.now();

        fStore.collection("qr_log").document("0nqVMZ32jztnmEuIMPNZ")
                .update("scan_time_out",currentDate);
    }

    private void insertimein() {

        Calendar calendar = Calendar.getInstance();
        Timestamp currentDate = Timestamp.now();

        fStore.collection("qr_log").document("0nqVMZ32jztnmEuIMPNZ")
                .update("scan_time_in",currentDate);

    }
}

