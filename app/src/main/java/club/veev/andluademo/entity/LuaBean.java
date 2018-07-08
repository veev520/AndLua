package club.veev.andluademo.entity;

/**
 * Created by Veev on 2018/7/4.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    LuaBean
 */
public class LuaBean {

    public static final String TYPE_CUSTOM = "custom";

    private int version;
    private int id;
    private String url;
    private String script;
    private String type;

    @Override
    public String toString() {
        return "LuaBean{" +
                "version=" + version +
                ", id=" + id +
                ", url='" + url + '\'' +
                ", script='" + script + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }
}
