package com.aaa.backsystem.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fileName:SaveOrUpdateEntityAnn
 * description:
 * author:gyc
 * createTime:2020/7/22 8:52
 * version:1.0.0
 */
//此注解使用在方法上
@Target(ElementType.METHOD)
//此注解在运行时生效
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveOrUpdateEntityAnn {
    //实体类的类型
    Class<?> entityClass();
}
