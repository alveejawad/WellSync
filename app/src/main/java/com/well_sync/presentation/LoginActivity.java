package com.well_sync.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import com.well_sync.logic.exceptions.InvalidCredentialsException;
import com.well_sync.objects.UserCredentials;
import com.well_sync.logic.UserAuthenticationHandler;
import com.well_sync.persistence.utils.DBHelper;

import android.graphics.Paint;


public class LoginActivity extends AppCompatActivity {

    private UserCredentials userCredentials;
    private UserAuthenticationHandler loginHandler;
    TextView loginButton, signUpButton;
    EditText userPassword, userEmail;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper.copyDatabaseToDevice(this);

        loginHandler = new UserAuthenticationHandler();
        loginButton = findViewById(R.id.Loginbutton);
        signUpButton = findViewById(R.id.SignUp);
        userEmail = findViewById(R.id.editEmail);
        userPassword = findViewById(R.id.editPassword);

        stylizeSignUpLink();


        //LOG IN BUTTON
        loginButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                userCredentials=getCredentials(v);

                try {
                    loginHandler.login(userCredentials);
                        Intent openHome=new Intent(LoginActivity.this,HomePageActivity.class);
                        openHome.putExtra("email", userCredentials.getEmail());
                        startActivity(openHome);
                } catch (InvalidCredentialsException e) {
                    Toast.makeText(getApplicationContext(), "The email you entered isn’t connected to an account" , Toast.LENGTH_SHORT).show();
                    resetLoginFields();
                    userPassword.setError("Email or Password invalid");
                    userEmail.setError("Email or Password invalid");
                }
            }
        });

        //SIGN UP BUTTON
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

    }
    private void stylizeSignUpLink() {
        TextView signUpLink = findViewById(R.id.SignUp);
        signUpLink.setPaintFlags(signUpLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public UserCredentials getCredentials(View view) {
        UserCredentials returningUser;
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        returningUser = new UserCredentials(email,password);
        return returningUser;
    }

    private void resetLoginFields(){
        EditText usernameInput = (EditText) findViewById(R.id.editEmail);
        EditText passwordInput = (EditText) findViewById(R.id.editPassword);
        usernameInput.setText("");
        passwordInput.setText("");
    }


}
