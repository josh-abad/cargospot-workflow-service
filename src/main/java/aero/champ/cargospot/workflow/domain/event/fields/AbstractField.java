package aero.champ.cargospot.workflow.domain.event.fields;

public abstract class AbstractField<V> implements IField<V> {

    private final String name;

    private final V value;

    protected AbstractField(String name, V value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public V value() {
        return value;
    }
}
