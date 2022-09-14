package com.tutoring.BaloonTask;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@ComponentScan
public class BaloonController {

    private final BaloonSolution baloonSolution = new BaloonSolution();

    @RequestMapping(value = "/get")
    public @ResponseBody int solutionController(@RequestParam(value = "string") String string) {
        return baloonSolution.solution(string);
    }
}
