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
import com.well_sync.presentation.SignUpActivity;
import com.well_sync.objects.UserCredentials;
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

    private Spinner bloodTypeSpinner;
    private String[] bloodTypeList = new String[]{"A", "B", "AB", "AB"};
    private ArrayAdapter<String> adapterBloodType;
    private Button saveButton;


    private String bloodType;
    private String gender;
    private int age;

    private UserCredentials newUserCredentials;
    private SignUpActivity signUpPage;


    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_userdetails);

        signUpPage = new SignUpActivity();
        patientHandler = new PatientHandler();
        userFirstName = findViewById(R.id.editFirstName);
        userLastName = findViewById(R.id.editLastName);
        genderPicker = findViewById(R.id.GenderPicker);
        saveButton = findViewById(R.id.savebutton);
        datePicker = findViewById(R.id.datePicker);
        bloodTypeSpinner = findViewById(R.id.BloodTypes);

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
                //get input from user
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();
                gender=getGender();
                bloodType=getBloodType();
                age=getAge();
                newUserCredentials=signUpPage.getUserCredentials();


                Patient newPatient= new Patient("test123@gmail.com",firstName,lastName,Patient.BloodType.TYPE_A, Patient.Sex.UNSPECIFIED,age);

                if(patientHandler.editDetails(newPatient)){
                    startActivity(new Intent(UserDetailsActivity.this, HomePageActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Data is invalid" , Toast.LENGTH_SHORT).show();
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