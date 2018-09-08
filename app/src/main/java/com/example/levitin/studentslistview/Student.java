package com.example.levitin.studentslistview;

import java.io.Serializable;

public class Student implements Serializable {
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    enum Gender {MALE,FEMALE};

    private String firstName;
    private String lastName;
    private String nameGroup;

    private Gender gender;
    //int gend

public Student(String fName, String lName, String nGroup, Gender gnd){
    setFirstName(fName);
    setLastName(lName);
    setNameGroup(nGroup);
    setGender(gnd);

}
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    @Override
    public boolean equals(Object obj) {
       Student std = (Student)obj;
       if(std.getLastName().equals(this.lastName))
           return true;

       return false;
    }
}
