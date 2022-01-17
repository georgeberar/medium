package com.example.ruleengine.classifier;

import com.example.ruleengine.classifier.control.RuleEngineControl;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationRequestDto;
import com.example.ruleengine.classifier.dto.CelestialObjectClassificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/classifier")
@RequiredArgsConstructor
@Validated
public class ClassifierBoundary {

    private final RuleEngineControl ruleEngineControl;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<CelestialObjectClassificationResponseDto> classify(@Valid @RequestBody @NotNull final CelestialObjectClassificationRequestDto body) {
        return ResponseEntity.ok(this.ruleEngineControl.classify(body));
    }

}
