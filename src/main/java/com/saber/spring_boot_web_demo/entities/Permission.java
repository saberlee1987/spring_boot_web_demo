package com.saber.spring_boot_web_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

//@Entity
//@Table(name = "permissions")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"groupPermissions","vipPermissions","blockPermissions"})
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(name = "name_fa")
    private String name_fa;
//    @Column(name = "name_en")
    private String name_en;
    private LocalDateTime created_at;
    private LocalDateTime  updated_at;
    private LocalDateTime  deleted_at;
//    @OneToMany(mappedBy = "permission",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<GroupPermission> groupPermissions;
//    @OneToMany(mappedBy = "permission",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<VipPermission> vipPermissions;
//    @OneToMany(mappedBy = "permission",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<BlockPermission> blockPermissions;
}