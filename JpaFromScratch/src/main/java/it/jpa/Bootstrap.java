package it.jpa;

import it.jpa.model.Student;
import it.jpa.repository.StudentRepository;

public class Bootstrap {

	public static void main(String[] args) {
		StudentRepository studentRepository = new StudentRepository();
//		Student student = new Student();
//		student.setFirstName("Quasimodo");
//		student.setLastName("Serafico");
//		studentRepository.addStudent(student);
//		System.out.println(studentRepository.findStudentById(student.getId()));
//		studentRepository.deleteById(1L);
//		student.setFirstName("Ciro");
//		student.setLastName("Amendola");
//		studentRepository.delete(student);
//		System.out.println(studentRepository.findStudentById(student.getId()));
//		studentRepository.close();
//		studentRepository.findAll().forEach(System.out::println);
//		studentRepository.updateFirstName("Luca", 2L);
//		studentRepository.findAll().forEach(System.out::println);
//		studentRepository.deleteById2(2L);
//		System.out.println("+++");
//		studentRepository.findByFirsNameBeginWith("C").forEach(System.out::println);
//		System.out.println("---");
		System.out.println("Count of objects in the database = " + studentRepository.count());
		studentRepository.findAllSortedByFirstName().forEach(System.out::println);
		
	}

}
