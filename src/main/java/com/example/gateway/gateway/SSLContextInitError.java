package com.example.gateway.gateway;

import java.security.GeneralSecurityException;

/**
 * Created by alokkulkarni on 15/10/17.
 */
public class SSLContextInitError extends Throwable {
    public SSLContextInitError(GeneralSecurityException e) {
    }
}
