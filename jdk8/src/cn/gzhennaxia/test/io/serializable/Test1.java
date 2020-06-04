package cn.gzhennaxia.test.io.serializable;

import java.io.Serializable;

/**
 * @author bo li
 * @date 2020-06-04 10:00
 */
public class Test1 implements Serializable {

    private int id;

    private String name;

    public Test1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
