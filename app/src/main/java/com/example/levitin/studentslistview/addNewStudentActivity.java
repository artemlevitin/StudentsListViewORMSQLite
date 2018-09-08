package com.example.levitin.studentslistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class addNewStudentActivity extends AppCompatActivity {

    private EditText fname;
    private EditText lname;
    private EditText grname;
    private RadioButton femaleGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        fname = (EditText)findViewById(R.id.fNameNewSt);
        lname = (EditText)findViewById(R.id.lNameNewSt);
        grname = (EditText)findViewById(R.id.grNameNewSt);
        femaleGender =(RadioButton)findViewById(R.id.female_radioButton);
    }
    public void onCanselAddStudentClick(View view){
        finish();
    }

    public void onConfirmAddStudentClick(View view){
        Intent intent = new Intent();
        Student.Gender gender=femaleGender.isChecked()? Student.Gender.FEMALE:Student.Gender.MALE;

        intent.putExtra("student",new Student(fname.getText().toString(),lname.getText().toString(),grname.getText().toString(), gender));
                   setResult(RESULT_OK, intent);
            finish();
    }
}
