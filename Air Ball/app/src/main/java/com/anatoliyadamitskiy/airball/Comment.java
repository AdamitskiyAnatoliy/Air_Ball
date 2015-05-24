package com.anatoliyadamitskiy.airball;

import java.io.Serializable;

/**
 * Created by Anatoliy on 3/14/15.
 */
public class Comment implements Serializable {

    private String comment;
    private String avatarUrl;
    private String name;

    public Comment (String _com, String _avatar, String _name) {
        comment = _com;
        avatarUrl = _avatar;
        name = _name;
    }

    public String getComment() {
        return comment;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }
}
