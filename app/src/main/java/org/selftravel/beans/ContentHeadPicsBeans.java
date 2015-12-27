package org.selftravel.beans;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ContentHeadPicsBeans {

    /**
     * status : 0
     * info : 操作成功！
     * data : {"id":5668,"title":"南湖公园","been":10,"go":5,"description":"","longitude":104.058343,"latitude":30.503328,"imgList":[{"img":"/Public/Upload/rinfo/month_1508/55d6d7338bed6.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d725e7e5e.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6dd623f4.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6b17cd20.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d69ebda61.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6933fca5.jpg","imgtitle":"南湖公园"}],"attractionsCount":10,"attractionsList":[],"voiceCount":0,"isbeen":0,"isgo":0,"photoid":0,"photofirstimg":""}
     */

    private int status;
    private String info;
    /**
     * id : 5668
     * title : 南湖公园
     * been : 10
     * go : 5
     * description :
     * longitude : 104.058343
     * latitude : 30.503328
     * imgList : [{"img":"/Public/Upload/rinfo/month_1508/55d6d7338bed6.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d725e7e5e.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6dd623f4.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6b17cd20.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d69ebda61.jpg","imgtitle":"南湖公园"},{"img":"/Public/Upload/rinfo/month_1508/55d6d6933fca5.jpg","imgtitle":"南湖公园"}]
     * attractionsCount : 10
     * attractionsList : []
     * voiceCount : 0
     * isbeen : 0
     * isgo : 0
     * photoid : 0
     * photofirstimg :
     */

    private DataEntity data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int id;
        private String title;
        private int been;
        private int go;
        private String description;
        private double longitude;
        private double latitude;
        private int attractionsCount;
        private int voiceCount;
        private int isbeen;
        private int isgo;
        private int photoid;
        private String photofirstimg;
        /**
         * img : /Public/Upload/rinfo/month_1508/55d6d7338bed6.jpg
         * imgtitle : 南湖公园
         */

        private List<ImgListEntity> imgList;
        private List<?> attractionsList;

        public void setId(int id) {
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

        public void setDescription(String description) {
            this.description = description;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setAttractionsCount(int attractionsCount) {
            this.attractionsCount = attractionsCount;
        }

        public void setVoiceCount(int voiceCount) {
            this.voiceCount = voiceCount;
        }

        public void setIsbeen(int isbeen) {
            this.isbeen = isbeen;
        }

        public void setIsgo(int isgo) {
            this.isgo = isgo;
        }

        public void setPhotoid(int photoid) {
            this.photoid = photoid;
        }

        public void setPhotofirstimg(String photofirstimg) {
            this.photofirstimg = photofirstimg;
        }

        public void setImgList(List<ImgListEntity> imgList) {
            this.imgList = imgList;
        }

        public void setAttractionsList(List<?> attractionsList) {
            this.attractionsList = attractionsList;
        }

        public int getId() {
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

        public String getDescription() {
            return description;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public int getAttractionsCount() {
            return attractionsCount;
        }

        public int getVoiceCount() {
            return voiceCount;
        }

        public int getIsbeen() {
            return isbeen;
        }

        public int getIsgo() {
            return isgo;
        }

        public int getPhotoid() {
            return photoid;
        }

        public String getPhotofirstimg() {
            return photofirstimg;
        }

        public List<ImgListEntity> getImgList() {
            return imgList;
        }

        public List<?> getAttractionsList() {
            return attractionsList;
        }

        public static class ImgListEntity {
            private String img;
            private String imgtitle;

            public void setImg(String img) {
                this.img = img;
            }

            public void setImgtitle(String imgtitle) {
                this.imgtitle = imgtitle;
            }

            public String getImg() {
                return img;
            }

            public String getImgtitle() {
                return imgtitle;
            }
        }
    }
}

