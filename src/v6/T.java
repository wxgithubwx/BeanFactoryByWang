package v6;

import v6.bean.User;
import v6.spring.ClassPathXMLApplicationContext;
import v6.bean.User;
import v6.spring.ClassPathXMLApplicationContext;

public class T {
    public static void main(String[] args){
        ClassPathXMLApplicationContext cac=new ClassPathXMLApplicationContext("v6/spring/beans.xml");
        User user1 = (User) cac.getBean("user1");
        System.out.println(user1.toString());
    }
}
