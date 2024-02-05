package Application.restController;

import Application.entity.Course;
import Application.entity.Review;
import Application.service.CourseService;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/course")
public class CourseRestController {

    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService){

        this.courseService = courseService;
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<String> findCourseById(@PathVariable int courseId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();


        try {

           Course course  = courseService.findCourseById(courseId);


            return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(course), HttpStatus.OK);
        } catch (Exception exc) {
            return new ResponseEntity<>("Sorry the course is not existed", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{courseId}/reviews")
    public ResponseEntity<String> getCourseReviews(@PathVariable int courseId){

        ObjectMapper mapper = new ObjectMapper();


        try {

            List<Review> reviews = courseService.getCourseReviewsById(courseId);


            return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reviews), HttpStatus.OK);
        } catch (Exception exc) {
            return new ResponseEntity<>("Sorry the course is not existed", HttpStatus.NOT_FOUND);
        }



    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseById(
            @PathVariable int courseId
    ){
        try {

           courseService.deleteCourseById(courseId);

            return new ResponseEntity<>("Course is Deleted ", HttpStatus.OK);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
//            TODO: try to handle the deletion remove the student enrolled the course
            return new ResponseEntity<>("Sorry the course is not existed or currently enrolled by Other Student", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Course>> findCourses(){

        try{

            return new ResponseEntity<>(courseService.findCourses(), HttpStatus.OK);
        }catch(Exception e){


            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }


    }
    @PutMapping("/{courseId}")
    public ResponseEntity<String> updateCourse(@RequestBody Course c, @PathVariable int courseId){


        if(!c.isValid()){
            return new ResponseEntity<>("Please Provide a valid course Object", HttpStatus.BAD_REQUEST);
        }
        c.setId(courseId);
        try{

            courseService.updateCourseById(courseId, c);
        }catch(DataIntegrityViolationException exc){
                return new ResponseEntity<>("There is a another instructor teaches the current course", HttpStatus.BAD_REQUEST);

        }catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity("The course you want to update doesn't existed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("The course is updated", HttpStatus.OK);

    }
}
