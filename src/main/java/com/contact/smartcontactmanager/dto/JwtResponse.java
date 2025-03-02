package com.contact.smartcontactmanager.dto;

import com.contact.smartcontactmanager.entity.User;

public record JwtResponse(String accessToken, String refreshToken, User user) {

}
