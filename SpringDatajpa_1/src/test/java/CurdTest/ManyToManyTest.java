package CurdTest;

import com.demo.entity.Role;
import com.demo.dao.RoleDao;
import com.demo.dao.UsersDao;
import com.demo.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ManyToManyTest {//
    //自动注入
    @Autowired
    UsersDao userDao;
    @Autowired
    RoleDao roleDao;


    @Test
    @Transactional     // 开启事务
    @Rollback(false)  // 不回滚
    public   void  testAdd(){

        //  创建  2个用户  3 个角色
        Users  users1 =  new Users();
        users1.setUsername("张三1");

        Role   role  =  new Role();
        role.setRoleName("角色1");


        //  建立关联关系
        users1.getRoles().add(role);
        role.getUsers().add(users1);


        userDao.save(users1);
       // roleDao.save(role);

    }


    @Test
    @Transactional
    @Rollback(false)
    public  void  testDelete(){
        // 删除ID 为 6 的数据
        userDao.delete(6);
    }


}
