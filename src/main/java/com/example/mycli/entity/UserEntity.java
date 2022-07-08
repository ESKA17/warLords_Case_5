package com.example.mycli.entity;

import com.example.mycli.model.SubjectType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_en")
    @SequenceGenerator(name = "user_en", sequenceName = "user_en", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @OneToOne
    @JoinColumn(name = "auth_data", nullable = false, referencedColumnName = "id")
    private AuthData authdata;

    @ElementCollection
    @Column(name = "subjects", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<SubjectType> subjectTypeList;

    @ManyToMany
    @Column(name = "connections")
    private List<UserEntity> connections;

    @OneToOne
    @JoinColumn(name = "user_information", nullable = false, referencedColumnName = "id")
    private UserInformation userInformation;

    @Column(name = "rating")
    private Integer rating;

}
