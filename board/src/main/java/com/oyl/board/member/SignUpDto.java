package com.oyl.board.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    String email;
    String name;
    String password;

    public static SignUpDto profile(Member member) {
        return SignUpDto.builder()
                .password(member.getPassword())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
