package com.jiuxian.import_.annotation;

import com.jiuxian.import_.constant.EnumBeanSelector;
import org.springframework.context.annotation.Import;

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

    EnumBeanSelector value();
}
