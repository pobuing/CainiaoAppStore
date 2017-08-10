package com.xinw.cainiaoappstore.ui.bean;

/**
 * byD9ing on 2017/8/9.
 * Describe:
 * good luck
 */

public class FragmentInfo {
    private String title;
    private Class fragment;

    public FragmentInfo(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }


}
