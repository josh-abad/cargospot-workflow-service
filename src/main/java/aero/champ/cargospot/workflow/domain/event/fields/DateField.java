package aero.champ.cargospot.workflow.domain.event.fields;

import java.time.Instant;

public class DateField extends AbstractField<Instant> {

    protected DateField(String name, Instant value) {
        super(name, value);
    }

    public static DateField of(String name, Instant value) {
        return new DateField(name, value);
    }

    @Override
    public boolean isGreaterThan(IField<Instant> other) {
        return false;
    }

    @Override
    public boolean isLessThan(IField<Instant> other) {
        return false;
    }

    @Override
    public boolean isEquals(IField<Instant> other) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
