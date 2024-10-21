package com.example.meeting_calendar_assistant.repository;

import com.example.meeting_calendar_assistant.model.Meeting;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MeetingRepository {
    private Map<String, List<Meeting>> employeeMeetings = new HashMap<>();

    public void saveMeeting(String employeeId, Meeting meeting) {
        employeeMeetings.computeIfAbsent(employeeId, k -> new ArrayList<>()).add(meeting);
    }

    public List<Meeting> findMeetingsByEmployeeId(String employeeId) {
        return employeeMeetings.getOrDefault(employeeId, new ArrayList<>());
    }
}
