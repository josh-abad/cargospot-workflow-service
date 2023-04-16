package aero.champ.cargospot.workflow;

import aero.champ.cargospot.workflow.domain.rule.Rule;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RuleModelAssembler implements RepresentationModelAssembler<Rule, EntityModel<Rule>> {

    @Override
    public EntityModel<Rule> toModel(Rule rule) {
        return EntityModel.of(rule, linkTo(methodOn(RuleController.class).getRule(rule.getId())).withSelfRel(),
                linkTo(methodOn(RuleController.class).getRules()).withRel("rules"));
    }
}
