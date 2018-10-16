package com.example.levitin.studentslistview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();
    ListView studentsListView;
    ListStudent_BaseAdapter adapter;

    final static int codeResult = 1;



    private void fillStudents(){
        students.add(new Student("Ivan","Ivanov","IOT-0928", Student.Gender.MALE));
        students.add(new Student("Oleg","Olegov","IOT-0928", Student.Gender.MALE));
        students.add(new Student("Anna","Annova","IOT-0928", Student.Gender.FEMALE));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         studentsListView = (ListView)findViewById(R.id.students_ListView);
         fillStudents();
         adapter = new ListStudent_BaseAdapter(this,students);
         studentsListView.setAdapter(adapter);

    }

    private Student findStudent(String lName,String fName) {
        for(Student st : students) {
            if(st.getLastName().equals(lName)&st.getFirstName().equals(fName))
                return st;
        }
        return null;
    }

    public void onRemoveStudentClick(View view){

        for( int i =0; i<studentsListView.getCount();++i){
            LinearLayout line= (LinearLayout)studentsListView.getChildAt(i);
            CheckBox chBox = line.findViewById(R.id.student_checkBox);
            if (chBox.isChecked()) {
                TextView lNameView =(TextView) line.findViewById(R.id.lastName);
                TextView fNameView =(TextView) line.findViewById(R.id.firstName);
                students.remove( findStudent( lNameView.getText().toString() ,fNameView.getText().toString() ) );
                adapter.notifyDataSetChanged();
            }
        }

    }

    public  void addStudentClick(View view){
        Intent intent = new Intent(this,addNewStudentActivity.class);
        startActivityForResult(intent,codeResult);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode==codeResult){
            if(resultCode==RESULT_OK){
                Student st = (Student) intent.getSerializableExtra("student");
                students.add(st);
                adapter.notifyDataSetChanged();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public void daoSave () {
        Dao studentDao;

        try {
            studentDao = DatabaseManager.getInstance().getHelper().getStStudentDao();
            DatabaseManager.getInstance().getHelper().dropCreateStudentTable();
            for (Student std : students) {
                studentDao.create(std);
            }
            Log.d("myLogs", "Dao is created good");
            /*List<Student> studentList = studentDao.queryForAll();
            for (Student st : studentList) {
                Log.d("myLogs", String.format("Student name: %s group: %s", st.getLastName(), st.getNameGroup()));
            }*/
     } catch (SQLException e) {
            Log.d("myLogs", e.getMessage());
            e.printStackTrace();
        }
    }
    public void daoLoad () {
        Dao studentDao;

        try {
            studentDao = DatabaseManager.getInstance().getHelper().getStStudentDao();
            List<Student> studentList = studentDao.queryForAll();
            students.clear();
            for (Student st : studentList) {
                //Log.d("myLogs", String.format("Student name: %s group: %s", st.getLastName(), st.getNameGroup()));
                students.add(st);
            }
            adapter.notifyDataSetChanged();

        } catch (SQLException e) {
            Log.d("myLogs", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.d("myLogs", "MainActivity.onDestroy");
        DatabaseManager.getInstance().release();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
             case R.id.save_students:
                 this.daoSave();
                 break;
             case R.id.load_students:
                 this.daoLoad();
                 break;
             default:
                 break;
        }
    return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}