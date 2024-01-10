package com.petadopt.mapper;

import com.petadopt.entity.Adopt;
import com.petadopt.entity.Adoption;
import com.petadopt.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    List<User> select(String username);

    @Insert("INSERT INTO user (name, username, password, email) VALUES (#{name}, #{username}, #{password}, #{email})")
    int insert(User user);

    @Select("SELECT COUNT(*) FROM user")
    int countUsers();

    @Select("SELECT * FROM user LIMIT #{pageSize} OFFSET #{offset}")
    List<User> selectUser(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM adopt")
    int countAdopt();

    @Select("SELECT * FROM adopt LIMIT #{pageSize} OFFSET #{offset}")
    List<Adopt> selectAdopt(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Delete("DELETE FROM adopt WHERE name = #{name}")
    int deleteAdopt(String name);

    @Select("SELECT COUNT(*) FROM adoption")
    int countAdoption();

    @Select("SELECT user.name AS user_name, user.username AS user_username, pet.name AS pet_name, pet.species AS pet_type " +
            "FROM adoption " +
            "JOIN user ON adoption.user_id = user.id " +
            "JOIN pet ON adoption.pet_id = pet.id " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<Adoption> selectAdoption(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%', #{name}, '%') OR username LIKE CONCAT('%', #{username}, '%') OR email = #{email} LIMIT #{offset}, #{pageSize}")
    List<User> queryWithPagination(@Param("name") String name,
                                   @Param("username") String username,
                                   @Param("email") String email,
                                   @Param("offset") int offset,
                                   @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM user WHERE name LIKE CONCAT('%', #{name}, '%') OR username LIKE CONCAT('%', #{username}, '%') OR email = #{email}")
    int countUserByQuery(@Param("name") String name,
                         @Param("username") String username,
                         @Param("email") String email);

    @Update("UPDATE user SET name=#{name}, username=#{username}, password=#{password}, email=#{email} WHERE name=#{name}")
    int update(User user);

    @Delete("DELETE FROM user WHERE name = #{name}")
    int delete(String name);

    @Delete({
            "<script>",
            "DELETE FROM user WHERE username IN",
            "<foreach item='username' collection='usernames' open='(' separator=',' close=')'>",
            "#{username}",
            "</foreach>",
            "</script>"
    })
    int deleteUsers(@Param("usernames") List<String> usernames);

}
