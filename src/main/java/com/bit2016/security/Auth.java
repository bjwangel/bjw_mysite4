package com.bit2016.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	String role() default "user";   // 기본 권한을 일반 user로 설정 
//	boolean required() default false; // required 기본을 false로
}
