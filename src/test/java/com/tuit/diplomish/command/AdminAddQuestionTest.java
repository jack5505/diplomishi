package com.tuit.diplomish.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


class AdminAddQuestionTest {


    @Test
    void checkIsDigit() {
        Assertions.assertEquals(Pattern.matches("\\d+", "4-4"),true);

    }
}