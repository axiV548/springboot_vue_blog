package com.axiv548.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test
 *
 * @date 2021/3/5 18:41
 */
public class test {

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap();
        map1.put("count(tag_id)", "1");
        map1.put("tag_id", "1");
        Map<String, String> map2 = new HashMap();
        map2.put("count(tag_id)", "2");
        map2.put("tag_id", "2");
        List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
        list1.add(map1);
        list1.add(map2);
        System.out.println(list1);

        List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();


        for (int i = 0; i < list1.size(); i++) {
            Map<String, String> map = list1.get(i);
            map.put("wd", "4");
            System.out.println(map);

//            String authorStr = map.get("tag_id").toString();
//            System.out.println("author" + " : " + authorStr);
        }
    }


}
