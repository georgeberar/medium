package com.example.openapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OpenAPIDocumentGeneratorIT {

    private static final String API_DOCS_PATH = "/v3/api-docs.yaml";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Generate YAML file with OpenAPI definitions")
    void test_generateOpenAPIDefinitions() throws Exception {
        final MvcResult response = mockMvc.perform(get(API_DOCS_PATH))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(response);
        assertNotNull(response.getResponse());
        final byte[] file = response.getResponse().getContentAsByteArray();
        assertNotEquals(0, file.length);
        try (final FileOutputStream fos = new FileOutputStream("src\\main\\resources\\apidocs\\openapi.yaml")) {
            fos.write(file);
        }
    }

}
