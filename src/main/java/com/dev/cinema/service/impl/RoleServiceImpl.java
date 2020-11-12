package com.dev.cinema.service.impl;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;
import com.dev.cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleService roleService;

    @Override
    public void add(Role role) {
        roleService.add(role);
    }

    @Override
    public Role getRoleByName(RoleName roleName) {
        return roleService.getRoleByName(roleName);
    }
}
