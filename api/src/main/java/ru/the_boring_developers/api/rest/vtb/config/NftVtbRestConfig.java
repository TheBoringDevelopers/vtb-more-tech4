package ru.the_boring_developers.api.rest.vtb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.the_boring_developers.api.rest.vtb.VtbRestTemplate;
import ru.the_boring_developers.api.rest.vtb.properties.VtbProperties;
import ru.the_boring_developers.common.utils.RestTemplateHelper;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class NftVtbRestConfig {

    @Bean
    public VtbRestTemplate fpRestTemplate(VtbProperties vtbProperties, ObjectMapper objectMapper) {
        return new VtbRestTemplate(vtbProperties, RestTemplateHelper.build(vtbProperties, objectMapper));
    }
}

