package v5;

import v5.bean.User;
import v5.spring.ClassPathXMLApplicationContext;

public class T {
    public static void main(String[] args){
        ClassPathXMLApplicationContext cac=new ClassPathXMLApplicationContext("v5/spring/beans.xml");
        User user2 = (User) cac.getBean("user2");
        System.out.println(user2.toString());
    }
}
