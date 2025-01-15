package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

//@Entity
//@Table(name = "groups")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"groupUsers","groupPermissions"})
public class Group {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "name_fa")
    private String name_fa;
//    @Column(name = "name_en")
    private String name_en;
    private LocalDateTime created_at;
    private LocalDateTime  updated_at;
    private LocalDateTime  deleted_at;
//    @OneToMany(mappedBy = "group",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<GroupUser> groupUsers;

//    @OneToMany(mappedBy = "group",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<GroupPermission> groupPermissions;
}