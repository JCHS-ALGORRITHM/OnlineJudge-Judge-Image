package com.github.ioloolo.judge.data;

public enum JudgeResult {
    READY, PROCESSING,
    ACCEPT, WRONG_ANSWER,
    COMPILE_ERROR, RUNTIME_ERROR,
    TIME_LIMIT_EXCEEDED, MEMORY_LIMIT_EXCEEDED,
}
