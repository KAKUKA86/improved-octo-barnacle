package org.zhang.word_backend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_phone;
}
