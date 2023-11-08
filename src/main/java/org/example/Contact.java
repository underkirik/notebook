package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
public class Contact {
    private String fio;
    private String number;
    private String email;

    public String getContactString() {
        return fio + ";" + number + ";" + email;
    }

}
