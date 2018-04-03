package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    String INSERT_FIELD = " user_id, content, created_data, entity_id, entity_type, status ";
    String SELECT_FIELD = " id, " + INSERT_FIELD;

    @Insert({"insert into " , TABLE_NAME , " (",INSERT_FIELD,") values (#{userId},#{content},#{createdDate}," +
            "#{entityId},#{entityType},#{status} )"})
    int addComment(Comment comment);

    @Select({"select ",SELECT_FIELD, " from ",TABLE_NAME, " where id = #{id}"})
    Comment getCommentById(int id);

    @Select({"select ",SELECT_FIELD," from ",TABLE_NAME," where entity_id = #{entityId} " +
            " and entity_type = #{entityType} order by created_data desc"})
    List<Comment> getCommentsByEntity(@Param("entityId") int entityId,@Param("entityType") int entityType);

    @Select({"select count(1) from ",TABLE_NAME,"where entity_id = #{entityId} and entity_type = #{entityType}"})
    int getCommentCount(@Param("entityId") int entityId,@Param("entityType") int entityType);

    @Update({"update ",TABLE_NAME," set status = #{status} where id = #{id}"})
    int updateStatus(@Param("id")int id,@Param("status") int status);
}
