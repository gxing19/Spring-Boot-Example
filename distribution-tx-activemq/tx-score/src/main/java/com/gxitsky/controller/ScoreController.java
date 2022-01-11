package com.gxitsky.controller;

import com.gxitsky.entity.Score;
import com.gxitsky.service.ScoreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private static final Logger logger = LogManager.getLogger(ScoreController.class);

    @Autowired
    private ScoreService scoreService;


    @RequestMapping("/add")
    public Score addScore() {
        Score score = new Score();
        System.out.println(score);
        return score;
    }

}
