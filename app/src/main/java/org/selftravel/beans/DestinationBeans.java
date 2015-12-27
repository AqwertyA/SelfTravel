package org.selftravel.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 15-11-30.
 */
@Table(name = "DestinationBeans")
public class DestinationBeans {
    /**
     * "status": 0,
     * "info": "操作成功！",
     * "data": [{"id": 389,"title": "武侯祠博物馆",
     * "been": 23,
     * "go": 24,
     * "type": 4,
     * "img": "\/Public\/Upload\/rinfo\/month_1506\/55865d1b55ac5.jpg",
     * "imgtitle": "武侯祠博物馆",
     * "hasvoice": 1,
     * "attractionsCount": 20,
     * "voiceCount": 14
     * },
     */

    private int status;
    private String info;
    private List<DataEntity> data;

    public List<DataEntity> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    @Table(name = "destination_data")
    public static class DataEntity {
        @Column(name = "id", isId = true)
        private String id;
        @Column(name = "title")
        private String title;
        @Column(name = "been")
        private int been;
        @Column(name = "go")
        private int go;
        @Column(name = "type")
        private int type;
        @Column(name = "img")
        private String img;
        @Column(name = "imgtitle")
        private String imgtitle;
        @Column(name = "hasvoice")
        private int hasvoice;
        @Column(name = "attractionsCount")
        private int attractionsCount;
        @Column(name = "voiceCount")
        private int voiceCount;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public int getBeen() {
            return been;
        }

        public int getGo() {
            return go;
        }

        public int getType() {
            return type;
        }

        public String getImg() {
            return img;
        }

        public String getImgtitle() {
            return imgtitle;
        }

        public int getHasvoice() {
            return hasvoice;
        }

        public int getAttractionsCount() {
            return attractionsCount;
        }

        public int getVoiceCount() {
            return voiceCount;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setBeen(int been) {
            this.been = been;
        }

        public void setGo(int go) {
            this.go = go;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setImgtitle(String imgtitle) {
            this.imgtitle = imgtitle;
        }

        public void setHasvoice(int hasvoice) {
            this.hasvoice = hasvoice;
        }

        public void setAttractionsCount(int attractionsCount) {
            this.attractionsCount = attractionsCount;
        }

        public void setVoiceCount(int voiceCount) {
            this.voiceCount = voiceCount;
        }
    }

}


