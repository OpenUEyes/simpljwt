package com.company.simpljwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user")
public class User implements Comparable<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    //    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Article> articles = new HashSet<>();

    @Builder
    public User(Long id, String login, String password, Role role, String name, int age, Set<Article> articles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.age = age;

        if (articles != null) {
            articles.forEach(article -> article.setUser(this));
            this.articles = articles;
        }
    }

    @Override
    public int compareTo(User user) {
        return Long.compare(id, user.getId());
    }
}
