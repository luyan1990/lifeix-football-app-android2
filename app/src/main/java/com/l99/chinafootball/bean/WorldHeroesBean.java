package com.l99.chinafootball.bean;

/**
 * Created by lifeix-101 on 2016/7/7.
 */
public class WorldHeroesBean {

    private String avatar;
    private long birthday;
    private String birthplace;
    private String company;
    private String country;
    private String englishName;
    private String id;
    private int height;
    private int weight;
    private int jerseysNum;
    private String introduce;
    private String level;
    private String name;
    private String position;
    private String sex;

    public WorldHeroesBean(String avatar, long birthday, String birthplace, String company, String country, String englishName,
                           String id, int height, int weight, int jerseysNum, String introduce,
                           String level, String name, String position, String sex) {
        this.avatar = avatar;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.company = company;
        this.country = country;
        this.englishName = englishName;
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.jerseysNum = jerseysNum;
        this.introduce = introduce;
        this.level = level;
        this.name = name;
        this.position = position;
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getJerseysNum() {
        return jerseysNum;
    }

    public void setJerseysNum(int jerseysNum) {
        this.jerseysNum = jerseysNum;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
