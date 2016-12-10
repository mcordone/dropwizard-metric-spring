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

/**
 * Created by jcordones13 on 10/31/16.
 */
public enum Environment {
    /**
     * PROD
     */
    PROD("prod"),

    /**
     *  QAI
     */
    QAI("qai"),

    /**
     * DEV (mostly localhost)
     */
    DEV("dev");

    Environment(String env) {
        this.env = env;
    }

    private String env;

    public String env() {
        return env;
    }
}
