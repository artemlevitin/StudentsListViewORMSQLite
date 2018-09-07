package com.example.levitin.studentslistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class newStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
    }
    public void onCanselAddStudentClick(){
        finish();
    }
    public void onConfirmAddStudentClick(){
        finish();
    }
}
