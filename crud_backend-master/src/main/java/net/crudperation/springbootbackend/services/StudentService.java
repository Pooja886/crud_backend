package net.crudperation.springbootbackend.services;

import net.crudperation.springbootbackend.model.Student;
import net.crudperation.springbootbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    public Optional<Student> findById(long id) {
        return studentRepository.findById(id);
    }
    public void delete(Student student) {
        studentRepository.delete(student);
    }
    public Object findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findByEmailId(String email){
        return studentRepository.findByEmail(email);
    }

    public Object getAllAdmin(){
        return studentRepository.findByRole("Admin");
    }


    public List<Student> getAllApprovedUser(String approved, long id){
        return studentRepository.findByApprovedStatusAndIdNot(approved, id);

    }
}