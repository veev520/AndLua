package club.veev.andluademo.entity;

/**
 * Created by Veev on 2018/7/4.
 * Tel:         18365264930
 * Email:       veev520@sina.com
 * Function:    TestBean
 */
public class TestBean {

    public static final String TYPE_LUA = "lua";
    public static final String TYPE_TEST_1 = "test1";

    private String type;

    private Object data;

    public TestBean() {
    }

    public TestBean(String type) {
        this.type = type;
    }

    public TestBean(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
