package com.android.app360.ui.home.adapters;

public class PostModel {

    public static final int POST_TYPE = 1;
    public static final int POST_HEADER = 0;
    public static final int POST_SHOW_MORE = 2;

    public int type;
    public String title;
    public String subtitle;
    public String detail;


    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDetail() {
        return detail;
    }

    public PostModel(int type, String title, String subtitle, String detail) {
        this.type = type;
        this.title = title;
        this.subtitle = subtitle;
        this.detail = detail;
    }
}
