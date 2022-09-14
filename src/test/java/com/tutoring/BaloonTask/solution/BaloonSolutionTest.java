package com.tutoring.BaloonTask.solution;

import com.tutoring.BaloonTask.BaloonSolution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaloonSolutionTest {

    BaloonSolution baloonSolution = new BaloonSolution();

    @Test
    void solutionNoWord() {
        assertEquals(0, baloonSolution.solution("test"));
    }

    @Test
    void solutionOneWord() {
        assertEquals(1, baloonSolution.solution("balloons"));
    }

    @Test
    void solutionTwoWord() {
        assertEquals(2, baloonSolution.solution("balloonbaboonballoon"));
    }

    @Test
    void solutionManyWord() {
        assertEquals(200000, baloonSolution.solution("balloons".repeat(200000)));
    }


}