package com.company.simpljwt.repositories;

import com.company.simpljwt.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}