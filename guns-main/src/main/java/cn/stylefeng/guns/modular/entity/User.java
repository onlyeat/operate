package cn.stylefeng.guns.modular.entity;

import cn.stylefeng.guns.base.enums.UserSexEnum;

import java.util.Objects;

/**
 * Created by LiuQianYi on 2018-12-19 23:06
 */
public class User implements Comparable<User> {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private String nickName;
    private Long score;



    public User() {
        super();
    }

    public User(String userName, String passWord, UserSexEnum userSex, Long score) {
        super();
        this.passWord = passWord;
        this.userName = userName;
        this.userSex = userSex;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(passWord, user.passWord) &&
                userSex == user.userSex &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(score, user.score);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, passWord, userSex, nickName, score);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userSex=" + userSex +
                ", nickName='" + nickName + '\'' +
                ", score=" + score +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public int compareTo(User user) {
        return this.getScore().compareTo(user.getScore());
    }
}
