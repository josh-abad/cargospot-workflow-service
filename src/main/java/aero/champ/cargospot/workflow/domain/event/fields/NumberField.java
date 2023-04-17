package aero.champ.cargospot.workflow.domain.event.fields;

import java.math.BigDecimal;

public class NumberField extends AbstractField<BigDecimal> {

    private NumberField(String name, BigDecimal value) {
        super(name, value);
    }

    public static NumberField of(String name, BigDecimal value) {
        return new NumberField(name, value);
    }

    @Override
    public boolean isGreaterThan(IField<BigDecimal> other) {
        return value().compareTo(other.value()) > 0;
    }

    @Override
    public boolean isLessThan(IField<BigDecimal> other) {
        return value().compareTo(other.value()) < 0;
    }

    @Override
    public boolean isEquals(IField<BigDecimal> other) {
        return value().compareTo(other.value()) == 0;
    }

    @Override
    public boolean isEmpty() {
        return value() == null || value().equals(BigDecimal.ZERO);
    }
}
