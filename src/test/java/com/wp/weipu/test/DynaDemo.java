package com.wp.weipu.test;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author zwp
 */

public class DynaDemo {

    /**
     * 不变属性
     */
    private String name;
    /**
     * 实体
     */
    private Object object=null;

    /**
     * 动态属性
     */
    private BeanMap beanMap =null;

    public DynaDemo() {
        super();
    }

    public DynaDemo(Map propertyMap) {

        this.object =generateBean(propertyMap);
        this.beanMap=BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     * @param
     * @return
     */

    public void setValue(String propert,Object value){
        beanMap.put(propert,value);
    }
//
//    /**
//     * 通过属性名获取属性值
//     * @param property
//     * @return
//     */
//    public Object getValue(String property){
//        return beanMap.get(property);
//    }
//
//    public Object getObject(){
//        return this.object;
//    }

    private Object generateBean(Map propertMap){
        BeanGenerator beanGenerator=new BeanGenerator()
;
        Set ketSet=propertMap.keySet();

        for (Iterator i = ketSet.iterator();  i.hasNext();){
            String key= (String) i.next();
            beanGenerator.addProperty(key,(Class) propertMap.get(key));
        }
        return beanGenerator.create();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(beanMap);
        return sb.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Map p=new HashMap<>();
        for (int i=0;i<3;i++){
            p.put("unit"+i, Class.forName("java.lang.String"));
            p.put("unitprice"+i,Class.forName("java.lang.String"));
            p.put("total"+i,Class.forName("java.lang.String"));
        }
        DynaDemo dynaDemo=new DynaDemo(p);

        System.out.printf(dynaDemo.toString());


    }
}
