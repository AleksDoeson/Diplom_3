package utils;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class UserModel {
    private final String email;
    private final String password;
    private final String name;
}

