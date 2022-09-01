package com.student.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.core.Student;
import com.student.exception.MyException;
import com.student.service.StudentService;

 

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {
	
	 
	@Inject
	private StudentService studentService;
	
	// The string after colon allows the @Value to have a defualt value.
	//@Value("${spring.main.web-application-type:servlet}")
	//private String firstValue;

	// When creating the class the firstValue is null, but after @PostConstruct the value is servlet
	// If the value is commented out or has not been declared then it an excepiton will be thrown.
	//@PostConstruct
	//public void test() {
	//	System.out.println("Charles Stockman the value of firstValue " + firstValue);
	//}
	
	//Begin commented out since this was part an experiment that I ran.  The firstValue was printed from the
	//constructor ( firstValue was null ) and the function with @PostContruct ( firstvalue had a value that
	//@Value retrieved
	//public StudentController() {
	//	System.out.println("Charles Stockman the value of firstValue " + firstValue);
    //}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	
	@GetMapping(path="/search/department", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getDepartments() {
		 System.out.println("Charles Stockman -- Currently in the controller ");
		 List<String> departments = studentService.getAllStudents().stream().map(p-> p.getDept()).distinct().collect(Collectors.toList());
		 return departments;
	}

	@GetMapping(path="/search/department/{department}", produces= MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getStudentsByDepartment(@PathVariable("department") String department) {
		Collection<Student> studentsByDepartment = studentService.getStudentsByDepartment(department); 
		return studentsByDepartment;
	}

	@GetMapping(path="/search/cost/{fee}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getStudentsByFee(@PathVariable("fee") double fee) {
		Collection<Student> studentsByFees = studentService.getStudentsWhoPaidExactFee(fee);
		return studentsByFees;
	}

	// Creates a transaction when a exception is thrown then the data rolled back unless 
	// a new transaction is create to save the data ( see the StudentRepository.java )
	@PostMapping("/add")
	@Transactional(rollbackFor = MyException.class)
	public ResponseEntity<String> add(@RequestBody Student student  ) throws MyException {
		studentService.insertStudent(student);
		if ( student.getFees() > 200.00 ) {
			throw new MyException("The fee has exceeded $200");
		}
		return ResponseEntity.accepted().header("location", "/student/" + student.getId()).build();
	}

	@ExceptionHandler(Exception.class) 
	public ResponseEntity<Object> handle(Exception exception) {
		return ResponseEntity.badRequest().build();
	}
}
