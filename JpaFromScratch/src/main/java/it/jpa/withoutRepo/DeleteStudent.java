package it.jpa.withoutRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.jpa.model.Student;

public class DeleteStudent {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		Student student = entityManager.find(Student.class, 1L);
		System.out.println(student.toString());
		entityTransaction.begin();
		entityManager.remove(student);
		entityTransaction.commit();
		System.out.println(student.toString());
		entityManager.close();
		entityManagerFactory.close();
	}

}
