package com.stoycho.margarita.timer;

import com.stoycho.margarita.generator.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 20*60*60*1000)
    public void scheduleTaskWithFixedRate(){
        updateTheSecret();
        logger.info("Secret updated");
    }

    public void updateTheSecret () {
        File file = new File("./extra/secret.txt");
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            String contents = RandomStringGenerator.getAlphaNumericString(8);
            writer.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}