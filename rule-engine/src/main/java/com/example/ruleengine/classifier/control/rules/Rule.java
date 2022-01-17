package com.example.ruleengine.classifier.control.rules;

import com.example.ruleengine.classifier.control.EvaluationResult;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationRequestDto;

public interface Rule {

    boolean shouldRun(CelestialObjectClassificationRequestDto celestialObject);

    EvaluationResult evaluate(CelestialObjectClassificationRequestDto celestialObject);

}
