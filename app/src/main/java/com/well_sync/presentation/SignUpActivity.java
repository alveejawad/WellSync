package com.well_sync.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.well_sync.R;
import com.well_sync.logic.UserAuthenticationHandler;
import com.well_sync.objects.UserCredentials;

public class SignUpActivity extends AppCompatActivity {
    private UserCredentials newUser;
    private UserAuthenticationHandler signUpHandler;
    TextView nextButton;
    EditText userFirstName;
    EditText userLastName;
    EditText userEmail;
    EditText userPassword;
    EditText userConfirmPassword;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_signup);
        //Logic Layer Handler
        signUpHandler = new UserAuthenticationHandler();
        //User data
        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
        userEmail = findViewById(R.id.editEmail);
        userPassword = findViewById(R.id.editPassword);
        userConfirmPassword = findViewById(R.id.editConfirmPassword);
        //next Button
        TextView nextButton = findViewById(R.id.Nextbutton);

        //NEXT BUTTON
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //get input from user
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String confirmPassword = userConfirmPassword.getText().toString();

                //If any field is empty
                if (firstName.isEmpty() || lastName.isEmpty()) {
                    userFirstName.setError("Name can't be blank");
                    userFirstName.requestFocus();
                    userLastName.setError("Name can't be blank");
                    userLastName.requestFocus();
                } else if (email.isEmpty()) {
                    userEmail.setError("Enter your email");
                    userEmail.requestFocus();
                } else if (password.isEmpty()) {
                    userPassword.setError("Enter your password");
                    userPassword.requestFocus();
                } else if (confirmPassword.isEmpty()) {
                    userConfirmPassword.setError("Confirm your password");
                    userConfirmPassword.requestFocus();
                }else if(!password.equals(confirmPassword)) {
                //if passwords don't match
                    userPassword.setError("Passwords don't match. Try again");
                    userPassword.requestFocus();
                    resetPasswords();
                }else{
                    //create user credentials
                    newUser = new UserCredentials(email,password);
                    boolean registered= signUpHandler.register(newUser);
                    //if fields were valid
                    if (registered){
                        startActivity(new Intent(SignUpActivity.this, UserDetailsActivity.class));
                    }else{
                        resetSignUpFields();
                        Toast.makeText(getApplicationContext(), "Not Registered. Invalid values, try again" , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }
    /**
     Function: reset the password entry fields in case passwords don't match
     */
    private void resetPasswords(){
        EditText Password = (EditText) findViewById(R.id.editPassword);
        EditText ConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        Password.setText("");
        ConfirmPassword.setText("");
    }

    /**
    Function: reset all of the data entry fields in case registration fails
    */
    private void resetSignUpFields(){
        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
        userEmail = findViewById(R.id.editEmail);
        userFirstName.setText("");
        userLastName.setText("");
        userEmail.setText("");
        resetPasswords();

    }
}