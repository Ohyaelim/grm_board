package com.oyl.board.member;

import com.oyl.board.common.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_member")
public class Member extends BaseTimeEntity implements UserDetails{
    private static final String DELETED_SIGN = "DELETED";
//
//    @Builder
//    public Member(Long member_id, String email, String name, String password, List<String> roles) {
//        this.member_id = member_id;
//        this.email = email;
//        this.name = name;
//        this.password = password;
//        this.roles = roles;
//    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String name;

    private String password;

    public void delete() {
        email = DELETED_SIGN;
        name = DELETED_SIGN;
        password = DELETED_SIGN;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void update(String name) {
        this.name = name;
    }
}
