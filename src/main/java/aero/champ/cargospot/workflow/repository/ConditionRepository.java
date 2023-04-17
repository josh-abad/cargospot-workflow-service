package aero.champ.cargospot.workflow.repository;

import aero.champ.cargospot.workflow.domain.condition.Condition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConditionRepository extends MongoRepository<Condition, Long> {
}
