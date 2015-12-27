package org.selftravel.beans;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Administrator on 15-12-4.
 */
@Table(name = "photo_list")

public class PhotoListBeans {

    @Column(name = "data")
    private String data;
    @Column(name = "uri")
    private String uri;
    @Column(name = "content")
    private String content;
    @Column(name = "id",isId = true)
    private int id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
