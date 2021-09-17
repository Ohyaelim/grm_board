package com.oyl.board.member;

import com.oyl.board.exception.MemberNotFoundException;
import com.oyl.board.member.security.exception.CDuplicatedEmailException;
import com.oyl.board.member.security.exception.CEmailSigninFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignUpDto signUpDto){
        if (memberRepository.findByEmail(signUpDto.getEmail()).isEmpty()){
            Member member = Member.builder()
                    .email(signUpDto.getEmail())
                    .name(signUpDto.getName())
                    .password(passwordEncoder.encode(signUpDto.getPassword()))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
            memberRepository.save(member);
        }
        else{
            throw new CDuplicatedEmailException();
        }

    }

    @Transactional
    public Member signin(SignInDto signinDto){
        Member member = memberRepository.findByEmail(signinDto.getEmail()).orElseThrow(MemberNotFoundException::new);
        if(!passwordEncoder.matches(signinDto.getPassword(), member.getPassword())){
            throw new CEmailSigninFailedException();
        }
        return member;
    }

    @Transactional
    public void dismembership(Long member_id){
        Member member = memberRepository.findById(member_id).orElseThrow(MemberNotFoundException::new);
        member.setEmail("");
        member.setName("");
        member.setPassword("");
        memberRepository.save(member);
    }

    @Transactional
    public Member getMember(String visitorId) {
        System.out.println("visitorID"+ visitorId);
        Member member = memberRepository.findByEmail(visitorId).orElseThrow(MemberNotFoundException::new);
        return member;
    }
}
