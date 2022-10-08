package ru.the_boring_developers.common.entity.property;


public interface RestProperties {

    String getBaseUrl();

    int getConnectTimeout();

    int getConnectRequestTimeout();

    int getPoolSize();
}
