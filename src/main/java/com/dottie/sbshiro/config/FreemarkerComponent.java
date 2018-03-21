package com.dottie.sbshiro.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * @author lindazhong
 * @date 2018/3/20 16:25
 * 继承FreeMarkerConfigurer类,重写afterPropertiesSet()方法；
 * 集成shiroTags标签
 */
@Component
public class FreemarkerComponent {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        System.out.println(freeMarkerConfigurer.getConfiguration());
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
