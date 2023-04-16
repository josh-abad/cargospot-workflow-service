package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IsConditionTest {

    @Test
    void shouldPassEvaluationWithText() {
        var condition =  IsCondition.of("shc", "COOL");
        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "COOL");
        assertTrue(condition.evaluate(event));
    }

    @Test
    void shouldFailEvaluationWithText() {
        var condition =  IsCondition.of("shc", "COOL");
        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "MAL");
        assertFalse(condition.evaluate(event));
    }

    @Test
    void shouldPassEvaluationWithNumber() {
        var condition =  IsCondition.of("weight", new BigDecimal("42.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("42.0"), "MAL");
        assertTrue(condition.evaluate(event));
    }

    @Test
    void shouldFailEvaluationWithNumber() {
        var condition =  IsCondition.of("weight", new BigDecimal("42.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertFalse(condition.evaluate(event));
    }
}