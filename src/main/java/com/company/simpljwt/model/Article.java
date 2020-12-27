package com.company.simpljwt.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Data
@Entity
@Table(name = "article")
public class Article implements Comparable<Article> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Article(Long id, Color color, String text, User user) {
        this.id = id;
        this.color = color;
        this.text = text;

        if (user != null) {
            user.getArticles().add(this);
            this.user = user;
        }
    }

    @Override
    public int compareTo(Article article) {
        return Long.compare(id, article.getId());
    }
}