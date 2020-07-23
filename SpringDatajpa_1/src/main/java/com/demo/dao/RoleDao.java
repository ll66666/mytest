package com.demo.dao;

import com.demo.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.demo.entity.Role;

public interface RoleDao extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {
}

