package org.selftravel.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */

public class HomeBeans {
    private List<Datas> data;

    public List<Datas> getData() {
        return data;
    }

    public void setData(List<Datas> data) {
        this.data = data;
    }

    @Table(name = "home")
    public class Datas{
        @Column(name = "id")
        private String id;
        @Column(name = "title")
        private String title;
        @Column(name = "img")
        private String img;
        @Column(name = "distance")
        private int distance;

        private String been;

        private String go;

        public String getBeen() {
            return been;
        }

        public void setBeen(String been) {
            this.been = been;
        }

        public String getGo() {
            return go;
        }

        public void setGo(String go) {
            this.go = go;
        }

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}
