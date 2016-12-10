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
package com.aol.advertising.metrics.resource;

import com.aol.advertising.metrics.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jcordones13 on 11/13/16.
 */
@Path("metrics")
public class resource {

    @Autowired
    private Service service;

    @GET
    @Path("meter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportMeterMetrics(){
        service.reportMeterMetrics();

        return Response.ok().build();
    }

    @GET
    @Path("timer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportTimerMetrics(){
        service.reportTimerMetrics();

        return Response.ok().build();
    }

    @GET
    @Path("counter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportCounterMetrics(){
        service.reportCounterMetrics();

        return Response.ok().build();
    }
}
