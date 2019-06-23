package com.bigfire.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ IDE    ：Eclipse
 * @ Author ：dahuo
 * @ Date   ：2019/4/16 16:15
 * @ Addr   ：China ZhengZhou
 * @ email  ：835476090@qq.com
 */
public class BeanToMap {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;

        T obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            if (field.getName().toLowerCase().contains("date")){
                Object temobj = field.get(obj);

//                Date temdate = (Date) temobj;
//                String datestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(temdate);
//                map.put(field.getName(), datestr);

                if (temobj instanceof Date){
                    Date temdate = (Date) temobj;
                    String datestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(temdate);
                    System.out.println(field.getName()+"----"+temdate+"----"+datestr);
                    map.put(field.getName(), datestr);
//                    if (field.getName().equals("effectiveDateEnd")){
//                        System.out.println(field.getName()+"----"+temdate+"----"+datestr);
//                        map.put(field.getName(), datestr);
//                    }
                }else {
                    map.put(field.getName(), field.get(obj));
                }
            }else {
                map.put(field.getName(), field.get(obj));
            }
        }
        return map;
    }

}
