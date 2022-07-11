package com.example.mycli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news_table")
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column(name = "news")
    private String news;

    @Column(name = "accepter" )
    private Long accepter_id;

    @Column(name = "accepted")
    private Boolean accepted;

    @Column(name = "date_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Column(name = "finished")
    private Boolean finished;
}
