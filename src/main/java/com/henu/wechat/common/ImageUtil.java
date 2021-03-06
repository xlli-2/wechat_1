package com.henu.wechat.common;

import com.baidu.aip.ocr.AipOcr;
import com.henu.wechat.bean.BaseMessage;
import com.henu.wechat.bean.TextMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

public  class ImageUtil {
    public static final String APP_ID = "18185355";
    public static final String API_KEY = "fOEf9PmfgVflGNC2oCGbG61o";
    public static final String SECRET_KEY = "DvSrsqeoR1jlLdqk5bqy6QeWEDfG9K7G";

    public static BaseMessage getContent(Map<String, String> requestMap) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneralUrl(requestMap.get("PicUrl"),new HashMap<String, String>());
        JSONArray words = res.getJSONArray("words_result");
        StringBuffer sb = new StringBuffer();
        for (Object word : words) {
            JSONObject t = new JSONObject(word.toString());
            sb.append(t.get("words")).append("\n");
        }
        System.out.println(sb);
        return new TextMessage(requestMap, "text", sb.toString());
    }
}
