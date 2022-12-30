package com.spring.batch.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobName {

    SIMPLE_JOB1("simpleJob1"),
    SIMPLE_JOB2("simpleJob2"),
    SIMPLE_JOB3("simpleJob3"),
    SIMPLE_JOB4("simpleJob4"),
    TASKLET_STEP_JOB("taskletStepJob"),
    CHUNK_STEP_JOB("chunkStepJob"),
    SIMPLE_FLOW_JOB1("simpleFlowJob1"),
    SIMPLE_FLOW_JOB2("simpleFlowJob2"),
    STEP_FLOW_JOB("stepFlowJob"),
    FLOW_JOB("flowJob"),
    MULTIPLE_FLOW_JOB("multipleFlowJob"),
    TRANSITION_JOB("transitionJob"),
    FLOW_STEP_JOB("flowStepJob"),
    MYBATIS_ITEM_READER_JOB("mybatisItemReaderJob"),
    MYBATIS_ITEM_WRITER_JOB("mybatisItemWriterJob"),
    ADAPTER_JOB("adapterJob")
    ;

    private String jobName;

    public String getKey() {
        return name();
    }
}
