package cn.gzhennaxia.test.json;

import java.io.Serializable;

/**
 * @author bo li
 * @date 2020-06-05 09:44
 */
public class Test implements Serializable {

    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

//    public String getBoris() {
//        return "Boris";
//    }

    @Override
    public String toString() {
        return "Test{" +
                "isSuccess=" + isSuccess +
                '}';
    }
}
