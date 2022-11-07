package com.saber.spring_boot_web_demo.repositories;

import com.saber.spring_boot_web_demo.entities.Group;
import com.saber.spring_boot_web_demo.entities.Permission;
import com.saber.spring_boot_web_demo.entities.User;

import java.util.List;
import java.util.Set;

public interface GroupRepository {

    ///<editor-fold desc="group">
    Group insertGroup(Group user);
    Group getGroupById(Integer id);
    boolean deleteGroupById(Integer id);
    Group updateGroup(Integer id,Group newGroup);
    List<Group> getAllGroups();
    ///</editor-fold>

    ///<editor-fold desc="permission    ">
    Set<Permission> getAllPermissionOfGroup(Integer groupId);
    boolean assignPermissionToGroup(Integer groupId,Integer permissionId);
    boolean deletePermissionFromGroup(Integer groupId,Integer permissionId);
    boolean checkAssignPermissionToGroup(Integer groupId,Integer permissionId);
    boolean checkAssignPermissionToGroupWithPermissionName(Integer groupId,String permissionName);

    ///</editor-fold>

    ///<editor-fold desc="user">
    Set<User> getAllUserOfGroup(Integer groupId);
    boolean assignUserToGroup(Integer groupId,Integer userId);
    boolean deleteUserFromGroup(Integer groupId,Integer userId);
    boolean checkAssignUserToGroup(Integer groupId,Integer userId);
    boolean checkAssignUserToGroupWithUserName(Integer groupId,String userName);

    ///</editor-fold>
}
