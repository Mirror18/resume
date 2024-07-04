package com.itranswarp.scan.primary;

import com.mirror.summer.annotation.Bean;
import com.mirror.summer.annotation.Configuration;
import com.mirror.summer.annotation.Primary;

@Configuration
public class PrimaryConfiguration {

    @Primary
    @Bean
    DogBean husky() {
        return new DogBean("Husky");
    }

    @Bean
    DogBean teddy() {
        return new DogBean("Teddy");
    }
}
