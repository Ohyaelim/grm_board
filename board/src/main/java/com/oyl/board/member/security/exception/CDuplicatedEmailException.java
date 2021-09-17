package com.oyl.board.member.security.exception;

public class CDuplicatedEmailException extends RuntimeException {
    public CDuplicatedEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public CDuplicatedEmailException(String msg) {
        super(msg);
    }

    public CDuplicatedEmailException() {
        super();
    }
}
