package com.example.demojpa.dao;

import com.example.demojpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zy
 * @date 2020-2-1 18:01
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
}
