package com.android.project1.fsth.pythonetutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class SigninActivity extends AppCompatActivity {
      FirebaseAuth auth;
      shared shared;
      WebView hiddenwv;
    GoogleSignInClient mGoogleSignInClient;
   // GoogleSignInClient getmGoogleSignInClientj;
    ProgressDialog progressDialog;
    SignInButton signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shared=new shared(this);
      /*  if(shared.loadnightmode()==true){setTheme(R.style.darkTheme);}else{setTheme(R.style.AppTheme);}
      */
        shared.LoadMode();//Dark Mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        FirebaseApp.initializeApp(getApplicationContext());
       auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
       //  Configure Google Sign In
        /*if(auth.getCurrentUser()!=null) {
            startActivity(new Intent(this,CommentsIdea.class));
        }
       */
        final  ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
      final   NetworkInfo ni=cm.getActiveNetworkInfo();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(ni!=null && ni.isConnected())
                {

                }
else{Toast.makeText(getApplicationContext(),"Not Connected",Toast.LENGTH_SHORT).show();
}
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.newdeafulit_client_id)).requestEmail().build();
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso);

        signInButton=findViewById(R.id.googlesignin);

        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mGoogleSignInClient= GoogleSignIn.getClient(SigninActivity.this,gso);
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);

    //Toast.makeText(getApplicationContext(),"Way of activity result",Toast.LENGTH_LONG).show();
                progressDialog.setMessage("fetching");
                progressDialog.show();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {

                GoogleSignInAccount account = task.getResult(Exception.class);
//                Toast.makeText(getApplicationContext(),"SUPER got exception",Toast.LENGTH_SHORT).show();
                firebaseAuthWithGoogle(account);


            } catch (Exception e) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account ) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
               /*             user.sendEmailVerification();
                            if(user.isEmailVerified()){
                                Toast.makeText(getApplicationContext(),"okay thanks bye bye",Toast.LENGTH_SHORT).show();
               TAG:  Something New           }
                 */
               Toast.makeText(getApplicationContext(),"done partial",Toast.LENGTH_SHORT).show();
               //this is for quiz section verification
                   //  String booleantry=getIntent().getStringExtra("yes").toString();
                            if(getIntent().getStringExtra("yes")!=null){
                                Toast.makeText(SigninActivity.this, "hey QUIZ", Toast.LENGTH_SHORT).show();
    Toast.makeText(getApplicationContext(),"getintent",Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(SigninActivity.this,Quiz.class));
                        finish();
                     return;}
                    else {
                                Intent i = new Intent(SigninActivity.this, CommentsIdea.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"couldnt done",Toast.LENGTH_LONG).show();


                        }

                        // ...
                    }
                });


   }

    @Override
    public void onBackPressed() {
     if(getIntent().getStringExtra("yes")!=null){
  /*      Intent intent=new Intent();
         setResult(777,intent);
  */ startActivity(new Intent(SigninActivity.this,Main4Activity.class));
         finish();
     }
      else{  super.onBackPressed();
    }}
}


