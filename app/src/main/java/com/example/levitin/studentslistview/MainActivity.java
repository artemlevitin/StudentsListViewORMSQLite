package com.example.levitin.studentslistview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Map<String, Object>> studentDataList;
    ListView studentsListView;
    SimpleAdapter smplAdapter;
    final String imgAttr= "image";
    final String fNameAttr= "fName";
    final String lNameAttr= "lName";
    final String grNameAttr= "grName";
    final String checkBoxAttr= "chkBox";
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
        fillStudents();
        studentsListView = (ListView)findViewById(R.id.students_ListView);
        setStudentsListView();

    }


    private void setStudentsListView() {
    // упаковываем данные в понятную для адаптера структуру
        studentDataList = new ArrayList<Map<String, Object>>( students.size());

        for(Student std : students){
           studentDataList.add(studentToMap(std));
        }
        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { imgAttr, fNameAttr, lNameAttr, grNameAttr, checkBoxAttr  };
        // массив ID View-компонентов, в которые будут вставляться данные
        int[] to = {R.id.image, R.id.firstName, R.id.lastName, R.id.groupName ,R.id.student_checkBox};

        smplAdapter = new SimpleAdapter(this, studentDataList,R.layout.student_view,from,to);
        studentsListView.setAdapter(smplAdapter);

    }

    @NonNull
    private Map<String, Object> studentToMap(Student std) {
        Map<String, Object> m;
        m = new HashMap<String, Object>();
        int img = std.getGender()== Student.Gender.MALE? R.drawable.boy:R.drawable.girl;
        m.put(imgAttr,img);
        m.put(fNameAttr, std.getFirstName());
        m.put(lNameAttr, std.getLastName());
        m.put(grNameAttr, std.getNameGroup());
        m.put(checkBoxAttr, "");
        return m;
    }






    public void onRemoveStudentClick(View view){
        for( int i =0; i<studentsListView.getCount();++i){
            LinearLayout line= (LinearLayout)studentsListView.getChildAt(i);
            CheckBox chBox = line.findViewById(R.id.student_checkBox);
            if (chBox.isChecked()) {
                TextView lNameView =(TextView) line.findViewById(R.id.lastName);
                studentDataList.remove( findStudent(lNameView.getText().toString()) );
                smplAdapter.notifyDataSetChanged();
            }
        }

    }

    private Map<String, Object> findStudent(String lName) {
        for(Map<String, Object> m : studentDataList) {
            if(m.get(lNameAttr).equals(lName))
                return m;
            }

        return null;
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
                 studentDataList.add(studentToMap(st));
              smplAdapter.notifyDataSetChanged();

             }
      }
         else{
             super.onActivityResult(requestCode, resultCode, intent);
         }
     }
}
