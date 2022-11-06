package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "group_user")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"user","group"})
public class GroupUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp created_at;

}
