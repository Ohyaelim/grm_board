package com.oyl.board.member;

public enum Role {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private String role;

    Role(String role) {
        this.role = role;
    }
}
