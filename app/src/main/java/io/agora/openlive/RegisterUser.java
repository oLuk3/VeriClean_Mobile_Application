package io.agora.openlive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mlastName;
    Button mregisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mlastName = findViewById(R.id.lastName);
        mFullName = findViewById(R.id.fullNamed);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mregisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.gotologin);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //check user if login
        //if (fAuth.getCurrentUser() != null) {
           // startActivity(new Intent(getApplicationContext(), dashbaord.class));
            //finish();
       // }

        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String fullName = mFullName.getText().toString();
                String lastName = mlastName.getText().toString();

                if (TextUtils.isEmpty(lastName)) {
                    mlastName.setError("lastName is required!");
                    return;
                }

                if (TextUtils.isEmpty(lastName)) {
                    mFullName.setError("First Name is required!");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required!");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required!");
                    return;
                }
                if (password.length() < 6) {
                    mPassword.setError("Min Password length is 6");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user in firestore firebase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterUser.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("cleaners").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("first_name",fullName);
                            user.put("last_name",lastName);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user profile is created for" +userID );
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), dashbaord.class));

                        } else {
                            Toast.makeText(RegisterUser.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });



            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });
    }
}

