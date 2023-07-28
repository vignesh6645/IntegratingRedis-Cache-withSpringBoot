package com.example.SpringBootCacheIntegrationWithRedis.controller;

import com.example.SpringBootCacheIntegrationWithRedis.model.EmployeeDetails;
import com.example.SpringBootCacheIntegrationWithRedis.model.UserDetails;
import com.example.SpringBootCacheIntegrationWithRedis.requestDTO.UserDTO;
import com.example.SpringBootCacheIntegrationWithRedis.service.LogicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logic")
public class LogicController {

    @Autowired
    private LogicServiceImpl service;

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployeeDetails(@RequestBody EmployeeDetails employeeDetails) throws Exception {
        return ResponseEntity.ok(service.saveEmployeeDetails(employeeDetails));
    }

    @PostMapping("/user")
    @CacheEvict(value = "userDetails",allEntries = true)
    public UserDetails saveUser(@RequestBody UserDTO userDTO) throws Exception{
        return service.saveUserDetails(userDTO);
    }

    @GetMapping("/user")
    @Cacheable("userDetails")
    public List<UserDetails> getAllUser() {
        return service.getAllUsers();
    }

    @DeleteMapping("/user")
    @CacheEvict(value = "userDetails",allEntries = true)
    public String deleteUser(@RequestParam long id) {
        return service.deleteUser(id);
    }

    @PutMapping("/user")
    @CacheEvict(value = "userDetails",allEntries = true)
    public UserDetails updateUser(@RequestBody UserDTO userDTO) throws Exception {
        return service.updateUser(userDTO);
    }

}
