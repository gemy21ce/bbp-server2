package com.bbpl.server2.servcie.impl;

import com.bbpl.server2.model.CalcInput;
import com.bbpl.server2.model.Operation;
import com.bbpl.server2.servcie.Calculator;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasicCalculator implements Calculator {

    @Override
    public CalcInput process(CalcInput input) {

        log.debug("Processing operation {}", input.getOperation());

        switch (input.getOperation()) {
            case ADD:
                log.debug("Adding values {}", Arrays.toString(input.getData().toArray()));
                input.setResult(processAdd(input.getData()));
                break;
            case MULTIPLY:
                log.warn("Unimplemented operation request {}", Operation.MULTIPLY);
                input.setResult(1);

            default:
                log.error("Unexpected operation type {}", input.getOperation());

        }

        return input;

    }

    private int processAdd(List<Integer> data) {

        return data.stream().mapToInt(Integer::intValue).sum();

    }
}
