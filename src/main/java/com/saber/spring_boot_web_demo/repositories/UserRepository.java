package com.saber.spring_boot_web_demo.repositories;

import com.saber.spring_boot_web_demo.entities.Group;
import com.saber.spring_boot_web_demo.entities.Permission;
import com.saber.spring_boot_web_demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {

    ///<editor-fold desc="user">
        User insertUser(User user);
        User getUserById(Integer id);
        boolean deleteUserById(Integer id);
        User updateUser(Integer id,User newUser);
        List<User> getAllUsers();
    ///</editor-fold>

    ///<editor-fold desc="group">
        Set<Group> getAllGroupOfUser(Integer userId);
        boolean assignGroupToUser(Integer userId,Integer groupId);
        boolean deleteGroupFromUser(Integer userId,Integer groupId);
        boolean checkAssignGroupToUser(Integer userId,Integer groupId);
        boolean checkAssignGroupToUserWithGroupName(Integer userId,String groupName);

    ///</editor-fold>

    ///<editor-fold desc="vip_permission">
    Set<Permission> getAllVipPermissionOfUser(Integer userId);
        boolean assignVipPermissionToUser(Integer userId,Integer vipPermissionId);
        boolean deleteVipPermissionFromUser(Integer userId,Integer vipPermissionId);
        boolean checkAssignVipPermissionToUser(Integer userId,Integer vipPermissionId);

    ///</editor-fold>

    ///<editor-fold desc="block_permission">
    Set<Permission> getAllBlockPermissionOfUser(Integer userId);
        boolean assignBlockPermissionToUser(Integer userId,Integer blockPermissionId);
        boolean deleteBlockPermissionFromUser(Integer userId,Integer blockPermissionId);
        boolean checkAssignBlockPermissionToUser(Integer userId,Integer blockPermissionId);
    ///</editor-fold>
}