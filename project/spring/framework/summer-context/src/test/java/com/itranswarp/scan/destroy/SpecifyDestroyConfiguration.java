package com.itranswarp.scan.destroy;

import com.mirror.summer.annotation.Bean;
import com.mirror.summer.annotation.Configuration;
import com.mirror.summer.annotation.Value;

@Configuration
public class SpecifyDestroyConfiguration {

    @Bean(destroyMethod = "destroy")
    SpecifyDestroyBean createSpecifyDestroyBean(@Value("${app.title}") String appTitle) {
        return new SpecifyDestroyBean(appTitle);
    }
}
