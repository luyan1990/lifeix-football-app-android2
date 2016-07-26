package com.l99.chinafootball.bean;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/21.
 */
public class BasicPlayerBean {

    private int id;
    private String name;
    private String englishName;
    private String avatar;
    private String country;
    private String sex;
    private int height;
    private int weight;
    private long birthday;
    private String birthplace;
    private String introuceUrl;
    private String matchUrl;
    private String recordUrl;
    private String sports_career;
    private String competition_record;
    private String data_statisitic;

    public String getSports_career() {
        return sports_career;
    }

    public void setSports_career(String sports_career) {
        this.sports_career = sports_career;
    }

    public String getCompetition_record() {
        return competition_record;
    }

    public void setCompetition_record(String competition_record) {
        this.competition_record = competition_record;
    }

    public String getData_statisitic() {
        return data_statisitic;
    }

    public void setData_statisitic(String data_statisitic) {
        this.data_statisitic = data_statisitic;
    }

    /**
     * id : 26
     * name : 广州恒大淘宝
     * flag :
     * teamCategory : {"id":8089497078257,"name":"职业足球俱乐部","fId":8089869029664,"isLeaf":1,"categories":[]}
     * position : 守门员
     * teamType : 1
     */

    private ClubBean club;
    /**
     * id : 1
     * name : 中国
     * flag : CF_flag_China.jpg
     * teamCategory : {"id":8089215429517,"name":"龙之队","fId":8089397712087,"isLeaf":1,"categories":[]}
     * position : 守门员
     * teamType : 0
     */

    private NationTeamBean nationTeam;
    /**
     * 运动生涯 : http://192.168.50.154:8000/football/games/players/11/html?key=visitor
     */

    private List<UrlsBean> urls;
    /**
     * id : 26
     * name : 广州恒大淘宝
     * flag :
     * teamCategory : {"id":8089497078257,"name":"职业足球俱乐部","fId":8089869029664,"isLeaf":1,"categories":[]}
     * position : 守门员
     * teamType : 1
     */

    private List<CareerBean> career;
    private List<PlayerVideosBean> playerVideos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getIntrouceUrl() {
        return introuceUrl;
    }

    public void setIntrouceUrl(String introuceUrl) {
        this.introuceUrl = introuceUrl;
    }

    public String getMatchUrl() {
        return matchUrl;
    }

    public void setMatchUrl(String matchUrl) {
        this.matchUrl = matchUrl;
    }

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

    public ClubBean getClub() {
        return club;
    }

    public void setClub(ClubBean club) {
        this.club = club;
    }

    public NationTeamBean getNationTeam() {
        return nationTeam;
    }

    public void setNationTeam(NationTeamBean nationTeam) {
        this.nationTeam = nationTeam;
    }

    public List<UrlsBean> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlsBean> urls) {
        this.urls = urls;
    }

    public List<CareerBean> getCareer() {
        return career;
    }

    public void setCareer(List<CareerBean> career) {
        this.career = career;
    }

    public List<?> getPlayerVideos() {
        return playerVideos;
    }

    public void setPlayerVideos(List<PlayerVideosBean> playerVideos) {
        this.playerVideos = playerVideos;
    }

    public static class ClubBean {
        private int id;
        private String name;
        private String flag;
        /**
         * id : 8089497078257
         * name : 职业足球俱乐部
         * fId : 8089869029664
         * isLeaf : 1
         * categories : []
         */

        private TeamCategoryBean teamCategory;
        private String position;
        private int teamType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public TeamCategoryBean getTeamCategory() {
            return teamCategory;
        }

        public void setTeamCategory(TeamCategoryBean teamCategory) {
            this.teamCategory = teamCategory;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getTeamType() {
            return teamType;
        }

        public void setTeamType(int teamType) {
            this.teamType = teamType;
        }

        public static class TeamCategoryBean {
            private long id;
            private String name;
            private long fId;
            private int isLeaf;
            private List<?> categories;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getFId() {
                return fId;
            }

            public void setFId(long fId) {
                this.fId = fId;
            }

            public int getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(int isLeaf) {
                this.isLeaf = isLeaf;
            }

            public List<?> getCategories() {
                return categories;
            }

            public void setCategories(List<?> categories) {
                this.categories = categories;
            }
        }
    }

    public static class NationTeamBean {
        private int id;
        private String name;
        private String flag;
        /**
         * id : 8089215429517
         * name : 龙之队
         * fId : 8089397712087
         * isLeaf : 1
         * categories : []
         */

        private TeamCategoryBean teamCategory;
        private String position;
        private int teamType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public TeamCategoryBean getTeamCategory() {
            return teamCategory;
        }

        public void setTeamCategory(TeamCategoryBean teamCategory) {
            this.teamCategory = teamCategory;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getTeamType() {
            return teamType;
        }

        public void setTeamType(int teamType) {
            this.teamType = teamType;
        }

        public static class TeamCategoryBean {
            private long id;
            private String name;
            private long fId;
            private int isLeaf;
            private List<?> categories;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getFId() {
                return fId;
            }

            public void setFId(long fId) {
                this.fId = fId;
            }

            public int getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(int isLeaf) {
                this.isLeaf = isLeaf;
            }

            public List<?> getCategories() {
                return categories;
            }

            public void setCategories(List<?> categories) {
                this.categories = categories;
            }
        }
    }


    public static class CareerBean {
        private int id;
        private String name;
        private String flag;
        /**
         * id : 8089497078257
         * name : 职业足球俱乐部
         * fId : 8089869029664
         * isLeaf : 1
         * categories : []
         */

        private TeamCategoryBean teamCategory;
        private String position;
        private int teamType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public TeamCategoryBean getTeamCategory() {
            return teamCategory;
        }

        public void setTeamCategory(TeamCategoryBean teamCategory) {
            this.teamCategory = teamCategory;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getTeamType() {
            return teamType;
        }

        public void setTeamType(int teamType) {
            this.teamType = teamType;
        }

        public static class TeamCategoryBean {
            private long id;
            private String name;
            private long fId;
            private int isLeaf;
            private List<?> categories;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getFId() {
                return fId;
            }

            public void setFId(long fId) {
                this.fId = fId;
            }

            public int getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(int isLeaf) {
                this.isLeaf = isLeaf;
            }

            public List<?> getCategories() {
                return categories;
            }

            public void setCategories(List<?> categories) {
                this.categories = categories;
            }
        }
    }

    private class PlayerVideosBean {
    }

    private class UrlsBean {

    }
}
