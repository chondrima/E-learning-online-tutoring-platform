package com.chondrimaishrat.elearning.registration;

import com.chondrimaishrat.elearning.user.AccountStatus;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserAccountRepository;
import com.chondrimaishrat.elearning.user.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class RegistrationService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserRole register(RegistrationForm form) {
        String email = form.getEmail().trim().toLowerCase(Locale.ROOT);

        if (userAccountRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account already exists with this email address."
            );
        }

        UserRole role = readAllowedRole(form.getRole());

        AccountStatus status = role == UserRole.INSTRUCTOR
                ? AccountStatus.PENDING
                : AccountStatus.ACTIVE;

        UserAccount account = new UserAccount(
                form.getFullName().trim(),
                email,
                passwordEncoder.encode(form.getPassword()),
                role,
                status
        );

        userAccountRepository.save(account);
        return role;
    }

    private UserRole readAllowedRole(String roleText) {
        try {
            UserRole role = UserRole.valueOf(
                    roleText.trim().toUpperCase(Locale.ROOT)
            );

            if (role == UserRole.ADMIN) {
                throw new IllegalArgumentException(
                        "Admin accounts cannot be created through registration."
                );
            }

            return role;
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Choose Student or Instructor as the account type."
            );
        }
    }
}