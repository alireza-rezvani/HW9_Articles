package ir.maktab32.java.homeworks.hw9.articles.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String brief;
    private String content;

    @Column(nullable = false)
    private Date createDate;
    private Date lastUpdateDate;
    private Date publishDate;

    @Column(nullable = false)
    private Boolean isPublished;

    @ManyToMany
    private List<Tag> tags;

    @ManyToOne
    private User author;

    @ManyToOne
    private  Category category;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", publishDate=" + publishDate +
                ", isPublished=" + isPublished +
                ", tags=" + tags +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
