package aero.champ.cargospot.workflow.repository;

import aero.champ.cargospot.workflow.domain.rule.Rule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RuleRepository extends CrudRepository<Rule, Long> {

    List<Rule> findByEventName(String eventName);
}
