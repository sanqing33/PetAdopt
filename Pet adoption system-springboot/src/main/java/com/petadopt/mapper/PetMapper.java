package com.petadopt.mapper;

import com.petadopt.entity.Adopt;
import com.petadopt.entity.Pet;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper {

    // 查询所有宠物信息
    @Select("SELECT * FROM pet")
    List<Pet> select();

    // 统计宠物的数量
    @Select("SELECT COUNT(*) FROM pet")
    int countPets();

    // 分页查询宠物信息
    @Select("SELECT * FROM pet LIMIT #{pageSize} OFFSET #{offset}")
    List<Pet> selectWithPagination(@Param("offset") int offset, @Param("pageSize") int pageSize);

    // 根据条件查询宠物信息
    @Select("SELECT * FROM pet WHERE name LIKE CONCAT('%', #{name}, '%') OR species LIKE CONCAT('%', #{species}, '%') OR available = #{available} LIMIT #{offset}, #{pageSize}")
    List<Pet> queryWithPagination(@Param("name") String name,
                                  @Param("species") String species,
                                  @Param("available") String available,
                                  @Param("offset") int offset,
                                  @Param("pageSize") int pageSize);

    // 根据条件统计宠物的数量
    @Select("SELECT COUNT(*) FROM pet WHERE name LIKE CONCAT('%', #{name}, '%') OR species LIKE CONCAT('%', #{species}, '%') OR available = #{available}")
    int countPetsByQuery(@Param("name") String name,
                         @Param("species") String species,
                         @Param("available") String available);

    // 添加宠物领养申请
    @Insert("INSERT INTO adopt (name, age, address, phone, petType, reason) VALUES (#{name}, #{age}, #{address}, #{phone}, #{petType}, #{reason})")
    int insertAdopt(Adopt adopt);

    // 添加宠物信息
    @Insert("INSERT INTO pet (name, species, age, description, available) VALUES (#{name}, #{species}, #{age}, #{description}, #{available})")
    int insertPet(Pet pet);

    // 编辑宠物信息
    @Update("UPDATE pet SET name=#{name}, species=#{species}, age=#{age}, description=#{description}, available=#{available} WHERE name=#{name}")
    int update(Pet pet);

    // 删除指定名称的宠物
    @Delete("DELETE FROM pet WHERE name = #{name}")
    int delete(String name);

    // 批量删除宠物信息
    @Delete({
            "<script>",
            "DELETE FROM pet WHERE name IN",
            "<foreach item='name' collection='names' open='(' separator=',' close=')'>",
            "#{name}",
            "</foreach>",
            "</script>"
    })
    int deletePets(@Param("names") List<String> names);

}
