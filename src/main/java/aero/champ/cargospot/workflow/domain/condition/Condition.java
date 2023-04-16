package aero.champ.cargospot.workflow.domain.condition;


import jakarta.persistence.*;

@Entity
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fieldName;

    private String value;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Operator operator;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public enum Type {
        TEXT,
        NUMBER
    }

    public enum Operator {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN
    }
}
