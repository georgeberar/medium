package com.example.mappings.provider;

import java.util.EnumMap;
import java.util.Set;

@FunctionalInterface
public interface EnumMappingContract<T extends Enum<T>> {

    EnumMap<T, Set<? extends Enum<?>>> registerMappings();

}
