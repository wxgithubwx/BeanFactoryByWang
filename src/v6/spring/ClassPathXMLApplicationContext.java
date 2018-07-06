package v6.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Method;
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
                //System.out.println(id+"   "+classValue);
                Class clazz=Class.forName(classValue);
                Object o = clazz.newInstance();
                beanMap.put(id,o);
                //获取bean属性
                List<Element> elements1 = bean.elements();
                for (Element p:elements1) {

                    String name = p.attributeValue("name");
                    String value = p.attributeValue("value");
                    String ref = p.attributeValue("ref");
                    //将值设到对象里面
                    //获取到类中所有的方法
                    Method[] methods = clazz.getMethods();
                    for (Method m:methods) {
                        //判断方法是否是set方法，并且包含此个属性
                        String methodName = m.getName();
                        if(methodName.contains("set")&&methodName.toUpperCase().contains(name.toUpperCase())){
                        //注入的是基本类型
                            if(ref==null){
                                //获取get方法参数类型
                                Class ParameterType = m.getParameterTypes()[0];
                                if(ParameterType.equals(int.class)){
                                    m.invoke(o,Integer.parseInt(value));
                                }else if(ParameterType.equals(String.class)){
                                    m.invoke(o,value);
                                }
                            }else{
                                Object bean1 = getBean(ref);
                                Method method = clazz.getMethod(methodName, bean1.getClass());
                                method.invoke(o,bean1);//这一句有点难以理解
                                /*如果传入的是user对象时，获取的是User类的全部方法，
                                * 而此时恰好解析到phone这个属性，获取到User类的setPhone方法，设置phone属性
                                * */
                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
