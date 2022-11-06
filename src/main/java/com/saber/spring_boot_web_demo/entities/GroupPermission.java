package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "group_permission")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"permission","group"})
public class GroupPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "permission_id",nullable = false)
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private Group group;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp created_at;

}
