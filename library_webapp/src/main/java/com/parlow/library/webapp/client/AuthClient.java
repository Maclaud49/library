package com.parlow.library.webapp.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthClient {
    private static final Logger logger = LogManager.getLogger(AuthClient.class);

    public static void main(String[] args) {
        AuthImplService authService = new AuthImplService();
        AuthI save = authService.getAuthImplPort();


        logger.info(save.register("Maclaud1", "AvecHonneur", "mac@parlow-co.com", "Admin"));

    }

}
