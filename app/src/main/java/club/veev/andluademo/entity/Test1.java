package club.veev.andluademo.entity;

public class Test1 {
    private String title;
    private String subTitle;
    private String content;

    public Test1(String title, String subTitle, String content) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getContent() {
        return content;
    }
}
