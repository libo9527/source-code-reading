package cn.gzhennaxia.test.io.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * @author bo li
 * @date 2020-06-04 10:31
 */
public class Test1 implements Externalizable {

    private int id;

    private String name;

    /**
     * 通过实现 Externalizable 接口的方式实现序列化时
     * 如果类没有空构造器，反序列化时则会报错：
     * java.io.InvalidClassException: cn.gzhennaxia.test.io.externalizable.Test1; no valid constructor
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 14:39
     */
    public Test1() {
    }

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

    /**
     * 通过实现 Externalizable 接口的方式实现序列化时
     * 如果没有实现接口的 writeExternal() 方法，则会将 fields 序列化为默认值(int:0 Object:null)
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 14:43
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id);
        out.writeObject(name);
    }

    /**
     * 通过实现 Externalizable 接口的方式实现序列化时
     * 如果没有实现接口的 readExternal() 方法，则会将 fields 反序列化为默认值(int:0 Object:null)
     *
     * @author bli@skystartrade.com
     * @date 2020-06-04 14:43
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = in.readInt();
        name = (String) in.readObject();
    }
}
