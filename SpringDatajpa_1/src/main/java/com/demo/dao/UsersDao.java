package com.demo.dao;

import com.demo.entity.LinkMan;
import com.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersDao  extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {
}

