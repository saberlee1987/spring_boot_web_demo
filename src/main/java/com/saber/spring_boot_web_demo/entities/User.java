package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

//@Entity
//@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"groupUsers","blockPermissions","vipPermissions"})
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String pass;
    private String email;
//    @Column(name = "active_code")
    private String activeCode;
//    @Column(name = "is_active")
    private UserActive isActive;
//    @Column(name = "remember_token")
    private String rememberToken;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GroupUser> groupUsers;
//     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<BlockPermission> blockPermissions;

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VipPermission> vipPermissions;

}