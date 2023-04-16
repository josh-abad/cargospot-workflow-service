package aero.champ.cargospot.workflow.domain.event;

class InvalidFieldNameException extends IllegalArgumentException {

    public InvalidFieldNameException(String fieldName, String className) {
        super("Error retrieving field " + fieldName + " in " + className);
    }

    public InvalidFieldNameException(String fieldName, String className, Throwable cause) {
        super("Error retrieving field " + fieldName + " in " + className, cause);
    }
}
