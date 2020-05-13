package cn.gzhennaxia.test.lang.object;

/**
 * @author bo li
 * @date 2020-05-13 11:51
 */
class Test2 {

    private int id;
    private String name;

    Test2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Test2) {
            Test2 test2 = (Test2) obj;
            return this.id == test2.id && this.name.equals(test2.name);
        }
        return false;
    }

}
