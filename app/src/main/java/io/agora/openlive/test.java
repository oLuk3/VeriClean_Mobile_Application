package io.agora.openlive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class test extends AppCompatActivity {

    private static final String TAG = "test";
    TextView phone;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        phone = findViewById(R.id.phone);




        DocumentReference documentReference = db.collection("feedback").document("Dg2fTgBk3Y7mp3t0155l");

        // adding snapshot listener to our document reference.
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                // inside the on event method.
                if (error != null) {
                    // this method is called when error is not null
                    // and we gt any error
                    // in this cas we are displaying an error message.
                    Toast.makeText(test.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null && value.exists()) {
                    // if th value from firestore is not null then we are getting
                    // our data and setting that data to our text view.
                    phone.setText(value.getData().get("body").toString());
                }
            }
        });

       // FirebaseFirestore.getInstance().collection("feedback").document("Data")
                //.addSnapshotListener(new EventListener<QuerySnapshot>() {
                   // @Override
                  //  public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                     //   if(error != null){
                           // return;
                     //   }

                       // if(queryDocumentSnapshots != null){
                          //  List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                          //  for(DocumentSnapshot snapshot : snapshotList) {
                             //   phone.setText(DocumentSnapshot.getData.get("phone").toString());
                          //  }

                      //  }else {

                      //  }
                  //  }
               // });



    }
}
