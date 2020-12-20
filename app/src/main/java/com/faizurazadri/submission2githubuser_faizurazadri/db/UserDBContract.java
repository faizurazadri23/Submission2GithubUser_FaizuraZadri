package com.faizurazadri.submission2githubuser_faizurazadri.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class UserDBContract {

    public static final String AUTHORITY = "com.faizurazadri.submission2githubuser_faizurazadri";
    public static final String SCHEME = "content";

    public static final class UserColumns implements BaseColumns{
        public static final String TABLE_USER_NAME = "user";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        static final String AVATAR = "avatar";
        public static final Uri CONTENT_URL = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_USER_NAME)
                .build();
    }
}