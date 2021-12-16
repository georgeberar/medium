package com.example.mappings.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Component
@Slf4j
public class EnumMappingProvider {

    private final Set<EnumMap<? extends Enum<?>, Set<? extends Enum<?>>>> mappings = new HashSet<>();

    @PostConstruct
    public void discoverAndRegisterMappings() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(EnableEnumMappingScan.class));

        final Set<BeanDefinition> beanDefinitions = scanner.findCandidateComponents("com.example.mappings");

        if (!CollectionUtils.isEmpty(beanDefinitions)) {
            for (final BeanDefinition beanDefinition : beanDefinitions) {
                final Class<?> enumClass = Class.forName(beanDefinition.getBeanClassName());

                if (enumClass.isEnum()) {
                    final Method[] methods = enumClass.getDeclaredMethods();
                    for (final Method method : methods) {
                        if (Stream.of(EnumMappingContract.class.getDeclaredMethods()).anyMatch(m -> m.getName().equals(method.getName()))) {
                            final EnumMap<? extends Enum<?>, Set<? extends Enum<?>>> mappingsToRegister =
                                    (EnumMap) method.invoke(enumClass.getEnumConstants()[0]);
                            this.mappings.add(mappingsToRegister);
                            log.info("Registered mappings for {}", enumClass);
                        }
                    }
                }
            }
        }
    }


    public <I extends Enum<I>, O extends Enum<O>> O mappingFromSourceToTarget(final I sourceEnumValue, final Class<O> targetEnumClass) {
        O val = null;

        for (final EnumMap<? extends Enum<?>, Set<? extends Enum<?>>> mapping : mappings) {
            if (mapping.containsKey(sourceEnumValue)) {
                val = (O) mapping.get(sourceEnumValue).stream()
                        .filter(mappingEntry -> mappingEntry.getClass().equals(targetEnumClass))
                        .findFirst()
                        .orElse(null);

                if (val != null)
                    break;
            }
        }

        return val;
    }


    public <I extends Enum<I>, O extends Enum<O>> O mappingToTargetFromSource(final Class<O> targetEnumClass, final I sourceEnumValue) {
        O val = null;

        for (final EnumMap<? extends Enum<?>, Set<? extends Enum<?>>> mapping : mappings) {
            final Map.Entry<? extends Enum<?>, Set<? extends Enum<?>>> candidateEntry = mapping.entrySet().stream()
                    .filter(mappingEntry -> mappingEntry.getValue().contains(sourceEnumValue))
                    .findFirst()
                    .orElse(null);

            if (candidateEntry != null && candidateEntry.getKey().getClass().equals(targetEnumClass)) {
                val = (O) candidateEntry.getKey();
            }

            if (val != null) {
                break;
            }
        }

        return val;
    }


}
