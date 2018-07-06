package v5.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassPathXMLApplicationContext implements  BeanFactory {
    Map<String,Object> beanMap=new HashMap<>();

    /**
     * 通过构造方法传入beans.xml
     */
    public ClassPathXMLApplicationContext(String xmlPath) {
        readXMLFile(xmlPath);
    }

    /**
     * 读取解析xml
     * @param xmlPath
     */
    public void readXMLFile(String xmlPath) {
        try {
            Document document = new SAXReader().read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
            Element rootElement = document.getRootElement();
            List elements = rootElement.elements();
            System.out.println("子节点个数："+elements.size());
            for (int i=0;i<elements.size();i++){
                Element bean=(Element) elements.get(i);
                String id = bean.attributeValue("id");
                String classValue = bean.attributeValue("class");
                System.out.println(id+"   "+classValue);
                Object o = Class.forName(classValue).newInstance();
                beanMap.put(id,o);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
