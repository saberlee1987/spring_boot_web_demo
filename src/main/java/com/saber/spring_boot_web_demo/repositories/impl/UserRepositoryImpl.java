package com.saber.spring_boot_web_demo.repositories.impl;

import com.saber.spring_boot_web_demo.entities.Group;
import com.saber.spring_boot_web_demo.entities.Permission;
import com.saber.spring_boot_web_demo.entities.User;
import com.saber.spring_boot_web_demo.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User insertUser(User user) {
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return false;
    }

    @Override
    public User updateUser(Integer id, User newUser) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Group> getAllGroupOfUser(Integer userId) {
        return null;
    }

    @Override
    public boolean assignGroupToUser(Integer userId, Integer groupId) {
        return false;
    }

    @Override
    public boolean deleteGroupFromUser(Integer userId, Integer groupId) {
        return false;
    }

    @Override
    public boolean checkAssignGroupToUser(Integer userId, Integer groupId) {
        return false;
    }

    @Override
    public List<Permission> getAllVipPermissionOfUser(Integer userId) {
        return null;
    }

    @Override
    public boolean assignVipPermissionToUser(Integer userId, Integer vipPermissionId) {
        return false;
    }

    @Override
    public boolean deleteVipPermissionFromUser(Integer userId, Integer vipPermissionId) {
        return false;
    }

    @Override
    public boolean checkAssignVipPermissionToUser(Integer userId, Integer vipPermissionId) {
        return false;
    }

    @Override
    public List<Permission> getAllBlockPermissionOfUser(Integer userId) {
        return null;
    }

    @Override
    public boolean assignBlockPermissionToUser(Integer userId, Integer blockPermissionId) {
        return false;
    }

    @Override
    public boolean deleteBlockPermissionFromUser(Integer userId, Integer blockPermissionId) {
        return false;
    }

    @Override
    public boolean checkAssignBlockPermissionToUser(Integer userId, Integer blockPermissionId) {
        return false;
    }
}
