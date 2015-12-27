package org.selftravel.beans;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ContentPicBeans {

    private List<ContentPic> data;

    public List<ContentPic> getData() {
        return data;
    }

    public void setData(List<ContentPic> data) {
        this.data = data;
    }

    public class ContentPic{
        private String img;
        private String head_s;
        private String username;
        private String showtime;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHead_s() {
            return head_s;
        }

        public void setHead_s(String head_s) {
            this.head_s = head_s;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }
    }
}
