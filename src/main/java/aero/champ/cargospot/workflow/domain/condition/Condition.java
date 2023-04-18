package aero.champ.cargospot.workflow.domain.condition;


import org.springframework.data.annotation.Id;

public class Condition {

    @Id
    private String id;

    private String fieldName;

    private String value;

    private Type type;

    private Operator operator;

    private boolean valueIsField;

    private Relation relation;

    private Condition next;

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

    public boolean isValueIsField() {
        return valueIsField;
    }

    public void setValueIsField(boolean valueIsField) {
        this.valueIsField = valueIsField;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Condition getNext() {
        return next;
    }

    public void setNext(Condition next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null && relation != null;
    }

    public enum Type {
        TEXT,
        NUMBER
    }

    public enum Operator {
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        LESS_THAN,
        /** Empty string, zero, or null */
        IS_EMPTY
    }

    public enum Relation {
        AND,
        OR
    }
}
