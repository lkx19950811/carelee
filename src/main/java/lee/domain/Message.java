package lee.domain;

/**
 * 描述:留言板信息类
 *
 * @author Leo
 * @create 2017-12-19 上午 9:23
 */
public class Message {
    private String title;
    private String content;
    private String author;
    private Integer ctime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCtime() {
        return ctime;
    }

    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (!title.equals(message.title)) return false;
        if (!content.equals(message.content)) return false;
        if (!author.equals(message.author)) return false;
        return ctime.equals(message.ctime);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + ctime.hashCode();
        return result;
    }
}
