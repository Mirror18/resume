package com.itranswarp.scan.init;

import com.mirror.summer.annotation.Bean;
import com.mirror.summer.annotation.Configuration;
import com.mirror.summer.annotation.Value;

@Configuration
public class SpecifyInitConfiguration {

    @Bean(initMethod = "init")
    SpecifyInitBean createSpecifyInitBean(@Value("${app.title}") String appTitle, @Value("${app.version}") String appVersion) {
        return new SpecifyInitBean(appTitle, appVersion);
    }
}
