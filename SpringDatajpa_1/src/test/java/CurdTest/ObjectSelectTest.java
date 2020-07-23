package CurdTest;


import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.entity.Customer;
import com.demo.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ObjectSelectTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    public void testFindById(){

       Customer customer=customerDao.findOne(5l);
        // 能获取到当前对象的联系人 ,则说明查询
       /* Set<LinkMan> set=customer.getLinkmans();
        for(LinkMan li: set ){
            System.out.println(li);
        }*/

        //根据联系人id获取当前联系人的客户
      /*  LinkMan linkMan=linkManDao.findOne(4L);
        Customer customer1=linkMan.getCustomer();
        System.out.println(customer1);*/
    }
     //根据 客户的id获取所有的数据 (所有的联系人数据)


}
