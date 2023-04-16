package aero.champ.cargospot.workflow.domain.conditions;

import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GreaterThanConditionTest {

    @Test
    void shouldPassEvaluation() {
        var condition =  GreaterThanCondition.of("weight", new BigDecimal("10.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("11.0"), "MAL");
        assertTrue(condition.evaluate(event));
    }

    @Test
    void shouldFailEvaluation() {
        var condition =  GreaterThanCondition.of("weight", new BigDecimal("10.0"));
        var event = new CreateBookingEvent("131-1234567", new BigDecimal("9.0"), "MAL");
        assertFalse(condition.evaluate(event));
    }
}