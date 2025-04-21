package com.wielkopolan.gymscheduler.dto;

import java.time.OffsetDateTime;

public record ScheduleRequestDTO(
        String memberId,
        String id,
        OffsetDateTime scheduledTime
) {}