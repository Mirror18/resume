package com.itranswarp.scan.destroy;

import com.mirror.summer.annotation.Component;
import com.mirror.summer.annotation.Value;

import jakarta.annotation.PreDestroy;

@Component
public class AnnotationDestroyBean {

    @Value("${app.title}")
    public String appTitle;

    @PreDestroy
    void destroy() {
        this.appTitle = null;
    }
}
