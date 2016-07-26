package com.l99.chinafootball.bean;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/5.
 */
public class RefereesBean {


    private String topName;

    private List<CategoryBean> category;

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {

        private String categoryName;

        private List<RefereeBean> referees;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<RefereeBean> getReferees() {
            return referees;
        }

        public void setReferees(List<RefereeBean> referees) {
            this.referees = referees;
        }

        public static class RefereeBean {

            private String association;
            private String avatar;
            private long birthday;
            private String birthplace;
            private String category;
            private String country;
            private int fifaTopANum;
            private String function;
            private int id;
            private String level;
            private String name;
            private String sex;
            private String sinceInternational;
            private int topLeagueNum;

            public String getAssociation() {
                return association;
            }

            public void setAssociation(String association) {
                this.association = association;
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

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getFifaTopANum() {
                return fifaTopANum;
            }

            public void setFifaTopANum(int fifaTopANum) {
                this.fifaTopANum = fifaTopANum;
            }

            public String getFunction() {
                return function;
            }

            public void setFunction(String function) {
                this.function = function;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSinceInternational() {
                return sinceInternational;
            }

            public void setSinceInternational(String sinceInternational) {
                this.sinceInternational = sinceInternational;
            }

            public int getTopLeagueNum() {
                return topLeagueNum;
            }

            public void setTopLeagueNum(int topLeagueNum) {
                this.topLeagueNum = topLeagueNum;
            }

            @Override
            public String toString() {
                return "RefereeBean{" +
                        "association='" + association + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", birthday=" + birthday +
                        ", birthplace='" + birthplace + '\'' +
                        ", category='" + category + '\'' +
                        ", country='" + country + '\'' +
                        ", fifaTopANum=" + fifaTopANum +
                        ", function='" + function + '\'' +
                        ", id=" + id +
                        ", level='" + level + '\'' +
                        ", name='" + name + '\'' +
                        ", sex='" + sex + '\'' +
                        ", sinceInternational='" + sinceInternational + '\'' +
                        ", topLeagueNum=" + topLeagueNum +
                        '}';
            }
        }
    }
}
