package com.l99.chinafootball.bean;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/25.
 */
public class WemediaRightListBean {


    /**
     * iconUrl :
     * id : 80891115649354
     * menus : []
     * name : 男足
     * page :
     */

    private String iconUrl;
    private String id;
    private String name;
    private String page;
    private List<?> menus;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<?> getMenus() {
        return menus;
    }

    public void setMenus(List<?> menus) {
        this.menus = menus;
    }
}
