package com.example.mappings.enums;

import com.example.mappings.provider.EnableEnumMappingScan;
import com.example.mappings.provider.EnumMappingContract;

import java.util.EnumMap;
import java.util.Set;

@EnableEnumMappingScan
public enum ClearanceStatus implements EnumMappingContract<ClearanceStatus> {
    CREATED,
    APPROVED,
    REJECTED,
    CLOSED;

    @Override
    public EnumMap<ClearanceStatus, Set<? extends Enum<?>>> registerMappings() {
        final EnumMap<ClearanceStatus, Set<? extends Enum<?>>> mappings = new EnumMap<>(ClearanceStatus.class);
        mappings.put(APPROVED, Set.of(ServiceAStatus.APPROVED_BY_CUSTOMER));
        mappings.put(CLOSED, Set.of(ServiceBStatus.CLOSED_DUE_EXPIRATION_PERIOD));
        mappings.put(REJECTED, Set.of(ServiceAStatus.FAILURE, ServiceBStatus.ERROR));
        return mappings;
    }
}
