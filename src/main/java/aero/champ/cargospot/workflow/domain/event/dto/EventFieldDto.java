package aero.champ.cargospot.workflow.domain.event.dto;

import aero.champ.cargospot.workflow.domain.event.EventField;

public record EventFieldDto(String field, String name, EventField.Type type) {
}
