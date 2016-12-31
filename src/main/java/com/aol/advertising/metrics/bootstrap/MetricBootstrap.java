/**
 * AOL CONFIDENTIAL INFORMATION.
 *
 * Date: Apr 08, 2015
 *
 * Copyright 2015 AOL, Inc.
 *
 * All Rights Reserved.  Unauthorized reproduction, transmission, or
 * distribution of this software is a violation of applicable laws.
 */
package com.aol.advertising.metrics.bootstrap;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.coursera.metrics.datadog.DatadogReporter;
import org.coursera.metrics.datadog.transport.HttpTransport;
import org.coursera.metrics.datadog.transport.Transport;
import org.coursera.metrics.datadog.transport.UdpTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * Startup service
 */
@Configuration
@EnableMetrics(proxyTargetClass=true)
public class MetricBootstrap extends MetricsConfigurerAdapter {

    private static final int STAT_FREQ = 1;

    private static final Logger logger = LoggerFactory.getLogger(MetricBootstrap.class);

    @Autowired
    private MetricConfig mConfig;

    /**
     * configureReporters.
     * @param  metricRegistry
     */
    @Override
    public void configureReporters(final MetricRegistry metricRegistry) {
        logger.info("************************* METRICS ************************");

        switch (mConfig.getReporterType()) {
            case DATADOG:
                logger.info("--------------- DATADOG --------------- ");
                //expansions
                EnumSet expansions = EnumSet.of(DatadogReporter.Expansion.COUNT,
                        DatadogReporter.Expansion.RATE_1_MINUTE,
                        DatadogReporter.Expansion.RATE_5_MINUTE,
                        DatadogReporter.Expansion.RATE_15_MINUTE,
                        DatadogReporter.Expansion.MEDIAN,
                        DatadogReporter.Expansion.MAX,
                        DatadogReporter.Expansion.MIN,
                        DatadogReporter.Expansion.P95,
                        DatadogReporter.Expansion.P999);

                //dataDog transport
                Transport transport = createTransport();

                DatadogReporter.Builder DDReporterBuilder = DatadogReporter.forRegistry(metricRegistry)
                        .withTransport(transport)
                        .withExpansions(expansions)
                        .withTags(mConfig.getTags())
                        .convertRatesTo(TimeUnit.SECONDS)
                        .convertDurationsTo(TimeUnit.MILLISECONDS);

                //For AWS only
                try {
                    Platform platform = mConfig.getPlatform();
                    if(platform == Platform.AWS)
                        DDReporterBuilder.withEC2Host();

                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }

                //build and start DD reporter
                DDReporterBuilder.build()
                        .start(STAT_FREQ, TimeUnit.MINUTES);

                break;

            case CONSOLE:
                logger.info("--------------- CONSOLE --------------- ");
                ConsoleReporter.forRegistry(metricRegistry)
                        .convertRatesTo(TimeUnit.SECONDS)
                        .convertDurationsTo(TimeUnit.MILLISECONDS)
                        .build().start(STAT_FREQ, TimeUnit.MINUTES);
                break;
            default:
        }
    }

    /**
     *
     * @return either UDP or HTTP Transport
     */
    private Transport createTransport(){
        Transport transport = null;

        switch (mConfig.getMetricProtocol()) {
            case UDP:
                transport = new UdpTransport.Builder()
                        .withStatsdHost(System.getenv("DOGSTATSD_PORT_8125_UDP_ADDR"))
                        .withPort(Integer.valueOf(System.getenv("DOGSTATSD_PORT_8125_UDP_PORT")))
                        .build();
                break;

            case HTTP:
                transport = new HttpTransport.Builder()
                        .withApiKey(mConfig.getApiKey())
                        .build();
                break;
        }

        return transport;
    }
}
