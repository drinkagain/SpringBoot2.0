package com.jiuxian.import_.annotation;

import com.jiuxian.import_.constant.EnumBeanSelector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Author: jiuxian
 * Date: 2019-02-25 23:04:00
 * Comment:
 */

public class ImportConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ImportSelectorConfiguration.class.getName());
        EnumBeanSelector selectorEnum = EnumBeanSelector.valueOf(annotationAttributes.get("value").toString());
        switch (selectorEnum) {
            case A:
                return new String[]{ AConfiguration.class.getName() };
            case B:
                return new String[]{ BConfiguration.class.getName() };
            default:
                return null;
        }
//        AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(importingClassMetadata, annType);
//
//        if (attributes == null) {
//            throw new IllegalArgumentException(String.format(
//                    "@%s is not present on importing class '%s' as expected",
//                    annType.getSimpleName(), importingClassMetadata.getClassName()));
//        }f
    }
}
