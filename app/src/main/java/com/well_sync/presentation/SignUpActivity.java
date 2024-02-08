package com.well_sync.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

        signUpHandler = new UserAuthenticationHandler();
        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
        userEmail = findViewById(R.id.editEmail);
        userPassword = findViewById(R.id.editPassword);
        userConfirmPassword = findViewById(R.id.editConfirmPassword);
        TextView nextButton = findViewById(R.id.Nextbutton);

        //NEXT BUTTON
        nextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String confirmPassword = userConfirmPassword.getText().toString();


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
                    userPassword.setError("Passwords don't match. Try again");
                    userPassword.requestFocus();
                    resetPasswords();
                }else{
                    newUser = new UserCredentials(email,password);
                    signUpHandler.register(newUser);
                    startActivity(new Intent(SignUpActivity.this, UserDetailsActivity.class));
                }
            }
        });


    }
    private void resetPasswords(){
        EditText Password = (EditText) findViewById(R.id.editPassword);
        EditText ConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        Password.setText("");
        ConfirmPassword.setText("");
    }
}