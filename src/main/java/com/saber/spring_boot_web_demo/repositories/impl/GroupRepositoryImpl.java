package com.saber.spring_boot_web_demo.repositories.impl;

import com.saber.spring_boot_web_demo.entities.Group;
import com.saber.spring_boot_web_demo.entities.Permission;
import com.saber.spring_boot_web_demo.entities.User;
import com.saber.spring_boot_web_demo.repositories.GroupRepository;

import java.util.List;
import java.util.Set;

public class GroupRepositoryImpl implements GroupRepository {
    @Override
    public Group insertGroup(Group user) {
        return null;
    }

    @Override
    public Group getGroupById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteGroupById(Integer id) {
        return false;
    }

    @Override
    public Group updateGroup(Integer id, Group newGroup) {
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Set<Permission> getAllPermissionOfGroup(Integer groupId) {
        return null;
    }

    @Override
    public boolean assignPermissionToGroup(Integer groupId, Integer permissionId) {
        return false;
    }

    @Override
    public boolean deletePermissionFromGroup(Integer groupId, Integer permissionId) {
        return false;
    }

    @Override
    public boolean checkAssignPermissionToGroup(Integer groupId, Integer permissionId) {
        return false;
    }

    @Override
    public boolean checkAssignPermissionToGroupWithPermissionName(Integer groupId, String permissionName) {
        return false;
    }

    @Override
    public Set<User> getAllUserOfGroup(Integer groupId) {
        return null;
    }

    @Override
    public boolean assignUserToGroup(Integer groupId, Integer userId) {
        return false;
    }

    @Override
    public boolean deleteUserFromGroup(Integer groupId, Integer userId) {
        return false;
    }

    @Override
    public boolean checkAssignUserToGroup(Integer groupId, Integer userId) {
        return false;
    }

    @Override
    public boolean checkAssignUserToGroupWithUserName(Integer groupId, String userName) {
        return false;
    }
}
