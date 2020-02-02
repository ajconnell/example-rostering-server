package com.wackywallaby.rosterexample.controller;

import com.wackywallaby.rosterexample.model.Role;
import com.wackywallaby.rosterexample.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public Iterable<Role> fetchRoles() {
        return roleRepository.findAll();
    }

}
