package com.bbpl.server2.servcie.impl;

import com.bbpl.server2.model.CalcInput;
import com.bbpl.server2.model.Operation;
import com.bbpl.server2.servcie.Calculator;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicCalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new BasicCalculator();
    }

    @Test
    public void testProcessAdditionTwoValues() {

        CalcInput data = CalcInput.builder().data(Arrays.asList(new Integer[] {1, 2})).operation(Operation.ADD).build();

        data = calculator.process(data);

        Assert.assertEquals("error adding two int values", 3, data.getResult().intValue());
    }

    @Test
    public void testProcessAdditionSingleValue() {

        CalcInput data = CalcInput.builder().data(Collections.singletonList(2)).operation(Operation.ADD).build();

        data = calculator.process(data);

        Assert.assertEquals("error adding one int values", 2, data.getResult().intValue());
    }

    @Test
    public void testProcessAdditionThreeValues() {

        CalcInput data = CalcInput.builder().data(Arrays.asList(new Integer[] {1, 2, 3})).operation(Operation.ADD).build();

        data = calculator.process(data);

        Assert.assertEquals("error adding three int values", 6, data.getResult().intValue());
    }

    @Test
    public void testProcessAdditionEmptyList() {

        CalcInput data = CalcInput.builder().data(Collections.EMPTY_LIST).operation(Operation.ADD).build();

        data = calculator.process(data);

        Assert.assertEquals("error operating empty list", 0, data.getResult().intValue());
    }
}