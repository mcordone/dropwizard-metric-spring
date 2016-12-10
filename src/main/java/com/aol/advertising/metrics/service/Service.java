/**
 * Copyright 2016 Miguel Cordones
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aol.advertising.metrics.service;

import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jcordones13 on 11/14/16.
 */
@Component
public class Service {
    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    @Metered(name = "codahale.metric.spring.meter.miguel")
    public void reportMeterMetrics(){
        logger.info("reporting meter metrics...");
        logger.info("------------------ " + System.getenv("DOGSTATSD_PORT_8125_UDP_PORT"));
    }

    @Timed(name = "codahale.metric.spring.timer.miguel")
    public void reportTimerMetrics(){
        logger.info("reporting timer metrics...");
    }

   @Counted(name = "codahale.metric.spring.counter.miguel")
    public void reportCounterMetrics(){
        logger.info("reporting counter metrics...");
    }
}
