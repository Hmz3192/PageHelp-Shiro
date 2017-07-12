package com.hmz.ssm.util.Translate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2017/7/7.
 */
public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);

        String res = HttpGet.get(TRANS_API_HOST, params);
        String[] res_1 = res.split(",");//根据”，”符号分成数组
        int length = 0;
        //计算前三个的长度，为了避免在翻译的句子中包含”，“这个符号混淆了。如“你好，中国”。
        for (int i = 0; i < 3; i++) {
            length += res_1[i].length();
        }
        //开始的位置加三是算上最开始句子的三个”，“，加7是为了减少后面的，结束为止-4。
        String res_2 = res.substring(length +10, res.length() - 4);
        return res_2;
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
