package com.tilldawn.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


class UserInfo {
    String name;
    String password;

    public UserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class UserStorage {

    private static final String FILE_PATH = "users.json";
    private static final Gson gson = new GsonBuilder().create();


    private static UserStorage instance;

    private UserStorage() {}

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public void saveUser(User user) throws IOException {
        UserInfo info = new UserInfo(user.getUsername(), user.getPassword());
        List<UserInfo> users = new ArrayList<>();

        File file = new File(FILE_PATH);

        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                // تغییر نوع به List<UserInfo> به جای List<User>
                Type userListType = new TypeToken<List<UserInfo>>() {}.getType();
                users = gson.fromJson(reader, userListType);
                if (users == null) {
                    users = new ArrayList<>();
                }
            } catch (com.google.gson.JsonSyntaxException | java.io.EOFException e) {
                // اگر فایل خالی بود یا JSON نادرست بود، لیست جدید بساز
                users = new ArrayList<>();
            }
        } else {
            // اگر فایل وجود ندارد، بهتر است یک فایل جدید با آرایه خالی بسازیم
            try (Writer writer = new FileWriter(file)) {
                writer.write("[]");
            }
        }

        users.add(info);

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        }
    }

}

