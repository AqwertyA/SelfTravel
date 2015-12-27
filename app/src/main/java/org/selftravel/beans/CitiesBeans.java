package org.selftravel.beans;

import java.util.List;

public class CitiesBeans {


    /**
     * name : 请选择
     * sub : [{"name":"请选择"}]
     * type : 1
     */

    private String name;
    private int type;
    /**
     * name : 请选择
     */

    private List<SubEntity> sub;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSub(List<SubEntity> sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public List<SubEntity> getSub() {
        return sub;
    }

    public static class SubEntity {
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
