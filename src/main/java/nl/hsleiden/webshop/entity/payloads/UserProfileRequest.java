package nl.hsleiden.webshop.entity.payloads;

import javax.validation.constraints.NotNull;

import nl.hsleiden.webshop.entity.UserProfile;

public class UserProfileRequest {

    @NotNull
    private int userId;

    private UserProfile userProfile;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "UserProfileRequest{" +
                "userId=" + userId +
                ", userProfile=" + userProfile +
                '}';
    }
}
