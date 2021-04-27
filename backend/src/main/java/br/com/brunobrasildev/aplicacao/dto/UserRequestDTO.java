package br.com.brunobrasildev.aplicacao.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserRequestDTO {

    private String login;
    private String password;

}
