package com.haryanaindustries.api.configuration.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:system.properties"})
public class SystemConfiguration {

    @Autowired
    Environment environment;

    private String getType() {
        return environment.getProperty("system.type");
    }

    private String getHost() {
        return environment.getProperty(getType() + "." + "host");
    }

    public String getServiceUrl(String service) {
        return getHost() + environment.getProperty(getType() + "." + service.toLowerCase() + "." + "context");
    }

}
