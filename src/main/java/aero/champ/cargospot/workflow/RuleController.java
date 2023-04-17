package aero.champ.cargospot.workflow;

import aero.champ.cargospot.workflow.domain.rule.Rule;
import aero.champ.cargospot.workflow.service.RuleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping("/api/rules")
public class RuleController {

    private final RuleService ruleService;

    private final RuleModelAssembler assembler;

    public RuleController(RuleService ruleService, RuleModelAssembler assembler) {
        this.ruleService = ruleService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Rule>> getRules() {
        List<EntityModel<Rule>> rules = new ArrayList<>();
        for (Rule rule : ruleService.getAllRules()) {
            rules.add(assembler.toModel(rule));
        }

        return CollectionModel.of(rules, linkTo(methodOn(RuleController.class).getRules()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Rule> getRule(@PathVariable String id) {
        Rule rule = ruleService.getRule(id).orElseThrow(() -> new RuleNotFoundException(id));
        return assembler.toModel(rule);
    }

    @PostMapping
    public ResponseEntity<?> createRule(@RequestBody Rule newRule) {
        EntityModel<Rule> entityModel = assembler.toModel(ruleService.createRule(newRule));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRule(@PathVariable String id) {
        ruleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
