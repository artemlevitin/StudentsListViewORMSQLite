package com.example.levitin.studentslistview;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.UUID;

@DatabaseTable(tableName = "student")
public class Student implements Serializable {
    public Gender getGender() {
        return gender;
    }
    public Student(){}
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    enum Gender {MALE,FEMALE};

    @DatabaseField(canBeNull = true)
    private String firstName;

    @DatabaseField(canBeNull = true)
    private String lastName;

    @DatabaseField(canBeNull = true)
    private String nameGroup;

    @DatabaseField(id=true)
    private String id;

    @DatabaseField(canBeNull = true)
    private Gender gender;


public Student(String fName, String lName, String nGroup, Gender gnd){

    setID();
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

    private String getID() {
       return this.id;
    }
    private void setID() {
        this.id= UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object obj) {
       Student std = (Student)obj;
       if(std.getLastName().equals(this.lastName))
           return true;

       return false;
    }
}
