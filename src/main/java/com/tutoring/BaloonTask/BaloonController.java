package com.tutoring.BaloonTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class BaloonController {

    @Autowired
    private BaloonSolution baloonSolution;

    @RequestMapping(value = "/execute", method = GET)
    public @ResponseBody int solutionController(@RequestParam(value = "string") String string) {
        return baloonSolution.solution(string);
    }
}
