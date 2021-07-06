package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userRegActivity extends AppCompatActivity {

    EditText emailaddress,password1, confirmpassword1;
    TextView loginpage;
    private FirebaseAuth mAuth;
    private Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        //take text from design
        emailaddress=findViewById(R.id.regemailaddress1);
        password1=findViewById(R.id.regpassword1);
        confirmpassword1=findViewById(R.id.regcpassword1);
        //take text from design

        loginpage = findViewById(R.id.teew5);


        //loading dialog
        loadingDialog = new Dialog(userRegActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //loading dialog



        loginpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userRegActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        });


        //check for empty email or password
        if(emailaddress.getText().toString().isEmpty()){
            emailaddress.setError("Enter email");
            return;
        }
        if(password1.getText().toString().isEmpty()){
            password1.setError("Enter email");
            return;
        }
        if(confirmpassword1.getText().toString().isEmpty()){
            confirmpassword1.setError("Enter email");
            return;
        }
        //check for empty email or password


        //validate email
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailaddress.getText().toString());


        if (!matcher.matches()) {
            Toast.makeText(userRegActivity.this,"Invalid email",Toast.LENGTH_SHORT).show();
            return;
        }
        //validate email



        //login
        if(password1.getText().equals(confirmpassword1.getText())){
            registerNewUser();
        }
        else{
            // password and confirm password do not match
            confirmpassword1.setError("Passwords do not match...");
            return;
        }


    }









    //create user
    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
       loadingDialog.show();

        // Take the value of two edit texts in Strings
        //String email, password;
        //email = emailTextView.getText().toString();
        //password = passwordTextView.getText().toString();

        // Validations for input email and password

        emailaddress=findViewById(R.id.regemailaddress1);
        password1=findViewById(R.id.regpassword1);

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(emailaddress.getText().toString(), password1.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            // hide the progress bar
                            //progressBar.setVisibility(View.GONE);

                            loadingDialog.dismiss();
                            // if the user created intent to login activity
                            Intent intent = new Intent(userRegActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {

                            loadingDialog.dismiss();
                            // Registration failed
                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later",
                                    Toast.LENGTH_LONG).show();

                            // hide the progress bar
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        loadingDialog.dismiss();
    }
    //create user


}