package run.boring.handler.annotation;

import run.boring.handler.config.InputType;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigField {

    InputType category() default InputType.StringType;

    int status() default 0;

    String name() default "";

    String description() default "";

    String options() default "";

    boolean readonly() default false;

    int order() default 0;
}