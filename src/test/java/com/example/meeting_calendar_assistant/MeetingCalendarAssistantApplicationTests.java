package com.example.meeting_calendar_assistant;

import com.example.meeting_calendar_assistant.model.Meeting;
import com.example.meeting_calendar_assistant.repository.MeetingRepository;
import com.example.meeting_calendar_assistant.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class MeetingCalendarAssistantApplicationTests {

	@Test
	void contextLoads() {
	}
	@InjectMocks
	private MeetingService meetingService;

	@Mock
	private MeetingRepository meetingRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBookMeeting() {
		Meeting meeting = new Meeting("1", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1).plusMinutes(30), Arrays.asList("emp1"));
		assertDoesNotThrow(() -> meetingService.bookMeeting(meeting));
	}

	@Test
	public void testCheckConflicts() {
		Meeting meeting = new Meeting("1", LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(1).plusMinutes(30), Arrays.asList("emp1", "emp2"));
		meetingService.bookMeeting(meeting);
		Meeting newMeeting = new Meeting("2", LocalDateTime.now().plusHours(1).plusMinutes(15), LocalDateTime.now().plusHours(1).plusMinutes(45), Arrays.asList("emp2"));

		List<String> conflicts = meetingService.checkConflicts(newMeeting);
		assertTrue(conflicts.contains("emp2"));
	}

}
