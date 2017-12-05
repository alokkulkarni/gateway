package com.example.gateway.gateway;

import com.netflix.client.config.IClientConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;

import static org.apache.http.impl.client.HttpClientBuilder.create;

/**
 * Created by alokkulkarni on 15/10/17.
 */
public class GatewayRibbonLoadBalancingHttpClient extends RibbonLoadBalancingHttpClient {

    private static final Logger log = LoggerFactory.getLogger(GatewayRibbonLoadBalancingHttpClient.class);
    /**
     * @param config
     * @param serverIntrospector
     */
    public GatewayRibbonLoadBalancingHttpClient(IClientConfig config, ServerIntrospector serverIntrospector) {
        super(config, serverIntrospector);
    }


    @Override
    protected org.apache.http.client.HttpClient createDelegate(IClientConfig config) {
        try {
            return httpClient();
        } catch (SSLContextInitError sslContextInitError) {
            sslContextInitError.printStackTrace();
        }
        return null;
    }

    public static CloseableHttpClient httpClient() throws SSLContextInitError {
        TrustSelfSignedStrategy trustStrategy = new TrustSelfSignedStrategy();

        SSLContext sslContext = null;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(trustStrategy).build();
        } catch (GeneralSecurityException e) {
            log.error("Error initializing ssl context.", e);
            throw new SSLContextInitError(e);

        }
        SSLConnectionSocketFactory socketFactory =
                new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        return create().setSSLSocketFactory(socketFactory).build();
    }

}
