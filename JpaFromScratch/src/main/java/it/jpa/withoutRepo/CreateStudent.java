package it.jpa.withoutRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.jpa.model.Student;

public class CreateStudent {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Student student = new Student("Victor", "Faramelli");
		entityManager.persist(student);
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

}
