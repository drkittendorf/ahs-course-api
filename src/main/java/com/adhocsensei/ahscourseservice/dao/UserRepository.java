package com.adhocsensei.ahscourseservice.dao;


import com.adhocsensei.ahscourseservice.dto.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByEmail(String email);
//    List<User> findByUser(User user, Sort sort);
}