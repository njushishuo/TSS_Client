package software.nju.tssclient.model.entity;

/**
 * Created by ss14 on 2017/6/12.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 班级
 */
public class Group implements Serializable {

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;

}
