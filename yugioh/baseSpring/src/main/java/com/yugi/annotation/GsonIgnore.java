package com.yugi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yugi
 * @apiNote 此注解用于Gson转换时候需要忽略的属性
 * @since 2017-06-20
 */
// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)
//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Target({ElementType.FIELD, ElementType.METHOD})
//说明该注解将被包含在javadoc中
@Documented
public @interface GsonIgnore {
}