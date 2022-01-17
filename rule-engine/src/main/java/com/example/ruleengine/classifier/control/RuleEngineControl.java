package com.example.ruleengine.classifier.control;

import com.example.ruleengine.classifier.control.rules.BlackHoleRule;
import com.example.ruleengine.classifier.control.rules.PlanetRule;
import com.example.ruleengine.classifier.control.rules.Rule;
import com.example.ruleengine.classifier.control.rules.StarRule;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationRequestDto;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ruleengine.classifier.control.ObjectType.UNKNOWN;
import static java.util.Collections.unmodifiableSet;

@Service
@Slf4j
public class RuleEngineControl {

    private final static Set<Rule> rules = unmodifiableSet(new HashSet<Rule>() {{
        add(new PlanetRule());
        add(new StarRule());
        add(new BlackHoleRule());
    }});


    public CelestialObjectClassificationResponseDto classify(final CelestialObjectClassificationRequestDto celestialObject) {
        ObjectType objectType = UNKNOWN;

        final List<EvaluationResult> matchedRules = rules.stream().filter(rule -> rule.shouldRun(celestialObject))
                .map(rule -> rule.evaluate(celestialObject)).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(matchedRules)) {
            if (matchedRules.size() > 1) {
                matchedRules.sort(Comparator.comparing(EvaluationResult::getPrecedence));
            }
            objectType = matchedRules.get(0).getType();
        }

        return CelestialObjectClassificationResponseDto.builder().type(objectType.name()).build();
    }

}
