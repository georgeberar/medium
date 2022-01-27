package com.example.pdfcontentextractor.extractor;


import com.example.pdfcontentextractor.extractor.control.ContentExtractorControl;
import com.example.pdfcontentextractor.extractor.dto.ContentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/extractor")
@RequiredArgsConstructor
@Validated
public class ContentExtractorBoundary {

    private final ContentExtractorControl contentExtractorControl;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<ContentResponseDto> classify(@Valid @NotNull @RequestParam("file") final MultipartFile pdfFile) {
        return ResponseEntity.ok().body(ContentResponseDto.builder().content(this.contentExtractorControl.extractContent(pdfFile)).build());
    }


}
