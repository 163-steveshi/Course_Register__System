package Application.restController;

import Application.dao.CourseDAO;
import Application.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import Application.dto.CourseRegistrationRequest;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/courseRegistration")
public class CourseRegistrationRestController {


    private StudentService studentService;

    @Autowired
    public CourseRegistrationRestController(StudentService studentService){

        this.studentService = studentService;
    }



//    ToDO: write customer bad request error handler
    @PostMapping("")
    public ResponseEntity<String> createCourseRegistration(@RequestBody CourseRegistrationRequest req) {

        System.out.println(req);
        studentService.registerCourse(req.getStudentId(),req.getCourseId());
        return new ResponseEntity<>("Receive the Information", HttpStatus.OK);

    }
}
