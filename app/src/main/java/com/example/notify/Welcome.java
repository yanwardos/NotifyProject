package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.example.notify.data.model.LoggedInUser;
import com.example.notify.ui.login.NotifySignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    private static final String TAG = "Welcome";
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser == null){
            //Jika tidak ada user, lempar ke activity login
            startActivity(new Intent(this, NotifySignIn.class));
            finish();
            return;
        }else {
            //jika ada user, lempar ke Main
            Toast.makeText(this, "Ada User"+mFirebaseUser.getDisplayName(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

    }


}
