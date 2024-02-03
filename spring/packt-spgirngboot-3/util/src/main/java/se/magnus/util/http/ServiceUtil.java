package se.magnus.util.http;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServiceUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceUtil.class);
    private final String port;
    private String serviceAddress = null;

    @Autowired
    public ServiceUtil(@Value("${server.port}") String port) {
        this.port = port;
    }

    public String getServiceAddress() {
        serviceAddress =  ( serviceAddress == null ) ? findMyHostName() + "/" + findMyIpAddress() + ":" + port : serviceAddress;
        return serviceAddress;
    }

    private String findMyHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch(UnknownHostException e) {
             return "Unknown Host Name with exception message " + e.getMessage();
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch(UnknownHostException e) {
            return "Unknown IP Address with exception message " + e.getMessage();
        }
    }

}
