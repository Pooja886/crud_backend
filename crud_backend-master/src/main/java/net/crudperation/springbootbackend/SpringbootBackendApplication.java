package net.crudperation.springbootbackend;

import net.crudperation.springbootbackend.model.Student;

import net.crudperation.springbootbackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
		@Autowired StudentRepository studentRepository;
	@Override
	public void run(String... args) throws Exception {

		Student student= new Student();
		student.setName("Pooja");
		student.setEmail("ps@gmail.com");
		student.setPassword("ps");
		student.setCity("udaipur");
		student.setPhonenumber("9565123456");
		student.setRole("Super Admin");
		studentRepository.save(student);




	}
}
