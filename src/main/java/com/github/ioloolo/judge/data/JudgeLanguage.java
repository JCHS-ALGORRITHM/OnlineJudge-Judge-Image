package com.github.ioloolo.judge.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum JudgeLanguage {
    C(
            "Solution.c",
            "gcc Solution.c -o Solution -O2 -w -lm -std=c18 -DONLINE_JUDGE",
            "./Solution",
            memory -> memory,
            time -> time
    ),

    CPP(
            "Solution.cpp",
            "g++ Solution.cpp -o Solution -O2 -w -lm -std=c++23 -DONLINE_JUDGE",
            "./Solution",
            memory -> memory,
            time -> time
    ),

    PYTHON(
            "Solution.py",
            null,
            "python Solution.py",
            memory -> memory * 2 + 32,
            time -> time * 3 + 2000
    ),

    JAVA(
            "Solution.java",
            "javac -encoding UTF-8 Solution.java",
            "java -Dfile.encoding=UTF-8 -DONLINE_JUDGE=1 Solution",
            memory -> memory * 2 + 16,
            time -> time * 2 + 1000
    ),
    ;

    private final String src;
    private final String compile;
    private final String exec;
    private final Function<Long, Long> memory;
    private final Function<Long, Long> time;
}

