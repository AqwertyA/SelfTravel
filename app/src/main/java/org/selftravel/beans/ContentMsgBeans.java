package org.selftravel.beans;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ContentMsgBeans {

    /**
     * status : 0
     * info : 操作成功！
     * data : {"recordCount":40,"dataList":[{"id":928,"adder_id":671,"padd_id":0,"content":"好多异域风情的特色建筑 还有很多的游乐设施 推荐","add_time":"2015-11-19 17:00:50","head_m":"/Public/head/85_85_564d873ac9704.jpg","head_s":"/Public/head/36_36_564d873ac9704.jpg","p_head_m":"","p_head_s":"","name":"西门吹雪","p_name":"","count":0,"childs":[]},{"id":916,"adder_id":665,"padd_id":0,"content":"好多适合摆拍的建筑 穿越穿越最穿越","add_time":"2015-11-19 16:45:29","head_m":"/Public/head/85_85_564d8792b78fe.jpg","head_s":"/Public/head/36_36_564d8792b78fe.jpg","p_head_m":"","p_head_s":"","name":"南山南","p_name":"","count":0,"childs":[]},{"id":905,"adder_id":169,"padd_id":0,"content":"好多耍的 好安逸 还要切开碰碰车！刺激","add_time":"2015-11-19 16:36:36","head_m":"/Public/head/85_85_564984389977e.jpg","head_s":"/Public/head/36_36_564984389977e.jpg","p_head_m":"","p_head_s":"","name":"東来紫气","p_name":"","count":0,"childs":[]},{"id":885,"adder_id":666,"padd_id":0,"content":"还可以划船船 安逸！","add_time":"2015-11-19 16:29:03","head_m":"/Public/head/85_85_564d87f145e0b.jpg","head_s":"/Public/head/36_36_564d87f145e0b.jpg","p_head_m":"","p_head_s":"","name":"北上广深","p_name":"","count":0,"childs":[]},{"id":667,"adder_id":657,"padd_id":0,"content":"比较安静，不会吵闹。","add_time":"2015-11-18 16:26:53","head_m":"/Public/head/564ad1ce78d12_m.jpg","head_s":"/Public/head/564ad1ce78d12_s.jpg","p_head_m":"","p_head_s":"","name":"深海里的星星","p_name":"","count":0,"childs":[]}]}
     */

    private int status;
    private String info;
    /**
     * recordCount : 40
     * dataList : [{"id":928,"adder_id":671,"padd_id":0,"content":"好多异域风情的特色建筑 还有很多的游乐设施 推荐","add_time":"2015-11-19 17:00:50","head_m":"/Public/head/85_85_564d873ac9704.jpg","head_s":"/Public/head/36_36_564d873ac9704.jpg","p_head_m":"","p_head_s":"","name":"西门吹雪","p_name":"","count":0,"childs":[]},{"id":916,"adder_id":665,"padd_id":0,"content":"好多适合摆拍的建筑 穿越穿越最穿越","add_time":"2015-11-19 16:45:29","head_m":"/Public/head/85_85_564d8792b78fe.jpg","head_s":"/Public/head/36_36_564d8792b78fe.jpg","p_head_m":"","p_head_s":"","name":"南山南","p_name":"","count":0,"childs":[]},{"id":905,"adder_id":169,"padd_id":0,"content":"好多耍的 好安逸 还要切开碰碰车！刺激","add_time":"2015-11-19 16:36:36","head_m":"/Public/head/85_85_564984389977e.jpg","head_s":"/Public/head/36_36_564984389977e.jpg","p_head_m":"","p_head_s":"","name":"東来紫气","p_name":"","count":0,"childs":[]},{"id":885,"adder_id":666,"padd_id":0,"content":"还可以划船船 安逸！","add_time":"2015-11-19 16:29:03","head_m":"/Public/head/85_85_564d87f145e0b.jpg","head_s":"/Public/head/36_36_564d87f145e0b.jpg","p_head_m":"","p_head_s":"","name":"北上广深","p_name":"","count":0,"childs":[]},{"id":667,"adder_id":657,"padd_id":0,"content":"比较安静，不会吵闹。","add_time":"2015-11-18 16:26:53","head_m":"/Public/head/564ad1ce78d12_m.jpg","head_s":"/Public/head/564ad1ce78d12_s.jpg","p_head_m":"","p_head_s":"","name":"深海里的星星","p_name":"","count":0,"childs":[]}]
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
        private int recordCount;
        /**
         * id : 928
         * adder_id : 671
         * padd_id : 0
         * content : 好多异域风情的特色建筑 还有很多的游乐设施 推荐
         * add_time : 2015-11-19 17:00:50
         * head_m : /Public/head/85_85_564d873ac9704.jpg
         * head_s : /Public/head/36_36_564d873ac9704.jpg
         * p_head_m :
         * p_head_s :
         * name : 西门吹雪
         * p_name :
         * count : 0
         * childs : []
         */

        private List<DataListEntity> dataList;

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

        public void setDataList(List<DataListEntity> dataList) {
            this.dataList = dataList;
        }

        public int getRecordCount() {
            return recordCount;
        }

        public List<DataListEntity> getDataList() {
            return dataList;
        }

        public static class DataListEntity {
            private int id;
            private int adder_id;
            private int padd_id;
            private String content;
            private String add_time;
            private String head_m;
            private String head_s;
            private String p_head_m;
            private String p_head_s;
            private String name;
            private String p_name;
            private int count;
            private List<?> childs;

            public void setId(int id) {
                this.id = id;
            }

            public void setAdder_id(int adder_id) {
                this.adder_id = adder_id;
            }

            public void setPadd_id(int padd_id) {
                this.padd_id = padd_id;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setHead_m(String head_m) {
                this.head_m = head_m;
            }

            public void setHead_s(String head_s) {
                this.head_s = head_s;
            }

            public void setP_head_m(String p_head_m) {
                this.p_head_m = p_head_m;
            }

            public void setP_head_s(String p_head_s) {
                this.p_head_s = p_head_s;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setP_name(String p_name) {
                this.p_name = p_name;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public void setChilds(List<?> childs) {
                this.childs = childs;
            }

            public int getId() {
                return id;
            }

            public int getAdder_id() {
                return adder_id;
            }

            public int getPadd_id() {
                return padd_id;
            }

            public String getContent() {
                return content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getHead_m() {
                return head_m;
            }

            public String getHead_s() {
                return head_s;
            }

            public String getP_head_m() {
                return p_head_m;
            }

            public String getP_head_s() {
                return p_head_s;
            }

            public String getName() {
                return name;
            }

            public String getP_name() {
                return p_name;
            }

            public int getCount() {
                return count;
            }

            public List<?> getChilds() {
                return childs;
            }
        }
    }
}
