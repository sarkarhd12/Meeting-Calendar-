package com.example.meeting_calendar_assistant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meeting {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> participants;

}
