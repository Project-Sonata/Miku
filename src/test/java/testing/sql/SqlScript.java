package testing.sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to run the given sql scripts before and after executing the method
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SqlScript {

    String[] beforeTestExecutionLocations() default {};

    String[] afterTestExecutionLocations() default {};

}
