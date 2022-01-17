package com.example.ruleengine.classifier.control.rules;

import com.example.ruleengine.classifier.control.EvaluationResult;
import com.example.ruleengine.classifier.control.ObjectType;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationRequestDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlanetRule implements Rule {

    private static final double MASS_OF_JUPITER = 1.898e27;
    private static final double UPPER_MASS_LIMIT = MASS_OF_JUPITER * 13;

    @Override
    public boolean shouldRun(final CelestialObjectClassificationRequestDto celestialObject) {
        // in order to be a planet the mass should be at most 13 times the mass of Jupiter
        return celestialObject.getMass() <= UPPER_MASS_LIMIT;
    }

    @Override
    public EvaluationResult evaluate(final CelestialObjectClassificationRequestDto celestialObject) {
        log.info("Planet Rule executed");
        return new EvaluationResult() {
            @Override
            public ObjectType getType() {
                return ObjectType.PLANET;
            }

            @Override
            public int getPrecedence() {
                return 1;
            }
        };
    }

}
