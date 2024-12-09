package Auction.AMS.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import Auction.AMS.security.entity.Role;
import Auction.AMS.security.entity.Users;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
        @Result(property = "profileImage", column = "profile_image")
})
    Users findById(@Param("id") Long id);

    @Insert("INSERT INTO users (username, password, email, enabled) VALUES (#{username}, #{password}, #{email}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveUser(Users user);
    
    @Update("update users set password=#{newPassword} where id=#{userId} ")
    public int changePassword(@Param("userId") Long userId, String newPassword);

    @Insert("INSERT INTO user_role (user_id, role_id) VALUES (#{userId}, #{roleId})")
    void saveUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Select("SELECT * FROM role")
    public List<Role>allRoles();

    @Select("SELECT username FROM users")
    public List<String>userExists();
    

    @Update("UPDATE users SET profile_image = #{profileImage} WHERE id = #{userId}")
    @Results({
        @Result(property = "profileImage", column = "profile_image")
})
    public void updateProfilePic(@Param("profileImage") String profileImage, @Param("userId") Long userId);


}   

