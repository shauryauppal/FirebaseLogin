package com.example.shaur.firebaselogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button;
    private FirebaseAuth mAuth;
    private AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.email_register);
        password = (EditText) findViewById(R.id.password_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onRegister(View view) {
        final String myEmail = email.getText().toString();
        final String myPass = password.getText().toString();

        if (checkvalidation(myEmail, myPass)) {
            mAuth.createUserWithEmailAndPassword(myEmail,myPass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                               Log.i("TAG","createuserwithEmail:success");
                                Toast.makeText(signup.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Log.i("TAG","createuserwithEmail:failure");
                                Toast.makeText(signup.this, "FAILURE", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }
    }


    private boolean checkvalidation(String myEmail,String myPass)
    {
        if(myEmail!=null && !myEmail.isEmpty() && myPass!=null && !myPass.isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(signup.this, "Please check the Input", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
