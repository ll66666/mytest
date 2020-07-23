package CurdTest;

import com.demo.dao.CustomerDao;
import com.demo.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.java2d.pipe.SpanClipRenderer;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")//再执行某些方法之前
public class SpecTest {
    @Autowired
    CustomerDao customerDao;

    @Test
//条件构造器
    public  void testSpecifcation(){
        //使用匿名
        Specification<Customer> sp=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //like的关键字 custName like ? CriteriaBuilder构建关键字
                return criteriaBuilder.like(root.get("custName").as(String.class),"张%");
            }
        };
        //调用查询
        List<Customer> customer=customerDao.findAll(sp);
        System.out.println(customer);
    }
    //精准匹配查询
    @Test
    public void specFind(){
        Specification<Customer> spec=new Specification<Customer>() {
            //封装自定义条件
            //where custName=?
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
              //获取比较的属性,查询的属性
                Path<Object> custName=root.get("custName");
                /*构建查询条件 当前比较值的属性
                第一个查询需要比较的属性
                第二个: 当前需要比较的取值
                */
                Predicate predicate=criteriaBuilder.equal(custName,"张三");

                return predicate;
            }
        };
        Customer customers=customerDao.findOne(spec);
        System.out.println(customers);
    }

    //多条件拼装
    //where custName=? AND custLevel=?
    public  void testSpec(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               //获取比较的属性
                Path<Object> custName=root.get("custName");
                Path<Object> custIndustry=root.get("custIndustry");
                Path<Object> custLevel=root.get("custLevel");
                //查询构建条件
                Predicate p=criteriaBuilder.equal(custName,"张三");
                Predicate p1=criteriaBuilder.equal(custIndustry,"物流");
                Predicate p2=criteriaBuilder.equal(custLevel,"1");
                //第三步将多个组合起来
                Predicate and=criteriaBuilder.and(p,p1);
                Predicate or=criteriaBuilder.or(and,p2);
                return or;
            }
        };
        List<Customer> customer=customerDao.findAll(specification);
        System.out.println(customer);
    }
    @Test
    public  void testLike(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //cust_name like "x
                // 获取查询的属性
                Path<Object> custName=root.get("custName");
                //拼接查询方式

                Predicate like=criteriaBuilder.like(custName.as(String.class),"张%");

                return like;
            }
        };
        List<Customer>list=customerDao.findAll(specification);
        System.out.println(list);
        //添加排序
        //倒序Sort.Direction.DESC ASC升
         //获取升序 被排序的字段 没有这种方向 根据年龄或其他else
        Sort sort=new Sort(Sort.Direction.DESC,"custId");
        List<Customer> lsit1=customerDao.findAll(specification,sort);
        System.out.println(lsit1);
    }
    @Test
   public void testPage(){
       Specification specification=null;
        //创建分页对象 PageReuqest对象
       //从第条开始
       PageRequest pageRequest=new PageRequest(0,2);

       Page<Customer> page=customerDao.findAll(null,pageRequest);
       //specificaiton 没有条件 自动省略
       //获取到数据的集合列表的总和数
       System.out.println(page.getContent());
       //获取总条数
       System.out.println(page.getTotalElements());
       //总页数
       System.out.println(page.getTotalPages());
    }
}
