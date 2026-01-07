package com.userCompany.usercad.services;

import com.userCompany.usercad.model.User;
import com.userCompany.usercad.repositories.UserRepository;
import com.userCompany.usercad.services.exceptions.DatabaseException;
import com.userCompany.usercad.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
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
        try{
            userRepository.deleteById(id);
            userRepository.flush();
        } catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public User update(Long id, User user){
        try{
            User userUp = userRepository.getReferenceById(id);
            updateDataUser(userUp,user);
            userRepository.flush();
            return userRepository.save(userUp);
        } catch(ResourceNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDataUser(User obj, User user){
        obj.setUsername(user.getUsername());
        obj.setEmail(user.getEmail());
        obj.setPassword(user.getPassword());
    }

}
