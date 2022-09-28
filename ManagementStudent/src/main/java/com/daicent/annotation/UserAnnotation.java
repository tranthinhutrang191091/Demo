package com.daicent.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
public @interface UserAnnotation {
	String idUser () default "";
	String userName() default "";
	String password() default "";
	String idStudent () default "";
}
