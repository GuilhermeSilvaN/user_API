package com.userCompany.usercad.services;

import com.userCompany.usercad.model.User;
import com.userCompany.usercad.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Transactional
    public User update(User user){
        User updatedUser = userRepository.getReferenceById(user.getId());
        updateDataUser(updatedUser, user);
        return userRepository.save(updatedUser);
    }

    private void updateDataUser(User obj, User user){
        obj.setUsername(user.getUsername());
        obj.setEmail(user.getEmail());
        obj.setPassword(user.getPassword());
    }

}
