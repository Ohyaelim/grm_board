package com.oyl.board.member;

import com.oyl.board.common.BaseTimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "t_member")
public class Member extends BaseTimeEntity implements UserDetails{
    private static final String DELETED_SIGN = "DELETED";

    @Builder
    public Member(Long member_id, String email, String name, String password, Role role) {
        this.member_id = member_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long member_id;

    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void delete() {
        email = DELETED_SIGN;
        name = DELETED_SIGN;
        password = DELETED_SIGN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
