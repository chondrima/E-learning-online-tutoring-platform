package com.chondrimaishrat.elearning.auth;

import com.chondrimaishrat.elearning.user.AccountStatus;
import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SessionUser authenticate(LoginForm form) {
        String email = form.getEmail().trim().toLowerCase(Locale.ROOT);
        UserAccount account = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Email address or password is incorrect."
                ));

        if (!passwordEncoder.matches(form.getPassword(), account.getPasswordHash())) {
            throw new IllegalArgumentException(
                    "Email address or password is incorrect."
            );
        }

        if (account.getStatus() == AccountStatus.PENDING) {
            throw new IllegalArgumentException(
                    "This Instructor account is waiting for administrator approval."
            );
        }

        if (account.getStatus() == AccountStatus.REJECTED) {
            throw new IllegalArgumentException(
                    "This account is not active. Please contact an administrator."
            );
        }

        return new SessionUser(
                account.getId(),
                account.getFullName(),
                account.getRole()
        );
    }
}
