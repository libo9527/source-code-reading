package cn.gzhennaxia.test.json;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author bo li
 * @date 2020-06-05 09:42
 */
public class JsonTest {

    public static void main(String[] args) throws JsonProcessingException {
        Test test = new Test();
        test.setSuccess(true);

        System.out.println("Serializable Result With fastjson-1.2.58 :" + JSON.toJSONString(test));

        System.out.println("Serializable Result With Gson-2.8.5 :" + new Gson().toJson(test));

        System.out.println("Serializable Result With jackson-2.10.0 :" + new ObjectMapper().writeValueAsString(test));

        System.out.println(new Gson().fromJson(JSON.toJSONString(test), Test.class));
    }
}
