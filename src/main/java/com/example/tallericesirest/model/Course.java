package com.example.tallericesirest.model;

import java.util.ArrayList;

public class Course {
    private String id;
    private String name;
    private ArrayList<Student> students;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        students=new ArrayList<>();
    }

    public Course(){
        students=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void registerStudent(Student student){
        students.add(student);
    }

    public void unregisterStudent(Student student){
        students.remove(student);
    }

    public boolean studentRegistered(String id){
        boolean exists=false;
        for (int i =0; i<students.size();i++){
            if (students.get(i).getId().equals(id)){
                exists=true;
            }
        }
        return exists;
    }



    @Override
    public String toString() {
        return "id: "+id+", name: "+name;
    }
}
