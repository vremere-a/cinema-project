package com.dev.cinema.service;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(RoleName roleName);
}
