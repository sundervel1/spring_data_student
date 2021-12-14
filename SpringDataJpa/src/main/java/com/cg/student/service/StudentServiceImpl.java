package com.cg.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.student.dao.ILoginDao;
import com.cg.student.dao.IStudentDao;
import com.cg.student.dto.UserDetails;
import com.cg.student.entities.Student;
import com.cg.student.exception.AuthenticationFailedException;
import com.cg.student.exception.StudentAlreadyExistsException;
import com.cg.student.exception.StudentNotFoundException;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private IStudentDao sDao;
	@Autowired
	private ILoginDao lDao;

	@Override
	public List<Student> findByName(String name) {
		System.out.println("name: " + name);
		List<Student> list = sDao.findByFirstName(name);
		return list;
	}
	@Override
	public List<Student> findByFullName(String fname, String lname) {
		List<Student> list = sDao.findByFullName(fname, lname);
		return list;
	}
	@Override
	public List<Student> findByFirstNameAndLastName(String fname, String lname) {
		List<Student> list = sDao.findByFirstNameAndLastName(fname, lname);
		return list;
	}
	@Override
	public Student findById(Integer id) {
		System.out.println("id: "+id);
        Optional<Student> optional = sDao.findById(id);
        if(!optional.isPresent()){
        	System.out.println("***error***");
            throw new StudentNotFoundException("student not found for id="+id);
        }
        Student student=optional.get();
        System.out.println("stud: "+ student);
        return student;
	}

	@Override
	public Student register(Student student) {
		boolean exists=student.getId()!=null && sDao.existsById(student.getId());
        if(exists){
            throw new StudentAlreadyExistsException("student already exists for id="+student.getId());
        }
        student = sDao.save(student);
        System.out.println("returning saved stud: " + student);
        return student;
	}
	
	@Override
	public List<Student> findAll() {
		System.out.println(sDao.getClass().getName());
		List<Student> list = sDao.findAll();
        return list;
	}
	@Override
	public String login(UserDetails userDetails) {
		String role = "";
		Optional<UserDetails> op = lDao.findById(userDetails.getUsername());
        if(!op.isPresent()){
            throw new AuthenticationFailedException("No User found for username="+userDetails.getUsername());
        }
		UserDetails uDetails = op.get();
		if(!userDetails.getPassword().equals(uDetails.getPassword())) {
            throw new AuthenticationFailedException("Authentification failed for username="+userDetails.getUsername());
		}
		role = uDetails.getUserRole();
		return role;
	}


}
