package com.learn.base;

/**
 * @author: yanzx
 * @date: 2019/08/04 17:20
 */
@FunctionalInterface
public interface IBase {

    /**
     * zz
     */
    void myAbsMethod();

    /**
     * zz
     * @return
     */
    @Override
    String toString();

    /**
     * default method
     */
    default void defaultMethod(){
        System.out.println("zz");
    }

}
