package aero.champ.cargospot.workflow.domain.event;

import java.math.BigDecimal;

public class FieldValue {

    private final String name;

    private final Type type;

    private final String textValue;

    private final BigDecimal numberValue;

    private FieldValue(String name, Type type, String textValue, BigDecimal numberValue) {
        this.name = name;
        this.type = type;
        this.textValue = textValue;
        this.numberValue = numberValue;
    }

    public boolean isText() {
        return type == Type.TEXT;
    }

    public boolean isNumber() {
        return type == Type.NUMBER;
    }

    public static FieldValue ofText(String name, String value) {
        return new FieldValue(name, Type.TEXT, value, BigDecimal.ZERO);
    }

    public static FieldValue ofNumber(String name, BigDecimal value) {
        return new FieldValue(name, Type.NUMBER, value.toPlainString(), value);
    }

    public String getText() {
        return textValue;
    }

    public BigDecimal getNumber() {
        return numberValue;
    }

    enum Type {
        TEXT, NUMBER
    }
}
