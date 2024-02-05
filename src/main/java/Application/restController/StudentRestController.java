package Application.restController;

import Application.entity.Student;
import Application.service.StudentService;

import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/student")
public class StudentRestController {


    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAllStudent(){


        try{
            List<Student> results = studentService.findAllStudents();
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        catch(Exception e){

            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }


    }

    @GetMapping("/{studentId}")
    public ResponseEntity<String> findStudentById(@PathVariable int studentId){

        try{

            ObjectMapper objectMapper = new ObjectMapper();

            return new ResponseEntity<>(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentService.findStudentById(studentId)), HttpStatus.OK);


        }catch(Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }


    }

    @PostMapping("")
    public ResponseEntity<String> registerStudent(@RequestBody Student  stu){
        if(!stu.isValid())
            return new ResponseEntity<>("please provide a valid student information", HttpStatus.BAD_REQUEST);
        try{

            studentService.registerStudent(stu);

        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Server Error, try again later", HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>("Student is created", HttpStatus.OK);
    }


    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@RequestBody Student  stu,  @PathVariable int studentId){
        if(!stu.isValid())
            return new ResponseEntity<>("please provide a valid student information", HttpStatus.BAD_REQUEST);
        try{

            studentService.updateStudent(stu, studentId);

        }catch(NullPointerException exc){

            return new ResponseEntity<>("The Student you want to modify is not existed", HttpStatus.BAD_REQUEST);

        }

        catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Server Error, try again later", HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>("Student is updated", HttpStatus.OK);
    }


    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int studentId){

        try{

            studentService.removeStudentById(studentId);


            return new ResponseEntity<>("The student with id: " + studentId + " is successful Deleted", HttpStatus.OK);

        }

        catch(Exception e){

            System.out.println(e.getMessage());
            return new ResponseEntity<>("Sorry, the student you want to delete isn't existed", HttpStatus.NOT_FOUND);
        }
    }





}
