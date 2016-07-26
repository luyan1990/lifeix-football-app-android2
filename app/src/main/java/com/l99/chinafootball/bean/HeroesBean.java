package com.l99.chinafootball.bean;

import java.util.List;

/**
 * Created by lifeix-101 on 2016/7/13.
 */
public class HeroesBean {

    private List<AssistantCoachBean> assistantCoachBeanList;
    private ChiefCoachBean chiefCoachBean;
    private CompetitionInfoBean competitionInfoBean;
    private String group;
    private int id;
    private String introduce;
    private String name;
    private List<PlayerBean> playerBeanList;
    private long setupDate;
    private TeamInfoBean teamInfoBean;
    private TeamLeaderBean teamLeaderBean;

    public List<AssistantCoachBean> getAssistantCoachBeanList() {
        return assistantCoachBeanList;
    }

    public void setAssistantCoachBeanList(List<AssistantCoachBean> assistantCoachBeanList) {
        this.assistantCoachBeanList = assistantCoachBeanList;
    }

    public ChiefCoachBean getChiefCoachBean() {
        return chiefCoachBean;
    }

    public void setChiefCoachBean(ChiefCoachBean chiefCoachBean) {
        this.chiefCoachBean = chiefCoachBean;
    }

    public CompetitionInfoBean getCompetitionInfoBean() {
        return competitionInfoBean;
    }

    public void setCompetitionInfoBean(CompetitionInfoBean competitionInfoBean) {
        this.competitionInfoBean = competitionInfoBean;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerBean> getPlayerBeanList() {
        return playerBeanList;
    }

    public void setPlayerBeanList(List<PlayerBean> playerBeanList) {
        this.playerBeanList = playerBeanList;
    }

    public long getSetupDate() {
        return setupDate;
    }

    public void setSetupDate(long setupDate) {
        this.setupDate = setupDate;
    }

    public TeamInfoBean getTeamInfoBean() {
        return teamInfoBean;
    }

    public void setTeamInfoBean(TeamInfoBean teamInfoBean) {
        this.teamInfoBean = teamInfoBean;
    }

    public TeamLeaderBean getTeamLeaderBean() {
        return teamLeaderBean;
    }

    public void setTeamLeaderBean(TeamLeaderBean teamLeaderBean) {
        this.teamLeaderBean = teamLeaderBean;
    }

    public static class AssistantCoachBean {

        private String avatar;
        private long birthday;
        private String birthplace;
        private String country;
        private int id;
        private String introduce;
        private String level;
        private String name;
        private String position;

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class ChiefCoachBean {
        private String avatar;
        private long birthday;
        private String birthplace;
        private String company;
        private String country;
        private int id;
        private String introduce;
        private String level;
        private String name;
        private String position;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }

    public static class CompetitionInfoBean {
        private long endDate;
        private String id;
        private String name;
        private long startDate;

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
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

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }
    }

    public static class TeamInfoBean {
        private String flag;
        private int id;
        private String name;

        private TeamCategoryBean teamCategoryBean;

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

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

        public TeamCategoryBean getTeamCategoryBean() {
            return teamCategoryBean;
        }

        public void setTeamCategoryBean(TeamCategoryBean teamCategoryBean) {
            this.teamCategoryBean = teamCategoryBean;
        }

        public static class TeamCategoryBean {
            private long fId;
            private long id;
            private int isLeaf;
            private String name;

            public long getfId() {
                return fId;
            }

            public void setfId(long fId) {
                this.fId = fId;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getIsLeaf() {
                return isLeaf;
            }

            public void setIsLeaf(int isLeaf) {
                this.isLeaf = isLeaf;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class TeamLeaderBean {
        private String avatar;
        private long birthday;
        private String company;
        private String country;
        private int id;
        private String introduce;
        private String name;
        private String position;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
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
    }

    public static class PlayerBean {
        private String avatar;
        private long birthday;
        private String birthplace;
        private String country;
        private String englishName;
        private int height;
        private String id;
        private String introduce;
        private int jerseysNum;
        private String name;
        private String position;
        private RecordBean recordBean;
        private String sex;
        private int weight;

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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getJerseysNum() {
            return jerseysNum;
        }

        public void setJerseysNum(int jerseysNum) {
            this.jerseysNum = jerseysNum;
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

        public RecordBean getRecordBean() {
            return recordBean;
        }

        public void setRecordBean(RecordBean recordBean) {
            this.recordBean = recordBean;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public static class RecordBean {
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
    }


}
