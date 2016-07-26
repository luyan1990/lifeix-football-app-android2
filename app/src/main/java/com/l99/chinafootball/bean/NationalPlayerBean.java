package com.l99.chinafootball.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lifeix-101 on 2016/6/28.
 */
public class NationalPlayerBean implements Serializable {

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

    public static class CategoryBean  implements Serializable {
        private String categoryName;

        private List<PlayersBean> players;

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<PlayersBean> getPlayers() {
            return players;
        }

        public void setPlayers(List<PlayersBean> players) {
            this.players = players;
        }

        public static class PlayersBean  implements Serializable {
            private String id;
            private String name;
            private String sex;
            private String englishName;
            private String country;
            private String avatar;
            private long birthday;
            private String birthplace;
            private int height;
            private int weight;
            private String position;
            private int jeserysNum;
            private String introduce;
            private String company;

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            private RecordBean recordBean;

            public RecordBean getRecordBean() {
                return recordBean;
            }

            public void setRecordBean(RecordBean recordBean) {
                this.recordBean = recordBean;
            }

            public static class RecordBean  implements Serializable {
                private int assists;
                private int bookings;
                private int dismissals;
                private int gamesplayed;
                private int goals;
                private int playerId;
                private int starts;
                private int substitution;

                public int getAssists() {
                    return assists;
                }

                public void setAssists(int assists) {
                    this.assists = assists;
                }

                public int getBookings() {
                    return bookings;
                }

                public void setBookings(int bookings) {
                    this.bookings = bookings;
                }

                public int getDismissals() {
                    return dismissals;
                }

                public void setDismissals(int dismissals) {
                    this.dismissals = dismissals;
                }

                public int getGamesplayed() {
                    return gamesplayed;
                }

                public void setGamesplayed(int gamesplayed) {
                    this.gamesplayed = gamesplayed;
                }

                public int getGoals() {
                    return goals;
                }

                public void setGoals(int goals) {
                    this.goals = goals;
                }

                public int getPlayerId() {
                    return playerId;
                }

                public void setPlayerId(int playerId) {
                    this.playerId = playerId;
                }

                public int getStarts() {
                    return starts;
                }

                public void setStarts(int starts) {
                    this.starts = starts;
                }

                public int getSubstitution() {
                    return substitution;
                }

                public void setSubstitution(int substitution) {
                    this.substitution = substitution;
                }
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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getEnglishName() {
                return englishName;
            }

            public void setEnglishName(String englishName) {
                this.englishName = englishName;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public int getJeserysNum() {
                return jeserysNum;
            }

            public void setJeserysNum(int jeserysNum) {
                this.jeserysNum = jeserysNum;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }


        }
    }
}
