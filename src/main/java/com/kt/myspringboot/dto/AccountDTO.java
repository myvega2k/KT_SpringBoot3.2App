package com.kt.myspringboot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AccountDTO {
    private String username;
    private String password;
    private String mode;
}
