package it.eng.care.domain.flow.jobs.look;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface LockEntity {

	public String operation();

	public String property();

	public int arg() default 0;
	
	public int alternativeArg() default 0;

	/**
	 * Millisecondi di durata del Lock, dopo i quali altre operazioni lo potranno
	 * usare
	 * 
	 * @return
	 */
	public int until() default -1;

	public String message() default "E' in corso un altra operazione su questa entità riprovare in un secondo momento";

}
