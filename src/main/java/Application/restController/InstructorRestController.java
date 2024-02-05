package Application.restController;


import Application.entity.Course;
import Application.entity.Instructor;
import Application.entity.InstructorDetail;
import Application.service.InstructorService;

import java.util.List;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/instructor")
public class InstructorRestController {

    private InstructorService instructorService;

    @Autowired
    public InstructorRestController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping("/{instructorId}")
    public ResponseEntity<String> getInstructorById(@PathVariable int instructorId) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();


        try {

            Instructor instructor = instructorService.findInstructorById(instructorId);
            if (instructor == null)
                return new ResponseEntity<>("Instructor not found", HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instructor), HttpStatus.OK);
        } catch (ResponseStatusException exc) {
            return new ResponseEntity<>(mapper.writeValueAsString(exc), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable int instructorId) {
        try {
            instructorService.deleteInstructorById(instructorId);

        } catch (Exception exc) {

            return new ResponseEntity<>("Request deleted Instructor is not existed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Instructor with ID: " + instructorId + " is deleted", HttpStatus.OK);
    }

    //   TODO: Testing the Port
    @PutMapping("/{instructorId}")
    public ResponseEntity<String> updateInstructorById(@PathVariable int instructorId, @RequestBody Instructor instructor) {

        if (instructor.isValid()) {

            return new ResponseEntity<>("Please provide a valid Instructor Info", HttpStatus.BAD_REQUEST);
        }
        try {

            instructorService.update(instructor, instructorId);
            return new ResponseEntity<>("Instructor Updated", HttpStatus.OK);
        } catch (Exception exce) {

            return new ResponseEntity<>("Please provide a valid Instructor id", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Instructor>> getAllInstructor() throws JsonProcessingException {
        try {
            List<Instructor> result = instructorService.getAllInstructor();

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){


            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> createInstructorById(@RequestBody Instructor instructor) {
        System.out.println(instructor);
        if (instructor.isValid())
            return new ResponseEntity<>("Please provide a valid Instructor Info", HttpStatus.BAD_REQUEST);

        instructorService.save(instructor);

        return new ResponseEntity<>("Instructor Created", HttpStatus.OK);
    }

    @PutMapping("/{instructorId}/instructorDetails")
    public ResponseEntity<String> updateInstructorDetailByiId(@RequestBody InstructorDetail instructorDetail, @PathVariable int instructorId) {

//      TODO: write a instructor detail validation function
//      check either the instructorDetail information is missed or not
        System.out.println(
                instructorDetail.isValid()
        );
        if (!instructorDetail.isValid()) {
            System.out.println("Running");
            return new ResponseEntity<>("Please provide a valid Instructor Detail Info", HttpStatus.BAD_REQUEST);

        }
        try {

            instructorService.updateInstructorDetailById(instructorId, instructorDetail);
            return new ResponseEntity<>("Instructor Detail Updated", HttpStatus.OK);
        } catch (Exception exception) {
            System.out.println(exception.toString());
            return new ResponseEntity<>("Please provide a valid Instructor id", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{instructorId}/instructorDetails")
    public ResponseEntity<String> deleteInstructorDetailsById(@PathVariable int instructorId) {
        try {
            instructorService.deleteInstructorDetailById(instructorId);

        } catch (Exception exc) {

            return new ResponseEntity<>("Request deleted Instructor Details is not existed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Instructor Details with ID: " + instructorId + " is deleted", HttpStatus.OK);
    }

    @GetMapping("/{instructorId}/courses")
    public ResponseEntity<String> findInstructorCourse(@PathVariable int instructorId){

        try{
            ObjectMapper mapper = new ObjectMapper();
            List<Course> courses = instructorService.findCoursesByInstructorId(instructorId);

            return new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(courses),HttpStatus.OK);

        }catch (Exception e){


            return new  ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/{instructorId}/course")
    public ResponseEntity<String> uploadCourseByInstructor(@PathVariable int instructorId, @RequestBody Course c){


        if (!c.isValid()){

            return new ResponseEntity<>("Please provide a valid Course", HttpStatus.BAD_REQUEST);

        }

        try{

            instructorService.uploadCourseByInstructorId(instructorId,c);
        }catch(DataIntegrityViolationException exc){
            System.out.println(exc.getMessage());
            return new ResponseEntity<>("The course is existed or taught by another instructor", HttpStatus.BAD_REQUEST);

        }catch(Exception e) {
            System.out.println(e);
            return new ResponseEntity("The instructor is not existed", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("You have upload the course", HttpStatus.OK);

    }

}
