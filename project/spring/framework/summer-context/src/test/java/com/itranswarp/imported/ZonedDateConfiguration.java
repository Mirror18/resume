package com.itranswarp.imported;

import java.time.ZonedDateTime;

import com.mirror.summer.annotation.Bean;
import com.mirror.summer.annotation.Configuration;

@Configuration
public class ZonedDateConfiguration {

    @Bean
    ZonedDateTime startZonedDateTime() {
        return ZonedDateTime.now();
    }
}
