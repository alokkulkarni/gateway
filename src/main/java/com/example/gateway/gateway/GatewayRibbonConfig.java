package com.example.gateway.gateway;

import com.netflix.client.RetryHandler;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alokkulkarni on 15/10/17.
 */
@Configuration
public class GatewayRibbonConfig {
    private static final String NAME = "gateway";

//    @Bean
//    public RibbonLoadBalancingHttpClient ribbonLoadBalancingHttpClient(IClientConfig config,
//                                                                       ServerIntrospector serverIntrospector, ILoadBalancer loadBalancer, RetryHandler retryHandler) {
//        GatewayRibbonLoadBalancingHttpClient client =
//                new GatewayRibbonLoadBalancingHttpClient(config, serverIntrospector);
//        client.setLoadBalancer(loadBalancer);
//        client.setRetryHandler(retryHandler);
////        Monitors.registerObject("Client_" + NAME, client);
//        return client;
//    }
}
