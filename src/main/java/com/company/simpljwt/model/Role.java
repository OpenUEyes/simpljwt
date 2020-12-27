package com.company.simpljwt.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @OneToOne(mappedBy = "role")
    private Account account;
}