/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Apr 29, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.aol.advertising.metrics.bootstrap;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;

/**
 * 
 * @author jcordones13
 *
 */
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    
    private Logger logger = LoggerFactory.getLogger(JerseyConfig.class);
    
    public JerseyConfig() throws ServletException {
        logger.info("\n***************** JERSEY CONFIGURING ********************\n");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        
        register(RequestContextFilter.class);
        packages("com.aol.advertising.metrics");
        register(LoggingFilter.class);
    }
}


