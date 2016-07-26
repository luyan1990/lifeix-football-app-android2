package com.l99.chinafootball.bean;

/**
 * Created by lifeix-101 on 2016/6/27.
 */
public class CompetitionTeamBean {

    private int awayScore;
    private int id;
    private String position;
    private String stage;
    private long startDate;
    private long startTime;
    private int state;
    private String url;

    private CompetitionInfoBean competitionInfo;

    private AwayTeamBean awayTeam;

    private CourtBean courtBean;

    private String group;

    private int hostScore;

    private HostTeamBean hostTeamBean;

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CompetitionInfoBean getCompetitionInfo() {
        return competitionInfo;
    }

    public void setCompetitionInfo(CompetitionInfoBean competitionInfo) {
        this.competitionInfo = competitionInfo;
    }

    public AwayTeamBean getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeamBean awayTeam) {
        this.awayTeam = awayTeam;
    }

    public CourtBean getCourtBean() {
        return courtBean;
    }

    public void setCourtBean(CourtBean courtBean) {
        this.courtBean = courtBean;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getHostScore() {
        return hostScore;
    }

    public void setHostScore(int hostScore) {
        this.hostScore = hostScore;
    }

    public HostTeamBean getHostTeamBean() {
        return hostTeamBean;
    }

    public void setHostTeamBean(HostTeamBean hostTeamBean) {
        this.hostTeamBean = hostTeamBean;
    }

    public static class CompetitionInfoBean {
        private String id;
        private String name;
        private long startDate;
        private long endDate;

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

        public long getEndDate() {
            return endDate;
        }

        public void setEndDate(long endDate) {
            this.endDate = endDate;
        }
    }

    public static class AwayTeamBean {
        private int competitionId;
        private int competitionTeamId;
        private String desc;
        private int id;
        private int jerseyId;
        private long setupDate;

        private TeamInfoBean teamInfoBean;

        public int getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(int competitionId) {
            this.competitionId = competitionId;
        }

        public int getCompetitionTeamId() {
            return competitionTeamId;
        }

        public void setCompetitionTeamId(int competitionTeamId) {
            this.competitionTeamId = competitionTeamId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJerseyId() {
            return jerseyId;
        }

        public void setJerseyId(int jerseyId) {
            this.jerseyId = jerseyId;
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
    }

    public static class CourtBean {
        private long buildTime;
        private int capacity;
        private String country;
        private int id;
        private String images;
        private String name;
        private String position;

        public long getBuildTime() {
            return buildTime;
        }

        public void setBuildTime(long buildTime) {
            this.buildTime = buildTime;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
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

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
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

    public static class HostTeamBean {
        private int competitionId;
        private int competitionTeamId;
        private String desc;
        private int id;
        private long setupDate;

        private TeamInfoBean teamInfoBean;

        public int getCompetitionId() {
            return competitionId;
        }

        public void setCompetitionId(int competitionId) {
            this.competitionId = competitionId;
        }

        public int getCompetitionTeamId() {
            return competitionTeamId;
        }

        public void setCompetitionTeamId(int competitionTeamId) {
            this.competitionTeamId = competitionTeamId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }


}
