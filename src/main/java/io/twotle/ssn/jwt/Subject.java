package io.twotle.ssn.jwt;

import lombok.Getter;

@Getter
public class Subject {
    private final Long accountId;

    private final String email;

    private final String nickname;

    private final String type;

    private Subject(Long accountId, String email, String nickname, String type) {
        this.accountId = accountId;
        this.email = email;
        this.nickname = nickname;
        this.type = type;
    }

    public static Subject access(Long accountId, String email, String nickname) {
        return new Subject(accountId, email, nickname, "access");
    }

    public static Subject refresh(Long accountId, String email, String nickname) {
        return new Subject(accountId, email, nickname, "refresh");
    }


}
