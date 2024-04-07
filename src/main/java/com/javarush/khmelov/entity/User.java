package com.javarush.khmelov.entity;

import com.javarush.khmelov.tools.Keys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements QuestObject {

    private Long id;
    private String login;
    private String password;
    private Role role;

    public String getImage() {
        return Keys.IMAGE + "-" + id;
    }

}
