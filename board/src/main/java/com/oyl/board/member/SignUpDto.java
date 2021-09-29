package com.oyl.board.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    String email;
    String nickname;
    String password;
    Long memberId;

    public static SignUpDto profile(Member member) {
        return SignUpDto.builder()
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
    }
}

