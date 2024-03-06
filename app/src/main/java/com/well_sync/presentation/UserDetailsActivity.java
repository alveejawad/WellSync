package com.well_sync.presentation;
import java.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.well_sync.R;
import com.well_sync.logic.PatientHandler;
import com.well_sync.objects.Patient;
import com.well_sync.objects.UserCredentials;
import com.well_sync.logic.exceptions.InvalidPatientException;

import com.well_sync.presentation.SignUpActivity;

import java.util.Objects;


import androidx.appcompat.app.AppCompatActivity;
public class UserDetailsActivity extends AppCompatActivity {

    private PatientHandler patientHandler;

    //User data
    private EditText userFirstName;
    private EditText userLastName;
    private DatePicker datePicker;
    private RadioGroup genderPicker;
    private RadioButton selectedGender;
    private Button saveButton;

    private Spinner bloodTypeSpinner;
    private String[] bloodTypeList = new String[]{"A+","A-", "B+", "B-", "O+","O-", "AB+","AB-"};
    private ArrayAdapter<String> adapterBloodType;

    private Intent intent;
    private String bloodType;
    private String gender;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_userdetails);

        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
        genderPicker = findViewById(R.id.GenderPicker);
        saveButton = findViewById(R.id.savebutton);
        datePicker = findViewById(R.id.datePicker);
        bloodTypeSpinner = findViewById(R.id.BloodTypes);
        patientHandler = new PatientHandler();
        setBloodTypeValues();


        bloodTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                bloodType = adapterView.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                bloodType = null;
                Toast.makeText(getApplicationContext(), "Please select blood type", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get email from Sign Up Activity
                intent = getIntent();
                email = intent.getStringExtra("email");
                //get input from user
                firstName = userFirstName.getText().toString();
                lastName = userLastName.getText().toString();
                gender=getGender();
                bloodType=getBloodType();
                age=getAge();


                Patient newPatient= new Patient(email,firstName,lastName,bloodType,gender,age);

                if(patientHandler.editDetails(newPatient)){
                   Intent homeIntent =new Intent(UserDetailsActivity.this, HomePageActivity.class);
                   homeIntent.putExtra("email",email);
                    startActivity(homeIntent);

                }else{
                    Toast.makeText(getApplicationContext(), "Data is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setBloodTypeValues(){
        adapterBloodType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodTypeList);
        bloodTypeSpinner.setAdapter(adapterBloodType);
    }
    private String getGender() {
        String gender=null;
        if (genderPicker.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select gender", Toast.LENGTH_SHORT).show();
        } else {
            selectedGender = findViewById(genderPicker.getCheckedRadioButtonId());
            gender = selectedGender.getText().toString();
        }
        return gender;
    }

    private int getAge() {
        int age=-1;
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        age=currentYear - userAge;
        return age;
    }
    private String getBloodType(){
        return bloodType;
    }

    private void resetNames() {
        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
    }

}