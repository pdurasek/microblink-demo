package com.pdurasek.demo.jobs;

import com.pdurasek.demo.service.RentRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckOverdueTask {

    private RentRecordService rentRecordService;

    Logger log = LoggerFactory.getLogger(CheckOverdueTask.class);

    @Autowired
    public CheckOverdueTask(RentRecordService rentRecordService) {
        this.rentRecordService = rentRecordService;
    }

    @Scheduled(cron = "0 0 1 ? * *")
    public void checkOverdue() {
        log.info("==========================================================");
        log.info("====================START OVERDUE CHECK===================");
        log.info("==========================================================");
        rentRecordService.checkOverdue();
    }
}

