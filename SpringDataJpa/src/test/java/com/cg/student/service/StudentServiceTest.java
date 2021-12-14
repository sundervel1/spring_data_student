package com.cg.student.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.student.entities.Student;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(StudentServiceImpl.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StudentServiceTest {
	@Autowired
	private StudentServiceImpl service;
	@Autowired
	private EntityManager em;

	@Test
	public void testFindById() {
		Student stud = new Student("Arpita", "Chakraborty", 21);
		em.persist(stud);
		Integer id = stud.getId();
		Student studFound = service.findById(id);
		System.out.println(studFound);
		Assertions.assertEquals(studFound.getFirstName(), stud.getFirstName());
	}
}
