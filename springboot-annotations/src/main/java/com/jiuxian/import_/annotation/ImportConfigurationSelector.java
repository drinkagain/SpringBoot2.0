package com.jiuxian.import_.annotation;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

/**
 * Author: jiuxian
 * Date: 2019-02-25 23:04:00
 * Comment:
 */

public class ImportConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Class<?> annType = GenericTypeResolver.resolveTypeArgument(getClass(), ImportConfigurationSelector.class);
        Assert.state(annType != null, "Unresolvable type argument for AdviceModeImportSelector");

        AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(importingClassMetadata, annType);

        if (attributes == null) {
            throw new IllegalArgumentException(String.format(
                    "@%s is not present on importing class '%s' as expected",
                    annType.getSimpleName(), importingClassMetadata.getClassName()));
        }
        return new String[0];
    }
}
