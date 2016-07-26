package com.l99.chinafootball.bean;

/**
 * Created by lifeix-101 on 2016/6/29.
 */
public class ElearningTrainingPageListBean {


    /**
     * id : 5791739de385a8820f4ae11d
     * title : video 2
     * video : {"id":"5791739de385a8820f4ae09f","imagePath":"elearning/fmc2014/part1/medias/th/fwc14-m01-bra-cro-02.jpg"}
     */

    private String id;
    private String title;
    /**
     * id : 5791739de385a8820f4ae09f
     * imagePath : elearning/fmc2014/part1/medias/th/fwc14-m01-bra-cro-02.jpg
     */

    private VideoBean video;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public static class VideoBean {
        private String id;
        private String imagePath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }
}
