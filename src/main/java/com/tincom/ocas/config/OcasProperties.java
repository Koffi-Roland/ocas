/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tincom.ocas.config;

import com.tincom.ocas.config.OcasProperties.Http.Cache;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 *
 * @author roland
 */
@ConfigurationProperties(prefix = "ocas", ignoreUnknownFields = false)
@Component
public class OcasProperties {
     @Autowired
    private Environment env;

    private final Http http = new Http();
    private final Security security = new Security();
    private final Cache cache = new Cache();
    private final Mail mail = new Mail();
    private final CorsConfiguration cors = new CorsConfiguration();
//   private final Jwt jwt = new Jwt();

    public static class Http {
 
        private final Cache cache = new Cache();

        public Cache getCache() {
            return cache;
        }

        public static class Cache {

            private int timeToLiveInDays = 1461;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Security {

        private final RememberMe rememberMe = new RememberMe();
        private final Jwt jwt = new Jwt();

        public RememberMe getRememberMe() {
            return rememberMe;
        }

        public Jwt getJwt() {
            return jwt;
        }

        public static class RememberMe {

            @NotNull
            private String key = "10213021420";

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }

        public static class Jwt {

            private String secret;
            private Long tokenValidityInSeconds;
            private Long tokenValidityInSecondsForRememberMe;

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public Long getTokenValidityInSeconds() {
                return tokenValidityInSeconds;
            }

            public void setTokenValidityInSeconds(Long tokenValidityInSeconds) {
                this.tokenValidityInSeconds = tokenValidityInSeconds;
            }

            public Long getTokenValidityInSecondsForRememberMe() {
                return tokenValidityInSecondsForRememberMe;
            }

            public void setTokenValidityInSecondsForRememberMe(Long tokenValidityInSecondsForRememberMe) {
                this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
            }

        }
    }
    public static class Mail {

        private String from = "";
        private String baseUrl;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

    }

    public Security getSecurity() {
        return security;
    }

    public Cache getCache() {
        return cache;
    }

    public Http getHttp() {
        return http;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public Mail getMail() {
        return mail;
    }
}
