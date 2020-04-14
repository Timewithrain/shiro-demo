package com.watermelon.mapper;

import com.watermelon.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> listUser();

    User getUserById();

    int addUser();

    int updateUser();

    int deleteUser();

}
