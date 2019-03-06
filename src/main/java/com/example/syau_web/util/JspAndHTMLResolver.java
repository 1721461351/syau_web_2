package com.example.syau_web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * create by wangyu
 * 2018/10/20 0020 10:38
 * <p>
 * 视图解析器
 * <p>
 * 没用上
 */
public class JspAndHTMLResolver {

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Bean
    InternalResourceViewResolver jspViewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(prefix);
        return viewResolver;
    }
}
