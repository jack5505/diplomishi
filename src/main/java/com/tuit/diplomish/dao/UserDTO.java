package com.tuit.diplomish.dao;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String phone;
    private String firstName;
    private String lastName;
}
