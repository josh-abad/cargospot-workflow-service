package aero.champ.cargospot.workflow.repository;

import aero.champ.cargospot.workflow.domain.rule.Rule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RuleRepository extends MongoRepository<Rule, Long> {

    List<Rule> findByEventName(String eventName);
}
