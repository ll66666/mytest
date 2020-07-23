package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.entity.Customer;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")//再执行某些方法之前
public class CURDTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testSave(){
        Customer customer=new Customer();
        customer.setCustName("张三");
        customerDao.save(customer);
    }
//修改 查询2的id进行修改
   /* @Test
    public void testUpdate(){
        Customer customer=customerDao.findOne(2L);

        customer.setCustName("张三");
        customer.setCustAddress("广东");
        customerDao.save(customer);
    }*/

@Test
    public void testDelete(){
        customerDao.delete(1L);
    }
    //查询全部
    @Test
    public void testAll(){
        List<Customer> list=customerDao.findAll();
        System.out.println(list);
    }
    //根据条件查询 ?代表的占位符 开始值1 对应参数的索引
@Test
    public void testOne(){
        Customer customer=customerDao.findName("张三");
        System.out.println(customer);
    }

@Test
@Transactional(rollbackFor = Exception.class)
    public void testupdate(){
        customerDao.update("李四",2L);
    }


    @Test
    public void findSQL(){
     List<Customer> list=customerDao.findSql();
     System.out.println(list);
    }

    @Test
    public void testrule(){
    /* Customer customer=customerDao.findByCustName("张三");
     System.out.println(customer);*/
    List<Customer> list=customerDao.findByCustLevelBetween("1","2");
    System.out.println(list);
    }



}
