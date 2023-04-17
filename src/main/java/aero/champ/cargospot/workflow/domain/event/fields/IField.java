package aero.champ.cargospot.workflow.domain.event.fields;

public interface IField<V> {

    String name();

    V value();

    boolean isGreaterThan(IField<V> other);

    boolean isLessThan(IField<V> other);

    boolean isEquals(IField<V> other);

    default boolean isNotEquals(IField<V> other) {
        return !isEquals(other);
    }

    boolean isEmpty();
}
