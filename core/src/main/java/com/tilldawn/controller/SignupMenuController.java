package com.tilldawn.controller;

import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.model.enums.SecurityQuestionType;

import java.util.Random;

public class SignupMenuController {
    private final Repository repo;

    public SignupMenuController(Repository repo) {
        this.repo = repo;
    }

    public String register(String username, String password, String question, String answer) {
        if (repo.isUsernameTaken(username)) {
            return "Username already taken!";
        }

        if (!isStrongPassword(password)) {
            return "Weak password! It must have 8+ chars, 1 number, 1 uppercase, 1 symbol.";
        }

        SecurityQuestionType questionType;

        if (question.startsWith("What is your pet")) {
            questionType = SecurityQuestionType.PET_NAME;
        } else if (question.startsWith("What is your favorite")) {
            questionType = SecurityQuestionType.FAVORITE_COLOR;
        } else if (question.startsWith("What city were you born")) {
            questionType = SecurityQuestionType.BORN_CITY;
        } else questionType = null;


        String avatar = assignRandomAvatar();
        User user = new User(username, password, questionType, answer, avatar);
        repo.addUser(user);
        return "User successfully registered!";
    }

    public boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[@%$#&*()_].*");
    }

    private String assignRandomAvatar() {
        String[] avatars = {"avatars/a1.png", "avatars/a2.png", "avatars/a3.png"};
        return avatars[new Random().nextInt(avatars.length)];
    }

    public User guestLogin() {
        return new User("Guest", "", null, "", "guest_avatar.png");
    }

    public Repository getRepo() {
        return repo;
    }
}
