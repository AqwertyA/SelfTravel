package org.selftravel.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class FindBeans {

    /**
     * status : 0
     * info : 操作成功！
     * data : [{"title":"中秋自游去旅行","wapimg":"/Public/Upload/specialevents/month_1509/5604e3704773c.jpg","wapurl":"/Specialevents/wap/13/index.html"},{"title":"寻找最有年味儿的城市","wapimg":"/Public/Upload/specialevents/month_1509/5600fedc1dabb.png","wapurl":"/Specialevents/web/10/index.html"},{"title":"别去滑雪，当心上瘾！","wapimg":"/Public/Upload/specialevents/month_1509/5600fee6389b3.png","wapurl":"/Specialevents/web/7/index.html"},{"title":"温泉水滑洗凝脂-四川十大顶级温泉推荐","wapimg":"/Public/Upload/specialevents/month_1504/553f58a251e6a.png","wapurl":"/Specialevents/web/8/index.html"}]
     */

    private int status;
    private String info;
    /**
     * title : 中秋自游去旅行
     * wapimg : /Public/Upload/specialevents/month_1509/5604e3704773c.jpg
     * wapurl : /Specialevents/wap/13/index.html
     */

    private List<DataEntity> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public List<DataEntity> getData() {
        return data;
    }

    @Table(name = "find_data")
    public static class DataEntity {
        @Column(name = "title")
        private String title;
        @Column(name = "wapimg")
        private String wapimg;
        @Column(name = "wapurl")
        private String wapurl;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setWapimg(String wapimg) {
            this.wapimg = wapimg;
        }

        public void setWapurl(String wapurl) {
            this.wapurl = wapurl;
        }

        public String getTitle() {
            return title;
        }

        public String getWapimg() {
            return wapimg;
        }

        public String getWapurl() {
            return wapurl;
        }
    }
}
