package com.tutoring.BaloonTask;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class BaloonSolution {

    public int solution(String s) {
        int b = 0, a = 0, l = 0, o = 0, n = 0;

        for (char ch : s.toUpperCase().toCharArray()) {
            switch (ch) {
                case 'B' -> b++;
                case 'A' -> a++;
                case 'L' -> l++;
                case 'O' -> o++;
                case 'N' -> n++;
            }
        }
        return Math.min(Math.min(o / 2, l / 2), Math.min(Math.min(b, a), n));

    }
}