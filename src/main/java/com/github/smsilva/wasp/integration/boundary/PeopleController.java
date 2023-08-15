package com.github.smsilva.wasp.integration.boundary;

import com.github.smsilva.wasp.integration.control.PeopleService;
import com.github.smsilva.wasp.integration.control.SimpleStringLogger;
import com.github.smsilva.wasp.integration.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("people")
public class PeopleController {

    @Autowired
    PeopleService people;

    @GetMapping
    public ResponseEntity<?> home() {
        List<People> list = people.list();

        return ResponseEntity.ok(list);
    }

}
