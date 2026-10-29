package com.chondrimaishrat.elearning.auth;

import com.chondrimaishrat.elearning.user.UserAccount;
import com.chondrimaishrat.elearning.user.UserAccountRepository;
import com.chondrimaishrat.elearning.user.UserRole;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionAccess {

    private final UserAccountRepository userAccountRepository;

    public SessionAccess(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public UserAccount requireUser(HttpSession session) {
        Object value = session.getAttribute("currentUser");
        if (!(value instanceof SessionUser sessionUser)) {
            throw new IllegalArgumentException("Please login to continue.");
        }

        return userAccountRepository.findById(sessionUser.id())
                .orElseThrow(() -> new IllegalArgumentException("Your account could not be found."));
    }

    public UserAccount requireRole(HttpSession session, UserRole requiredRole) {
        UserAccount user = requireUser(session);
        if (user.getRole() != requiredRole) {
            throw new IllegalArgumentException("You do not have permission to access this page.");
        }
        return user;
    }
}
