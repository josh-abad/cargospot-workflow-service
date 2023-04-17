package aero.champ.cargospot.workflow.domain.event.dto;

import java.util.List;

public record EventDto(String name, List<EventFieldDto> fields) {
}
