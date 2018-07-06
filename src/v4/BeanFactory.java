package v4;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 工厂类
 */
public class BeanFactory {
   /*<bean id="bookDao" class="v4.BookDaoImpl2"></bean>*/
    //解析xml
    public static Object getBean(String id){
        try {
            //需要导包
            Document doc = new SAXReader().read(new FileInputStream("src/v4/beans.xml"));
            //获取节指定的bean节点
            Element element = (Element) doc.selectSingleNode("/beans/bean[@id='" + id + "']");
            //获取到bean节点的class属性
            String className = element.attributeValue("class");
            Object o = Class.forName(className).newInstance();
            return o;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
