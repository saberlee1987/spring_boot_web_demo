package com.saber.spring_boot_web_demo.repositories.impl;

import com.saber.spring_boot_web_demo.entities.*;
import com.saber.spring_boot_web_demo.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User insertUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        Query query = entityManager.createQuery("delete from User u where u.id=:id").setParameter("id", id);
        int rowAffected = query.executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public User updateUser(Integer id, User newUser) {
        User user = entityManager.find(User.class, id);
        updateUserByNewUser(newUser,user);
        entityManager.persist(user);
        return user;
    }

    private void updateUserByNewUser(User newUser,User oldUser){
        oldUser.setName(newUser.getName());
        oldUser.setPass(newUser.getPass());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setActiveCode(newUser.getActiveCode());
        oldUser.setIsActive(newUser.getIsActive());
        oldUser.setRememberToken(newUser.getRememberToken());
        oldUser.setGroupUsers(newUser.getGroupUsers());
        oldUser.setBlockPermissions(newUser.getBlockPermissions());
        oldUser.setVipPermissions(newUser.getVipPermissions());

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Set<Group> getAllGroupOfUser(Integer userId) {
        Set<GroupUser> groupUsers = entityManager.find(User.class, userId).getGroupUsers();
        Set<Group> groupList = new HashSet<>();
        groupUsers.forEach(groupUser -> groupList.add(groupUser.getGroup()));
        return groupList;
    }

    @Override
    public boolean assignGroupToUser(Integer userId, Integer groupId) {
        int rowAffected = entityManager.createNativeQuery("insert into group_user (user_id,group_id,created_at) values(?,?,?)")
                .setParameter(1, userId)
                .setParameter(2, groupId)
                .setParameter(3, new Timestamp(Instant.now().toEpochMilli()))
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean deleteGroupFromUser(Integer userId, Integer groupId) {
        int rowAffected = entityManager.createNativeQuery("delete from group_user g where g.user_id=? and g.group_id=?")
                .setParameter(1, userId)
                .setParameter(2, groupId)
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean checkAssignGroupToUser(Integer userId, Integer groupId) {
        Long result = entityManager.createQuery("select count(g) from GroupUser g where g.user.id=:userId and g.group.id=:groupId", Long.class)
                .setParameter("userId", userId)
                .setParameter("groupId", groupId)
                .getSingleResult();
        return result > 0;
    }

    @Override
    public boolean checkAssignGroupToUserWithGroupName(Integer userId, String groupName) {
        Integer groupId = entityManager.createQuery("select g.id from Group g where g.name_en=:name_en", Integer.class)
                .setParameter("name_en", groupName)
                .getSingleResult();
        return checkAssignGroupToUser(userId,groupId);
    }

    @Override
    public Set<Permission> getAllVipPermissionOfUser(Integer userId) {
        User user = entityManager.find(User.class, userId);
        Set<VipPermission> vipPermissions = user.getVipPermissions();
       return vipPermissions.stream().map(VipPermission::getPermission).collect(Collectors.toSet());
    }

    @Override
    public boolean assignVipPermissionToUser(Integer userId, Integer vipPermissionId) {
        int rowAffected = entityManager.createNativeQuery("insert into vip_permission (user_id,permission_id,created_at) values(?,?,?)")
                .setParameter(1, userId)
                .setParameter(2, vipPermissionId)
                .setParameter(3, new Timestamp(Instant.now().toEpochMilli()))
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean deleteVipPermissionFromUser(Integer userId, Integer vipPermissionId) {
        int rowAffected = entityManager.createNativeQuery("delete from vip_permission v where v.user_id=? and v.permission_id=?")
                .setParameter(1, userId)
                .setParameter(2, vipPermissionId)
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean checkAssignVipPermissionToUser(Integer userId, Integer vipPermissionId) {
        Long result = entityManager.createQuery("select count(v) from VipPermission v where v.user.id=:userId and v.permission.id=:vipPermissionId", Long.class)
                .setParameter("userId", userId)
                .setParameter("vipPermissionId", vipPermissionId)
                .getSingleResult();
        return result > 0;
    }

    @Override
    public Set<Permission> getAllBlockPermissionOfUser(Integer userId) {
        User user = entityManager.find(User.class, userId);
        Set<BlockPermission> blockPermissions = user.getBlockPermissions();
        return blockPermissions.stream().map(BlockPermission::getPermission).collect(Collectors.toSet());

    }

    @Override
    public boolean assignBlockPermissionToUser(Integer userId, Integer blockPermissionId) {
        int rowAffected = entityManager.createNativeQuery("insert into block_permission (user_id,permission_id,created_at) values(?,?,?)")
                .setParameter(1, userId)
                .setParameter(2, blockPermissionId)
                .setParameter(3, new Timestamp(Instant.now().toEpochMilli()))
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean deleteBlockPermissionFromUser(Integer userId, Integer blockPermissionId) {
        int rowAffected = entityManager.createNativeQuery("delete from block_permission b where b.user_id=? and b.permission_id=?")
                .setParameter(1, userId)
                .setParameter(2, blockPermissionId)
                .executeUpdate();
        return rowAffected > 0;
    }

    @Override
    public boolean checkAssignBlockPermissionToUser(Integer userId, Integer blockPermissionId) {
        Long result = entityManager.createQuery("select count(b) from BlockPermission b where b.user.id=:userId and b.permission.id=:blockPermissionId", Long.class)
                .setParameter("userId", userId)
                .setParameter("blockPermissionId", blockPermissionId)
                .getSingleResult();
        return result > 0;
    }
}
