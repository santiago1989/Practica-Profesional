package com.gestor.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapped {
	public boolean customType() default false;
	public Class customTypeClass() default Object.class;
	public boolean required() default true;
	public String regexp();
	public String idName() default "id";
	public Class idClass() default Integer.class;
}
