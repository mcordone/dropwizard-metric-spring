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
package com.aol.advertising.metrics.bootstrap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcordones13 on 11/13/16.
 */
public class MetricConfig {
    private ReporterType reporterType;

    private List<String> tags;

    private String apiKey;

    private Environment environment;

    /**
     * [getReporterType description]
     * @return ReporterType
     */
    public ReporterType getReporterType() {
        return reporterType;
    }

    /**
     * [setReporterType description]
     * @param reporterType ReporterType
     */
    public void setReporterType(final ReporterType reporterType) {
        this.reporterType = reporterType;
    }

    /**
     * [setTags description]
     * @param tags [description]
     */
    public void setTags(final String tags) {
        List<String> tagList = new ArrayList<String>();

        for (String pairs : tags.split(",")) {
            tagList.add(pairs);
        }

        this.tags = tagList;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
