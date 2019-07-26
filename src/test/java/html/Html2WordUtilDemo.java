package html;

import java.util.HashMap;
import java.util.Map;

public class Html2WordUtilDemo {

    public static void main(String[] args) {
        String path = "E:/fj_transform/html/html2world";
        String temName = "sdfs_dsr.html";
        String redTemName = "sdfs_dlr.html";
        //String redTemName = "";
        String desDoc = "E:/fj_transform/html/html2world/create.doc";
        Map<String,Object> data = new HashMap<>();
        data.put("ah","（2019）粤01民初1号");
        data.put("dsr","liujianko");
        data.put("sjhm","13570775522");
        data.put("yjdz","广州市海珠区广州塔81楼");
        data.put("today","2019-07-25");
        data.put("dlr","我是律师");
        data.put("dlrsjhm","13900005522");
        data.put("dlryjdz","广州市天河区正佳广场4楼");
        String result = Html2WordUtil.html2Word(path, temName, desDoc, data);
        //String result2 = Html2WordUtil.html2Word2(path, temName, redTemName, desDoc, data);
        System.out.println(result);
        //System.out.println(result2);
    }
}
