package com.github.smsilva.wasp.integration.control;

import com.github.smsilva.wasp.integration.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleStringLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStringLogger.class);

    @Autowired
    PeopleService people;

    public void handle(String message) {
        List<People> list = people.list();
        LOGGER.info("message: {}, list: {}", message, list);
    }

}
