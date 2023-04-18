package aero.champ.cargospot.workflow.service;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import aero.champ.cargospot.workflow.domain.event.CreateBookingEvent;
import aero.champ.cargospot.workflow.repository.ConditionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ConditionServiceTest {

    private ConditionService conditionService;

    @BeforeEach
    void setUp() {
        conditionService = new ConditionService(mock(ConditionRepository.class));
    }

    @Test
    void shouldPassEqualsEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("shc");
        condition.setOperator(Condition.Operator.EQUALS);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("COOL");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "COOL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailEqualsEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("shc");
        condition.setOperator(Condition.Operator.EQUALS);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("COOL");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassEqualsEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.EQUALS);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("42.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("42.0"), "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailEqualsEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.EQUALS);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("42.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassGreaterThanEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.GREATER_THAN);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("10.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("11.0"), "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailGreaterThanEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.GREATER_THAN);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("10.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("9.0"), "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassLessThanEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.LESS_THAN);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("10.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("9.0"), "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailLessThanEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.LESS_THAN);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("10.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("11.0"), "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassNotEqualsEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("shc");
        condition.setOperator(Condition.Operator.NOT_EQUALS);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("COOL");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "COOL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailNotEqualsEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("shc");
        condition.setOperator(Condition.Operator.NOT_EQUALS);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("COOL");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassNotEqualsEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.NOT_EQUALS);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("42.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("42.0"), "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailNotEqualsEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.NOT_EQUALS);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("42.0");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassMultipleConditions() {
        var firstCondition = new Condition();
        firstCondition.setFieldName("weight");
        firstCondition.setOperator(Condition.Operator.GREATER_THAN);
        firstCondition.setType(Condition.Type.NUMBER);
        firstCondition.setValue("42.0");

        var secondCondition = new Condition();
        secondCondition.setFieldName("shc");
        secondCondition.setOperator(Condition.Operator.EQUALS);
        secondCondition.setType(Condition.Type.TEXT);
        secondCondition.setValue("MAL");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertTrue(conditionService.evaluateAll(List.of(firstCondition, secondCondition), event));
    }

    @Test
    void shouldFailMultipleConditions() {
        var firstCondition = new Condition();
        firstCondition.setFieldName("weight");
        firstCondition.setOperator(Condition.Operator.GREATER_THAN);
        firstCondition.setType(Condition.Type.NUMBER);
        firstCondition.setValue("42.0");

        var secondCondition = new Condition();
        secondCondition.setFieldName("shc");
        secondCondition.setOperator(Condition.Operator.EQUALS);
        secondCondition.setType(Condition.Type.TEXT);
        secondCondition.setValue("COOL");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        assertFalse(conditionService.evaluateAll(List.of(firstCondition, secondCondition), event));
    }

    @Test
    void shouldPassIsEmptyEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("priority");
        condition.setOperator(Condition.Operator.IS_EMPTY);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        event.setPriority(null);
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailIsEmptyEvaluationWithText() {
        var condition = new Condition();
        condition.setFieldName("priority");
        condition.setOperator(Condition.Operator.IS_EMPTY);
        condition.setType(Condition.Type.TEXT);
        condition.setValue("");

        var event = new CreateBookingEvent("131-1234567", new BigDecimal("43.0"), "MAL");
        event.setPriority("H");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassIsEmptyEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.IS_EMPTY);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.ZERO, "MAL");
        assertTrue(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldFailIsEmptyEvaluationWithNumber() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.IS_EMPTY);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("");

        var event = new CreateBookingEvent("131-1234567", BigDecimal.TEN, "MAL");
        assertFalse(conditionService.evaluate(condition, event));
    }

    @Test
    void shouldPassWhenValueIsField() {
        var condition = new Condition();
        condition.setFieldName("weight");
        condition.setOperator(Condition.Operator.LESS_THAN);
        condition.setType(Condition.Type.NUMBER);
        condition.setValue("flightCapacity");
        condition.setValueIsField(true);

        var event = new CreateBookingEvent("131-1234567", BigDecimal.TEN, "MAL");
        event.setFlightCapacity(new BigDecimal("11.0"));
        assertTrue(conditionService.evaluate(condition, event));
    }
}
