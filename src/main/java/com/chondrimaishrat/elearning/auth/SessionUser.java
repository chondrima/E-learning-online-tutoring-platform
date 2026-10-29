package com.chondrimaishrat.elearning.auth;

import com.chondrimaishrat.elearning.user.UserRole;

import java.io.Serializable;

public record SessionUser(Long id, String fullName, UserRole role)
        implements Serializable {
}
