package com.wielkopolan.gymscheduler.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("scheduled_tasks")
public class ScheduledTask {
    private String id;
    private String memberId;
    private Instant scheduledTime;
    private boolean processed = false;
}