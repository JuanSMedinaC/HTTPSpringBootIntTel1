package com.example.tallericesirest.model;

import java.util.ArrayList;

public class Registry {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public Registry(){
        courses=new ArrayList<>();
        students= new ArrayList<>();
    }

    public void addCourse(Course course) throws Exception{
        if (!courseExists(course.getId())){
            courses.add(course);
        }else{
            throw new Exception("Curso existente");
        }
    }

    public boolean courseExists(String id){
        boolean exists=false;
        for (int i =0; i<courses.size();i++){
            if (courses.get(i).getId().equals(id)){
                exists=true;
            }
        }
        return exists;
    }

    public boolean studentExists(String id){
        boolean exists=false;
        for (int i =0; i<students.size();i++){
            if (students.get(i).getId().equals(id)){
                exists=true;
            }
        }
        return exists;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Course findCourse(String id){
        for (int i =0; i<courses.size();i++){
            if (courses.get(i).getId().equals(id)){
                return courses.get(i);
            }
        }
        return null;
    }

    public Student findStudent(String id){
        Student student = null;
        for (int i =0; i<students.size();i++){
            if (students.get(i).getId().equals(id)){
                student=students.get(i);
            }
        }
        return student;
    }


}
