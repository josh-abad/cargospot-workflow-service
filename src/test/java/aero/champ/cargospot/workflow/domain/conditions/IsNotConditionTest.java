package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IsNotConditionTest {

    @Test
    void shouldPassEvaluationWithText() {
        var condition =  IsNotCondition.of("shc", "COOL");
        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "COOL");
        assertFalse(condition.evaluate(event));
    }

    @Test
    void shouldFailEvaluationWithText() {
        var condition =  IsNotCondition.of("shc", "COOL");
        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "MAL");
        assertTrue(condition.evaluate(event));
    }

    @Test
    void shouldPassEvaluationWithNumber() {
        var condition =  IsNotCondition.of("weight", new BigDecimal("42.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("42.0"), "MAL");
        assertFalse(condition.evaluate(event));
    }

    @Test
    void shouldFailEvaluationWithNumber() {
        var condition =  IsNotCondition.of("weight", new BigDecimal("42.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertTrue(condition.evaluate(event));
    }
}