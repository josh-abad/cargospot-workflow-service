package aero.champ.cargospot.workflow.domain.event.dto;

import java.util.List;

public record EventDto(long id, String name, List<EventFieldDto> fields) {
}
