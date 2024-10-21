package com.example.meeting_calendar_assistant.controller;

import com.example.meeting_calendar_assistant.model.Meeting;
import com.example.meeting_calendar_assistant.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/book")
    public ResponseEntity<Meeting> bookMeeting(@RequestBody Meeting meeting) {
        Meeting bookedMeeting = meetingService.bookMeeting(meeting);
        return ResponseEntity.ok(bookedMeeting);
    }

    @GetMapping("/free-slots")
    public ResponseEntity<List<LocalDateTime>> findFreeSlots(@RequestParam String employeeId1,
                                                             @RequestParam String employeeId2,
                                                             @RequestParam int duration) {
        List<LocalDateTime> freeSlots = meetingService.findFreeSlots(employeeId1, employeeId2, duration);
        return ResponseEntity.ok(freeSlots);
    }

    @PostMapping("/conflicts")
    public ResponseEntity<List<String>> checkConflicts(@RequestBody Meeting meeting) {
        List<String> conflictingParticipants = meetingService.checkConflicts(meeting);
        return ResponseEntity.ok(conflictingParticipants);
    }
}
