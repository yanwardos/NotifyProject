package com.example.notify.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notify.MainActivity;
import com.example.notify.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class NotifySignIn extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "Notify Sign In";

    //Google
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth mfirebaseAuth;

    private SignInButton signInButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_sign_in);

        signInButton = (SignInButton) findViewById(R.id.google_signin_button);
        signInButton.setOnClickListener(this);

        GoogleSignInOptions opsiSignIn = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, opsiSignIn)
                .build();

        mfirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.google_signin_button:
                signIn();
                break;
            default:
                return;
        }
    }

    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(signInResult.isSuccess()){
                GoogleSignInAccount account = signInResult.getSignInAccount();
                otentikasiFirebase(account);
            }else{
                Toast.makeText(this, "Gagal Sign-in", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void otentikasiFirebase(GoogleSignInAccount account) {
        AuthCredential kredensi = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mfirebaseAuth.signInWithCredential(kredensi)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onComplete: " + task.isSuccessful());

                        if (task.isSuccessful()){
                            Toast.makeText(NotifySignIn.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NotifySignIn.this, MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(NotifySignIn.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // TODO Bikin progressBar
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: "+connectionResult);
        Toast.makeText(this, "Google Services Error.", Toast.LENGTH_SHORT).show();
    }
}
