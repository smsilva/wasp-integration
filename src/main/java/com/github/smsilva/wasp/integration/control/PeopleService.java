package com.github.smsilva.wasp.integration.control;

import com.github.smsilva.wasp.integration.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleService.class);

    public List<People> list() {
        LOGGER.info("here");
        return new ArrayList<>();
    }

}
