package com.gestor.common.annotations;


public @interface RequestMapped {
	public boolean customType() default false;
	public boolean required() default true;
	public String regexp();
}
