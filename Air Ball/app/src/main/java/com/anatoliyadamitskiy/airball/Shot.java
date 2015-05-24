package com.anatoliyadamitskiy.airball;

import java.io.Serializable;

/**
 * Created by Anatoliy on 3/12/15.
 */
public class Shot implements Serializable {

    private String webUrl;
    private String imageUrl;
    private String avatarUrl;
    private String commentsUrl;
    private String playerName;
    private String shotTitle;

    public Shot (String _webUrl, String _imageUrl, String _avatarUrl, String _commentsUrl, String _playerName, String _shotTitle) {
        imageUrl = _imageUrl;
        avatarUrl = _avatarUrl;
        commentsUrl = _commentsUrl;
        playerName = _playerName;
        shotTitle = _shotTitle;
        webUrl = _webUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getShotTitle() {
        return shotTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

}
