package com.example.levitin.studentslistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListStudent_BaseAdapter extends BaseAdapter {

    ArrayList<Student> students;
    LayoutInflater inflater;

   public ListStudent_BaseAdapter(Activity activity, ArrayList<Student> students){
       inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       this.students = students;
   }
    @Override
    public int getCount() {
        return  students.size();
    }

    @Override
    public Object getItem(int position) {
       try{
           return students.get(position);
       }
       catch(Exception exc){
           return null;
       }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       try {
           View resView = inflater.inflate(R.layout.student_view, null);

           TextView fName = resView.findViewById(R.id.firstName);
           TextView lName = resView.findViewById(R.id.lastName);
           TextView grName= resView.findViewById(R.id.groupName);
           ImageView imgGender = resView.findViewById(R.id.image);

           Student student = students.get(position);
           fName.setText(student.getFirstName());
           lName.setText(student.getLastName());
           grName.setText(student.getNameGroup());

           int gender = student.getGender() == Student.Gender.MALE ? R.drawable.boy : R.drawable.girl;
           imgGender.setImageResource(gender);
           return resView;
       }
       catch(Exception exc) {
           return null;
       }
    }
}
