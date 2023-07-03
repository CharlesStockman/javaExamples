package com.seleniumexpress.employeeapp.employeeservice.fieignClient;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.env.Environment;

public class MyCustomLoadBalancerConfiguration {
        ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
                Environment environment,
                LoadBalancerClientFactory factory) {
            String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
            RandomLoadBalancer loadBalancer =
                    new RandomLoadBalancer(
                            factory.getLazyProvider(name,
                                    ServiceInstanceListSupplier.class), name);
            return loadBalancer;
        }
}
