package it.jpa.repository;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.jpa.model.Student;
public class StudentRepository {
	EntityManager entityManager;
	EntityManagerFactory entityManagerFactory;

	public StudentRepository() {
		entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
		entityManager = entityManagerFactory.createEntityManager();
	}


	public Student addStudent(Student student) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(student);
		entityTransaction.commit();
		return student;
	}
//	Only when changes to the database are made a transaction is needed 
	public Student find(Student student) {

		return entityManager.find(Student.class, student.getId());
	}
	
	public Student update(Student student) {
		Student studentToUpdate = findStudentById(student.getId());
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		studentToUpdate.setFirstName(student.getFirstName());
		studentToUpdate.setLastName(student.getLastName());
		entityTransaction.commit();
		return studentToUpdate;
	}
	
	public Student delete(Student student) {
		Student studentToDelete = findStudentById(student.getId());

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(studentToDelete);
		entityTransaction.commit();
		return studentToDelete;
	}
	public Student deleteById(Long id) {
		Student studentToDelete = findStudentById(id);

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(studentToDelete);
		entityTransaction.commit();
		return studentToDelete;
	}
	
	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	
//	methods with Jpql (Java persistence query language)
	
	public List<Student> findAll(){
		
		Query query= entityManager.createQuery("Select a from Student a");
		return query.getResultList();
	}
//	this method uses a non parametered string, that could lead to breaches in the database by using inputs that make a query
	public Student updateFirstName(String firstName, Long id) {
		entityManager.getTransaction().begin();
		Query query	= entityManager.createQuery("Update Student set firstName ='" + firstName + "' where id = "+ id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
		return findStudentById(id);
	}
	
	public void deleteById2(Long id) {
		entityManager.getTransaction().begin();
		Query query	= entityManager.createQuery("delete from Student where id =:id");
		query.setParameter("id", id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
	
	public Long count() {
		Query query= entityManager.createQuery("Select count(a) from Student a");
		return (Long)query.getSingleResult();

	}
	public List<Student> findByFirsNameBeginWith(String substring){
		Query query = entityManager.createQuery("Select a from Student a where a.firstName like :substring");
		query.setParameter("substring", substring + "%");
		return query.getResultList();
	}
	public List<Student> findAllSortedByFirstName(){
		Query query = entityManager.createQuery("Select a from Student a order by a.firstName");
		return query.getResultList();
	}
	public List<Student> findAllSortedByFirstNameDesc(){
		Query query = entityManager.createQuery("Select a from Student a order by a.firstName desc");
		return query.getResultList();
	}

//	methods with named query are defined in the entity class
	public Student findStudentById(Long id) {
		Query query = entityManager.createNamedQuery("find by Id");
		query.setParameter("id", id);
		return (Student) query.getSingleResult();
	}
//	clear does clean the entities in the entity manager, freeing memory and making it run smoother
	public void clearEntityManager() {
		entityManager.clear();
	}
}
