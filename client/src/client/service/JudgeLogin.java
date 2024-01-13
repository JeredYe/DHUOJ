/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.service;

import client.io.xml.User_io;
import client.model.User;
import java.util.List;

/**
 *
 * @author ytxlo
 */
public class JudgeLogin {
    private List<User> userList;
    //online
    public void loginWrite(User user){
        try {
            User_io io = new User_io();
            userList = io.getUserList();
            if (isExit(user)){
                update(user);
            }
            else{
                userList.add(user);
                io.writeUserList(userList);
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //offline
    public User loginJudge(User user){
        try {
            User_io io = new User_io();
            userList = io.getUserList();
            if(isExit(user)){
                return select(user);
            }else{
                return null;
            }    
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private User select(User selectUser){
        for(User user:userList){
            if(user.getUserName().equals(selectUser.getUserName())&&user.getPassword().equals(selectUser.getPassword())){
                return user;
            }
        }
        return null;
    }
    private boolean isExit(User newUser) throws Exception{
        for(User user:userList){
            if(user.getUserName().equals(newUser.getUserName())&&user.getPassword().equals(newUser.getPassword())){
                return true;
            }
        }
        return false;
    }
    private void update(User updateUser){
        for(User user:userList){
            if(user.getUserName().equals(updateUser.getUserName())&&user.getPassword().equals(updateUser.getPassword())){
                user.setId(updateUser.getId());
                user.setBanji(updateUser.getBanji());
                user.setChineseName(updateUser.getChineseName());
                user.setCreateDate(updateUser.getCreateDate());
                user.setEmail(updateUser.getEmail());
                user.setIp(updateUser.getIp());
                user.setNickName(updateUser.getNickName());
                user.setPassword(updateUser.getPassword());
                user.setRspMsg(updateUser.getRspMsg());
                user.setStudentNo(updateUser.getStudentNo());
                user.setUserName(updateUser.getUserName());
            }
        }
    }
    
}
