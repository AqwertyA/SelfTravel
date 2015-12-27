package org.selftravel.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class HomeHeadBeans {
    private List<HeadDatas> data;

    public List<HeadDatas> getData() {
        return data;
    }

    public void setData(List<HeadDatas> data) {
        this.data = data;
    }

    @Table(name = "home_head")
    public class HeadDatas{
        @Column(name = "title")
        private String title;
        @Column(name = "wapimg")
        private String wapimg;
        @Column(name = "wapurl")
        private String wapurl;

        public String getWapimg() {
            return wapimg;
        }

        public void setWapimg(String wapimg) {
            this.wapimg = wapimg;
        }

        public String getWapurl() {
            return wapurl;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
