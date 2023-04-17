package aero.champ.cargospot.workflow.domain.event.fields;

public class TextField extends AbstractField<String> {

    private TextField(String name, String value) {
        super(name, value);
    }

    public static TextField of(String name, String value) {
        return new TextField(name, value);
    }

    @Override
    public boolean isGreaterThan(IField<String> other) {
        return value().compareTo(other.value()) > 0;
    }

    @Override
    public boolean isLessThan(IField<String> other) {
        return value().compareTo(other.value()) < 0;
    }

    @Override
    public boolean isEquals(IField<String> other) {
        return value().equals(other.value());
    }

    @Override
    public boolean isEmpty() {
        return value() == null || value().isEmpty();
    }
}
