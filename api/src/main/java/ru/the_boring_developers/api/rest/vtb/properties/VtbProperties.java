package ru.the_boring_developers.api.rest.vtb.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import ru.the_boring_developers.common.entity.property.RestProperties;

/** Свойства для настройки фискального процессинга */
@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "vtb-nft")
public class VtbProperties implements RestProperties {

    /** Адрес сервиса */
    private String baseUrl;
    /** Таймаут соединения. По-умолчанию 5000мс */
    private int connectTimeout;
    /** Таймаут запроса. По-умолчанию 5000мс */
    private int connectRequestTimeout;
    /** Размер пула запроса. По-умолчанию 20 */
    private int poolSize;
}
