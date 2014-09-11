package org.agjin.java.tutorial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation의 적용되는 시점. --> runtime 때적용?
 * 이 설정은 spring에서 annotation이 적용되는 시점이기때문에 매우중요.
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * ElementType.TYPE : 클래스 
 * ElementType.METHOD : 메소드
 */
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Controller {
	public String value();
}
