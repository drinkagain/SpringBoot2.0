package com.jiuxian.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-23 16:04:00
 * Comment:
 */
@Data
@ConfigurationProperties(prefix = "jiuxian")
public class JiuxianProperties {

    private String name;
    private String nameCn;
    private String nameEn;
    private String[] hobbies;
    private SexEnum sexEnum;
    private boolean single;

    @NestedConfigurationProperty
    private School school;

    private City city;

    @DeprecatedConfigurationProperty(replacement = "jiuxian.name-cn", reason = "deprecated")
    public String getName() {
        return getNameCn();
    }

    public void setName(String name) {
        setNameCn(name);
    }

    enum SexEnum {
        MAN, WOMAN
    }

    @Data
    static class City {
        private String no;
        private String name;
    }
}
