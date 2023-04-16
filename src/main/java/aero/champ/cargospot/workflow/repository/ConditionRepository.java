package aero.champ.cargospot.workflow.repository;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import org.springframework.data.repository.CrudRepository;

public interface ConditionRepository extends CrudRepository<Condition, Long> {
}
