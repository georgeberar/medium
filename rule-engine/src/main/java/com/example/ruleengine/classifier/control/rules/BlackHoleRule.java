package com.example.ruleengine.classifier.control.rules;

import com.example.ruleengine.classifier.control.EvaluationResult;
import com.example.ruleengine.classifier.control.ObjectType;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationRequestDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlackHoleRule implements Rule {

    private static final double G = 6.67e-11;
    private static final long c = 299_792_458;

    @Override
    public boolean shouldRun(final CelestialObjectClassificationRequestDto celestialObject) {
        final double schwarzschildRadius = (2 * G * celestialObject.getMass()) / (Math.pow(c, 2));
        final double physicalRadius = celestialObject.getEquatorialDiameter() / 2;
        // in order to be a black-hole the physical radius needs to be smaller than its Schwarzschild radius
        return physicalRadius < schwarzschildRadius;
    }

    @Override
    public EvaluationResult evaluate(final CelestialObjectClassificationRequestDto celestialObject) {
        log.info("Black-Hole Rule executed");
        return new EvaluationResult() {
            @Override
            public ObjectType getType() {
                return ObjectType.BLACK_HOLE;
            }

            @Override
            public int getPrecedence() {
                return 0;
            }
        };
    }

}
