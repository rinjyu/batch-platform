package com.spring.batch.common.processor;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;

public class BatchBeanPostProcessor extends JobRegistryBeanPostProcessor {

    public BatchBeanPostProcessor() {

    }

    public BatchBeanPostProcessor(JobRegistry jobRegistry) {
        setJobRegistry(jobRegistry);
    }
}
