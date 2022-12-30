package com.spring.batch.common.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchChunkListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {

    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {

    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {

    }
}