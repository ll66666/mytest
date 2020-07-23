package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.dao.LinkManDao;
import com.demo.entity.Customer;
import com.demo.entity.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class OnetoManyTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(false) //设置为步回滚
    public void TestAdd(){
        Customer c=new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        LinkMan l=new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("女");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");
           c.getLinkmans().add(l);
           l.setCustomer(c);
           customerDao.save(c);
           linkManDao.save(l);
    }
    @Test
    @Transactional
    @Rollback(false) //设置为步回滚
    public void testDelete(){
       // linkManDao.delete(3L);
        customerDao.delete(4L);

    }
 //级联添加
 @Test
 @Transactional
 @Rollback(false)
    public void testAdd(){
        Customer c=new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        LinkMan l=new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("女");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");

        c.getLinkmans().add(l);
        l.setCustomer(c);
        customerDao.save(c);    }
}
