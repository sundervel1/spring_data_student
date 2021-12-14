package com.cg.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.student.dto.UserDetails;

public interface ILoginDao extends JpaRepository<UserDetails, String> {

}
