package com.anatoliyadamitskiy.airball;

import java.io.Serializable;

/**
 * Created by Anatoliy on 3/19/15.
 */
public class User implements Serializable {

    String avatarUrl;
    String followers;
    String following;
    String numberOfShots;
    String shotsUrl;

    public User(String _avatar, String _followers, String _following, String _shots, String _shotsUrl) {
        avatarUrl = _avatar;
        followers = _followers;
        following = _following;
        numberOfShots = _shots;
        shotsUrl = _shotsUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getNumberOfShots() {
        return numberOfShots;
    }

    public String getShotsUrl() {
        return shotsUrl;
    }
}
