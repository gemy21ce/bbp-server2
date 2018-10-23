package com.bbpl.server2.web;

import com.bbpl.server2.model.CalcInput;
import com.bbpl.server2.servcie.Calculator;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/operation")
@Slf4j
public class OperationController {

    private final Calculator calculator;

    public OperationController(Calculator calculator) {
        this.calculator = calculator;
    }

    @RequestMapping(value = "/process", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalcInput> process(@Valid @RequestBody CalcInput data) {
        log.debug("processing {}", data.toString());
        return ResponseEntity.ok(calculator.process(data));
    }
}
