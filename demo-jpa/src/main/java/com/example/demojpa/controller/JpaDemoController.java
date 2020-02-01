package com.example.demojpa.controller;

import com.example.demojpa.dao.UserDao;
import com.example.demojpa.entity.Address;
import com.example.demojpa.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zy
 * @date 2020-2-1 18:54
 */
@RestController
@AllArgsConstructor
public class JpaDemoController {

    private UserDao userDao;

    @RequestMapping("/jpa-demo/add")
    public void add(String addressName, String userName){
        Address addressBean = Address.builder().address(addressName).build();
        User user = User.builder().name(userName).address(addressBean).build();
        userDao.save(user);
    }

    @RequestMapping("/jpa-demo/del")
    public void deleteById(Integer userId){
        userDao.deleteById(userId);
    }

    @RequestMapping("/jpa-demo/{id}")
    public User findById(@PathVariable(name = "userId") Integer userId){
        return userDao.findById(userId).get();
    }

}
