package com.toby.config.autoconfig;

import com.toby.config.ConditionalMyOnClass;
import com.toby.config.MyAutoConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    @Bean(name = "tomcatWebServetFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(final ServerProperties properties) {
        final TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.setContextPath(properties.getPath());
        tomcat.setPort(properties.getPort());
        return tomcat;
    }

}
