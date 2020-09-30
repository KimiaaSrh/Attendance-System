package ir.ac.shirazu.attendance_system.Models;

import com.google.gson.JsonObject;

import ir.ac.shirazu.attendance_system.Api.Config;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JsonObject getJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(Config.username, username);
        jsonObject.addProperty(Config.password, password);

        return jsonObject;
    }
}
