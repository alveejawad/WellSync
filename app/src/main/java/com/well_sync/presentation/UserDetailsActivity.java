package com.well_sync.presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.well_sync.R;
import androidx.appcompat.app.AppCompatActivity;
public class UserDetailsActivity extends AppCompatActivity {
    private Spinner BloodTypeSpinner;
    private String[] bloodTypeList = new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    ArrayAdapter<String> adapterBloodType;
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_userdetails);
        TextView saveButton = findViewById(R.id.savebutton);

        BloodTypeSpinner = findViewById(R.id.BloodTypes);
        adapterBloodType = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodTypeList);
        BloodTypeSpinner.setAdapter(adapterBloodType);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDetailsActivity.this,HomePageActivity.class));
            }
        });
    }
}
