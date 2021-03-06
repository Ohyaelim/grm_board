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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
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
        String token = jwtTokenProvider.createToken(member.getUsername(),member.getRoles());
        return new ResponseEntity(token, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<SignUpDto> findUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member  = (Member) authentication.getPrincipal();
        log.info(member.getEmail());
        SignUpDto memberResponse = memberService.findByUserEmail(member.getEmail());
        return ResponseEntity.ok(memberResponse);
    }
    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestParam String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member  = (Member) authentication.getPrincipal();
        memberService.update(name, member);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/dismembership/{memberId}")
    public ResponseEntity dismembership(@PathVariable Long memberId){
        try {
            memberService.dismembership(memberId);
            return ResponseEntity.ok().build();
        }catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CEmailSigninFailedException.class)
    protected ResponseEntity emailSigninFailed(HttpServletRequest request, CEmailSigninFailedException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("??????????????? ???????????? ????????????.");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CDuplicatedEmailException.class)
    protected ResponseEntity emailSigninFailed(HttpServletRequest request, CDuplicatedEmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("?????? ???????????? ???????????? ??????????????????.");
    }
}