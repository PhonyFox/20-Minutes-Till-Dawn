package com.tilldawn.controller;

import com.tilldawn.Main;
import com.tilldawn.model.Repository;
import com.tilldawn.model.User;
import com.tilldawn.view.MainMenuView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ScoreboardMenuController {
    private final Repository repo;

    public ScoreboardMenuController(Repository repo) {
        this.repo = repo;
    }

    public enum SortOption {
        SCORE, USERNAME, KILLS, SURVIVAL_TIME
    }

    public List<User> getTopUsers(SortOption sortOption) {
        List<User> allUsers = repo.getUsers();

        Comparator<User> comparator;

        switch (sortOption) {
            case SCORE:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return Integer.compare(u2.getScore(), u1.getScore());
                    }
                };
                break;

            case USERNAME:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return u1.getUsername().compareTo(u2.getUsername());
                    }
                };
                break;

            case KILLS:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return Integer.compare(u2.getKills(), u1.getKills());
                    }
                };
                break;

            case SURVIVAL_TIME:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return Integer.compare(u2.getSurvivalTime(), u1.getSurvivalTime());
                    }
                };
                break;

            default:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User u1, User u2) {
                        return 0;
                    }
                };
        }

        return allUsers.stream()
            .sorted(comparator)
            .limit(10)
            .collect(Collectors.toList());
    }

    public Repository getRepo() {
        return repo;
    }

    public void goToMainMenu() {
        Main.getMain().setScreen(new MainMenuView(new MainMenuController(repo)));
    }

}
