package com.dev.cinema.dao;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;

public interface RoleDao {
    void add(Role role);

    Role getRoleByName(RoleName roleName);
}
