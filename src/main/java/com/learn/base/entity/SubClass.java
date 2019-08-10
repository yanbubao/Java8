package com.learn.base.entity;

import com.learn.base.IBase;

/**
 * @author: yanzx
 * @date: 2019/08/10 17:52
 */
public class SubClass implements IBase {
    @Override
    public void myAbsMethod() {

    }

    @Override
    public void defaultMethod() {
        IBase.super.defaultMethod();
    }
}
