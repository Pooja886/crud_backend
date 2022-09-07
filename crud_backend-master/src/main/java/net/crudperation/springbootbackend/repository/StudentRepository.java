package net.crudperation.springbootbackend.repository;

import net.crudperation.springbootbackend.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Long > {

    List<Student> findByRole(String role);


    List<Student> findByApprovedStatusAndIdNot(String approved, long id );



    Optional<Student> findByEmail(String email);
    Boolean existsByEmail(String email);
}
