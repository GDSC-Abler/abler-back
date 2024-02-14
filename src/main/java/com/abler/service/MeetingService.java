package com.abler.service;
import com.abler.domain.meeting.Meeting;
import com.abler.domain.user.User;
import com.abler.dto.meeting.MeetingJoinRequestDto;
import com.abler.dto.meeting.MeetingListDto;
import com.abler.dto.meeting.MeetingSaveDto;
import com.abler.dto.meeting.MeetingResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface MeetingService {
/*    void saveMeetingWithUserUUID(String providerId, Meeting meeting);*/

    Meeting createMeeting(User user, MeetingSaveDto meetingSaveDto);

    Meeting updateMeeting(UUID meetingUuid, MeetingResponseDto updateDto);

    List<Meeting> getMeetings(MeetingListDto listDto);

    Optional<MeetingResponseDto> getMeetingDetailById(UUID id);

    boolean joinMeeting(MeetingJoinRequestDto requestDto);

    boolean  cancelJoinMeeting(MeetingJoinRequestDto requestDto);

    boolean subscribeToMeeting(UUID meetingUuid, UUID userUuid);

    boolean unsubscribeFromMeeting(UUID meetingUuid, UUID userUuid);

    Set<Meeting> getSubscribedMeetings(UUID userUuid);
}
