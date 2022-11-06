package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vip_permission")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"permission","user"})
public class VipPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "permission_id",nullable = false)
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp created_at;

}
