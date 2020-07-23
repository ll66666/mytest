package com.demo.dao;

import com.demo.entity.Customer;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {
              //Repository仓库,Specification规则 executor执行者
    //使用 jpgl的方式查询
    //查询全部
    @Query(value="from Customer")
    public List<Customer> findAll();

   @Query("from Customer  where custName=?1")
    public Customer findName(String custName);

    @Modifying//修改非查询创作
    @Query(value="update Customer set custName=?1 where custId=?2")
    public void update(String custName,Long cusId);


    @Query(value = "SELECT * FROM cst_customer",nativeQuery =true)
    public List<Customer> findSql();

    //方法命名查询
    public Customer findByCustName(String custNaame);

    public List<Customer> findByCustLevelBetween(String numl,String num2);
}
