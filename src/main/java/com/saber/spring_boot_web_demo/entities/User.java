package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"groupUsers","blockPermissions","vipPermissions"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String pass;
    private String email;
    @Column(name = "active_code")
    private String activeCode;
    @Column(name = "is_active")
    private UserActive isActive;
    @Column(name = "remember_token")
    private String rememberToken;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp created_at;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp updated_at;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp deleted_at;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GroupUser> groupUsers;
     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BlockPermission> blockPermissions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VipPermission> vipPermissions;

}