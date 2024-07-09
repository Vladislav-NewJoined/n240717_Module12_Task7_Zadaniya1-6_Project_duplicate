package task11_9_1.zadaniye7.models;

public class PostModel { // Этот продублирован в папке/package models
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostModel(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
