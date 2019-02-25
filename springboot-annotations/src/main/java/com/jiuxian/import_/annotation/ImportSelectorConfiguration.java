package com.jiuxian.import_.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurationSelector;

import java.lang.annotation.*;

/**
 * Author: jiuxian
 * Date: 2019-02-20 21:34:00
 * Comment:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ImportConfigurationSelector.class)
public @interface ImportSelectorConfiguration {

    String value() default "ABC";
}
