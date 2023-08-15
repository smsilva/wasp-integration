package com.github.smsilva.wasp.integration.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleStringLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStringLogger.class);

    public void handle(String message) {
        LOGGER.info("message: {}", message);
    }

}
