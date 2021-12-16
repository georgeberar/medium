package com.example.mappings;

import com.example.mappings.enums.ClearanceStatus;
import com.example.mappings.enums.ServiceAStatus;
import com.example.mappings.enums.ServiceBStatus;
import com.example.mappings.provider.EnumMappingProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class EnumMappingProviderTest {

    @Autowired
    private EnumMappingProvider enumMappingProvider;

    @Test
    @DisplayName("When Clearance status is given then correct Service A status is returned")
    public void test_mappingFromSourceToTarget_when_ClearanceStatusIsGiven_then_correctServiceAStatusIsReturned() {
        assertEquals(ServiceAStatus.APPROVED_BY_CUSTOMER, enumMappingProvider.mappingFromSourceToTarget(ClearanceStatus.APPROVED,
                ServiceAStatus.class));

        assertEquals(ServiceAStatus.FAILURE, enumMappingProvider.mappingFromSourceToTarget(ClearanceStatus.REJECTED,
                ServiceAStatus.class));
    }

    @Test
    @DisplayName("When Clearance status is given then correct Service B status is returned")
    public void test_mappingFromSourceToTarget_when_ClearanceStatusIsGiven_then_correctServiceBStatusIsReturned() {
        assertEquals(ServiceBStatus.ERROR, enumMappingProvider.mappingFromSourceToTarget(ClearanceStatus.REJECTED,
                ServiceBStatus.class));

        assertEquals(ServiceBStatus.CLOSED_DUE_EXPIRATION_PERIOD, enumMappingProvider.mappingFromSourceToTarget(ClearanceStatus.CLOSED,
                ServiceBStatus.class));
    }

    @Test
    @DisplayName("When Service A status is given then correct Clearance status is returned")
    public void test_mappingToTargetFromSource_when_ServiceAStatusIsGiven_then_correctClearanceStatusIsReturned() {
        assertEquals(ClearanceStatus.APPROVED, enumMappingProvider.mappingToTargetFromSource(ClearanceStatus.class,
                ServiceAStatus.APPROVED_BY_CUSTOMER));

        assertEquals(ClearanceStatus.REJECTED, enumMappingProvider.mappingToTargetFromSource(ClearanceStatus.class,
                ServiceAStatus.FAILURE));
    }

    @Test
    @DisplayName("When Service B status is given then correct Clearance status is returned")
    public void test_mappingToTargetFromSource_when_ServiceBStatusIsGiven_then_correctClearanceStatusIsReturned() {
        assertEquals(ClearanceStatus.CLOSED, enumMappingProvider.mappingToTargetFromSource(ClearanceStatus.class,
                ServiceBStatus.CLOSED_DUE_EXPIRATION_PERIOD));

        assertEquals(ClearanceStatus.REJECTED, enumMappingProvider.mappingToTargetFromSource(ClearanceStatus.class,
                ServiceBStatus.ERROR));
    }

}
