package com.jiuxian.springel;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * Author: jiuxian
 * Date: 2019-01-03 22:01:00
 * Comment:
 */


@Configuration
public class ElConfig {
    /**
     * 注入普通字符串
     */
    @Value("I Love Java")
    private String normal;
    /**
     * 注入操作系统属性
     */
    @Value("#{systemProperties['os.name']}")
    private String osName;
    /**
     * 注入表达式结果
     */
    @Value("#{T(java.lang.Math).random()*100 }")
    private double random;
    /**
     * 注入其它Bean的属性(其它Bean的属性需要get set 方法，或者 public)
     */
    @Value("#{otherService.another}")
    private String fromAnother;
    /**
     * 注入文件
     */
    @Value("classpath:/test/test.txt")
    private Resource testFile;
    /**
     * 注入网址
     */
    @Value("http://www.baidu.com")
    private Resource testUrl;
    /**
     * 注入配置文件
     */
    @Value("${book.name}")
    private String userName;
    /**
     * 注入配置文件
     */
    @Autowired
    private Environment environment;

    public void getSecret() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(random);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream(), "utf-8"));
            System.out.println(IOUtils.toString(testUrl.getInputStream(), "utf-8"));
            System.out.println(userName);
            //注入配置文件
            System.out.println(environment.getProperty("book.name"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
