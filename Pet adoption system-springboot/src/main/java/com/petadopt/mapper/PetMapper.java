package com.petadopt.mapper;

import com.petadopt.entity.Adopt;
import com.petadopt.entity.Pet;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PetMapper {

    @Select("SELECT * FROM pet")
    List<Pet> select();

    @Select("SELECT COUNT(*) FROM pet")
    int countPets();

    @Select("SELECT * FROM pet LIMIT #{pageSize} OFFSET #{offset}")
    List<Pet> selectWithPagination(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT * FROM pet WHERE name LIKE CONCAT('%', #{name}, '%') OR species LIKE CONCAT('%', #{species}, '%') OR available = #{available} LIMIT #{offset}, #{pageSize}")
    List<Pet> queryWithPagination(@Param("name") String name,
                                  @Param("species") String species,
                                  @Param("available") String available,
                                  @Param("offset") int offset,
                                  @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM pet WHERE name LIKE CONCAT('%', #{name}, '%') OR species LIKE CONCAT('%', #{species}, '%') OR available = #{available}")
    int countPetsByQuery(@Param("name") String name,
                         @Param("species") String species,
                         @Param("available") String available);

    @Insert("INSERT INTO adopt (name, age, address, phone, petType, reason) VALUES (#{name}, #{age}, #{address}, #{phone}, #{petType}, #{reason})")
    int insertAdopt(Adopt adopt);

    @Insert("INSERT INTO pet (name, species, age, description, available) VALUES (#{name}, #{species}, #{age}, #{description}, #{available})")
    int insertPet(Pet pet);

    @Update("UPDATE pet SET name=#{name}, species=#{species}, age=#{age}, description=#{description}, available=#{available} WHERE name=#{name}")
    int update(Pet pet);

    @Delete("DELETE FROM pet WHERE name = #{name}")
    int delete(String name);

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
