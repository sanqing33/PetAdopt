package com.petadopt.mapper;

import com.petadopt.entity.Adopt;
import com.petadopt.entity.Adoption;
import com.petadopt.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 统计用户数量
    @Select("SELECT COUNT(*) FROM user")
    int countUsers();

    // 分页查询用户信息
    @Select("SELECT * FROM user LIMIT #{pageSize} OFFSET #{offset}")
    List<User> selectUser(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 统计领养信息数量
    @Select("SELECT COUNT(*) FROM adopt")
    int countAdopt();

    // 分页查询领养信息
    @Select("SELECT * FROM adopt LIMIT #{pageSize} OFFSET #{offset}")
    List<Adopt> selectAdopt(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 删除指定名称的领养信息
    @Delete("DELETE FROM adopt WHERE name = #{name}")
    int deleteAdopt(String name);

    // 统计领养记录数量
    @Select("SELECT COUNT(*) FROM adoption")
    int countAdoption();

    // 分页查询领养记录信息
    @Select("SELECT user.name AS user_name, user.username AS user_username, pet.name AS pet_name, pet.species AS pet_type " +
            "FROM adoption " +
            "JOIN user ON adoption.user_id = user.id " +
            "JOIN pet ON adoption.pet_id = pet.id " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<Adoption> selectAdoption(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 根据条件分页查询用户信息
    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%', #{name}, '%') OR username LIKE CONCAT('%', #{username}, '%') OR email = #{email} LIMIT #{offset}, #{pageSize}")
    List<User> queryWithPagination(@Param("name") String name,
                                   @Param("username") String username,
                                   @Param("email") String email,
                                   @Param("offset") int offset,
                                   @Param("pageSize") int pageSize);

    // 根据条件统计用户数量
    @Select("SELECT COUNT(*) FROM user WHERE name LIKE CONCAT('%', #{name}, '%') OR username LIKE CONCAT('%', #{username}, '%') OR email = #{email}")
    int countUserByQuery(@Param("name") String name,
                         @Param("username") String username,
                         @Param("email") String email);

    // 编辑用户信息
    @Update("UPDATE user SET name=#{name}, username=#{username}, password=#{password}, email=#{email} WHERE name=#{name}")
    int update(User user);

    // 删除指定名称的用户
    @Delete("DELETE FROM user WHERE name = #{name}")
    int delete(String name);

    // 批量删除用户信息
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
