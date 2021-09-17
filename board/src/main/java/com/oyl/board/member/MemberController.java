package com.oyl.board.member;

import com.oyl.board.member.security.JwtTokenProvider;
import com.oyl.board.member.security.exception.CDuplicatedEmailException;
import com.oyl.board.member.security.exception.CEmailSigninFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/auth")
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignUpDto signUpDto, Errors errors){
        try{
            memberService.signup(signUpDto);
            return ResponseEntity.ok().build();
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody SignInDto signinDto){
        Member member= memberService.signin(signinDto);
        MultiValueMap<String, String>header = new LinkedMultiValueMap<>();
        header.add("authtoken", jwtTokenProvider.createToken(member.getUsername(), member.getRoles()));
        return new ResponseEntity(header, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CEmailSigninFailedException.class)
    protected ResponseEntity emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호가 정확하지 않습니다.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CDuplicatedEmailException.class)
    protected ResponseEntity emailSigninFailed(HttpServletRequest request, CDuplicatedEmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("다른 사용자가 이메일을 사용중입니다.");
    }
}
