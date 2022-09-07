package net.crudperation.springbootbackend.controller;

import net.crudperation.springbootbackend.dto.Login;
import net.crudperation.springbootbackend.model.Student;
import net.crudperation.springbootbackend.services.StudentService;
import net.crudperation.springbootbackend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
     private String msg = "Student not found by ID";
     private String status ="APPROVED";
     @Autowired
     private StudentService studentService;

     //this is getting api to get all data
     @GetMapping
     public List<Student> getAllStudents() {
          return (List<Student>) studentService.findAll();
     }

     //this is create student api
     @PostMapping
     public Student createStudent(@Valid @RequestBody Student student) {
          Optional<Student> stuObj = studentService.findByEmailId(student.getEmail());
          if (stuObj.isPresent()) {
               throw new ResourceNotFoundException("student Already exists");
          }
          if (student.getRole().equals("Admin")) {
               student.setApprovedStatus("PENDING");
               student.setApproved(false);

          } else {
               student.setApprovedStatus(status);

               student.setApproved(true);

          }
          Student savedUser = studentService.save(student);
          savedUser.setPassword(null);
          return savedUser;
     }

     //build get student by id rest api
     @GetMapping("{id}")
     public ResponseEntity<Object> getStudentById(@PathVariable long id) {
          Object student = studentService.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException(msg + " " + id));
          return ResponseEntity.ok(student);
     }

     //build update rest api
     @PutMapping("{id}")
     public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails) {
          Student updateStudent = studentService.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException(msg + " " + id));
          updateStudent.setName(studentDetails.getName());
          updateStudent.setEmail(studentDetails.getEmail());
          updateStudent.setPassword(studentDetails.getPassword());
          updateStudent.setCity(studentDetails.getCity());
          updateStudent.setPhonenumber(studentDetails.getPhonenumber());

          studentService.save(updateStudent);
          return ResponseEntity.ok(updateStudent);
     }

     //build delete rest api
     @DeleteMapping("{id}")
     public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id) {
          Student student = studentService.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException(msg + " " + id));
          studentService.delete(student);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

     @PostMapping("/login")
     public Student loginUser(@Valid @RequestBody Login login) {

          Optional<Student> student = studentService.findByEmailId(login.email);

          if (!student.isPresent() || !student.get().getPassword().equals(login.password)) {
               throw new ResourceNotFoundException("Invalid Credentials");
          }
          if(Boolean.FALSE.equals(student.get().getApproved())){
              throw new ResourceNotFoundException("Your account is not verified yet");
          }
          student.get().setPassword(null);
          return student.get();
     }


     @GetMapping("/adminUser")
     public List<Student> getAdmin(){
          return (List<Student>) studentService.getAllAdmin();
     }



     @GetMapping("/getApprovedUsers/{currentUserId}")
     public List<Student> getAllApprovedUser(@PathVariable long currentUserId){

        return studentService.getAllApprovedUser(status, currentUserId);



     }

     @PostMapping("/{id}/approve")
     public ResponseEntity<Student> adminAccess(@PathVariable long id){
          Student adminAccess = studentService.findById(id).
                  orElseThrow(() -> new ResourceNotFoundException("No user exist with such id:  " + id));

          adminAccess.setApprovedStatus("APPROVED");

          studentService.save(adminAccess);
          return ResponseEntity.ok(adminAccess);
     }
     @PostMapping("/{id}/deny")
     public ResponseEntity<Student> adminAccessDeny(@PathVariable long id){
          Student adminAccessDeny = studentService.findById(id).
                  orElseThrow(() -> new ResourceNotFoundException("No user exist with such id:  " + id));
          adminAccessDeny.setApprovedStatus("REJECTED");
          adminAccessDeny.setApproved(Boolean.FALSE);
          studentService.save(adminAccessDeny);
          return ResponseEntity.ok(adminAccessDeny);
     }

}




