package com.jiuxian.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-23 16:04:00
 * Comment:
 */

@ConfigurationProperties(prefix = "jiuxian")
public class JiuxianProperties {
    private String name;
    private String nameCn;
    private String nameEn;
    private String[] hobbies;
    private SexEnum sexEnum;
    private boolean single;
    private School school;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String[] getHobbies() {
        return hobbies;
    }


    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public SexEnum getSexEnum() {
        return sexEnum;
    }

    public void setSexEnum(SexEnum sexEnum) {
        this.sexEnum = sexEnum;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    static class School {
        private Integer no;
        private String name;
        private String address;

        public Integer getNo() {
            return no;
        }

        public void setNo(Integer no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "School{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    enum SexEnum {
        MAN, WOMAN
    }

    @Override
    public String toString() {
        return "JiuxianProperties{" +
                "name='" + name + '\'' +
                ", nameCn='" + nameCn + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", sexEnum=" + sexEnum +
                ", single=" + single +
                ", school=" + school +
                '}';
    }
}
