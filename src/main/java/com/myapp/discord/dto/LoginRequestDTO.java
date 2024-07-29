package com.myapp.discord.dto;

import java.util.Date;

public record LoginRequestDTO(
        String email,
        String password
        )
{

}
