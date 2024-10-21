package com.example.meeting_calendar_assistant.service;

import com.example.meeting_calendar_assistant.exception.ConflictException;
import com.example.meeting_calendar_assistant.model.Meeting;
import com.example.meeting_calendar_assistant.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting bookMeeting(Meeting meeting) {
        List<Meeting> existingMeetings = meetingRepository.findMeetingsByEmployeeId(meeting.getParticipants().get(0));
        for (Meeting existingMeeting : existingMeetings) {
            if (isConflict(existingMeeting, meeting)) {
                throw new ConflictException("Meeting conflict for participant: " + existingMeeting.getParticipants());
            }
        }
        meetingRepository.saveMeeting(meeting.getParticipants().get(0), meeting);
        return meeting;
    }

    public List<LocalDateTime> findFreeSlots(String employeeId1, String employeeId2, int duration) {
        List<Meeting> meetings1 = meetingRepository.findMeetingsByEmployeeId(employeeId1);
        List<Meeting> meetings2 = meetingRepository.findMeetingsByEmployeeId(employeeId2);
        List<LocalDateTime> freeSlots = new ArrayList<>();

        // Merge meetings for both employees
        meetings1.addAll(meetings2);
        meetings1.sort((m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime()));

        LocalDateTime currentTime = LocalDateTime.now();
        for (Meeting meeting : meetings1) {
            if (currentTime.plusMinutes(duration).isBefore(meeting.getStartTime())) {
                freeSlots.add(currentTime);
                currentTime = meeting.getEndTime();
            } else {
                currentTime = meeting.getEndTime().isAfter(currentTime) ? meeting.getEndTime() : currentTime;
            }
        }
        return freeSlots;
    }

    public List<String> checkConflicts(Meeting meeting) {
        List<String> conflictingParticipants = new ArrayList<>();
        for (String participant : meeting.getParticipants()) {
            List<Meeting> meetings = meetingRepository.findMeetingsByEmployeeId(participant);
            for (Meeting existingMeeting : meetings) {
                if (isConflict(existingMeeting, meeting)) {
                    conflictingParticipants.add(participant);
                    break;
                }
            }
        }
        return conflictingParticipants;
    }

    private boolean isConflict(Meeting existingMeeting, Meeting newMeeting) {
        return !newMeeting.getEndTime().isBefore(existingMeeting.getStartTime()) &&
                !newMeeting.getStartTime().isAfter(existingMeeting.getEndTime());
    }
}
