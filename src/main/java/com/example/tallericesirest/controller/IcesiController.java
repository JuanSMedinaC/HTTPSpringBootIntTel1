package com.example.tallericesirest.controller;

import com.example.tallericesirest.model.Course;
import com.example.tallericesirest.model.Registry;
import com.example.tallericesirest.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IcesiController {
    Registry registry = new Registry();

    @GetMapping
    public String echo(){
        return "";
    }

    @PostMapping("materias/create")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        try {
            registry.addCourse(course);
            return ResponseEntity.status(200).body("Course Created");
        } catch (Exception e) {
            return ResponseEntity.status(406).body("Course already exists");
        }

    }

    @GetMapping("materias")
    public String findCourse(@RequestParam("id") String id){
        if (registry.courseExists(id)){
            Course course=registry.findCourse(id);
            return course.toString();
        }else {
            return "Course not found";
        }
    }

    @PostMapping("estudiantes")
    public String addStudent(@RequestBody Student student){
        if(registry.studentExists(student.getId())){
            return "User already created";
        }else{
            registry.addStudent(student);
            return "Student created succesfully";
        }
    }

    @GetMapping("estudiantes/{id}")
    public String findStudent(@PathVariable("id") String id){
        if (registry.studentExists(id)){
            return registry.findStudent(id).toString();
        }else{
            return "Student couldn't be found";
        }
    }

    @PutMapping("estudiantes/{estid}/matricular/{matid}")
    public String registerStudent(@PathVariable("estid")String estid, @PathVariable("matid") String matid){
        if(!registry.courseExists(matid)){
            return "Course does not exist";
        } else{
            if (!registry.studentExists(estid)){
                return "Student is not registered";
            }else{
                if (!registry.findCourse(matid).studentRegistered(estid)){
                    Student student= registry.findStudent(estid);
                    registry.findCourse(matid).registerStudent(student);
                    return "Student registered in course succesfully";
                }else{
                    return "Student already registered in course";
                }
            }
        }
    }

    @DeleteMapping("materias")
    public String unRegisterStudent(@RequestHeader("matid")String matid,@RequestHeader("estid")String estid){
        if(!registry.courseExists(matid)){
            return "Course does not exist";
        } else{
            if (!registry.studentExists(estid)){
                return "Student is not registered";
            }else{
                if (registry.findCourse(matid).studentRegistered(estid)){
                    Student student= registry.findStudent(estid);
                    registry.findCourse(matid).unregisterStudent(student);
                    return "Student unregistered from course succesfully";
                }else{
                    return "Student is not registered in course";
                }
            }
        }
    }


}
