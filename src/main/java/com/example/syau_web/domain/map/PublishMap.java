package com.example.syau_web.domain.map;

import com.example.syau_web.domain.Publish;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/25 0025 19:51
 * <p>
 * 利用mybatis操作publish数据
 */
@Component
@Mapper//启动类能够扫描到此map
public interface PublishMap {

    /**
     * 插入数据
     * 注意：publish类中的数据要和参数的名字相对应。
     *
     * @param publish 公告类
     * @return
     */
    @Insert("insert into publish (user_number,publish_content,file_name) values (#{userNumber,jdbcType=INTEGER},#{publishContent,jdbcType=VARCHAR},#{fileName,jdbcType=VARCHAR})")
    int addPublish(Publish publish);

    /**
     * 查询数据
     *
     * @return publish的列表
     */
    @Select("select * from publish")
    List<Publish> selectAllPublish();

    /**
     * 删除某一条公告
     * 注意sql语句， 直接是delete from 表名，而没有*号
     *
     * @param publishId
     * @return
     */
    @Delete("delete from publish where publish_id=#{publishId}")
    public int deletePublishById(Integer publishId);

    /**
     * 根据学生的学号查询出反馈的列表
     *
     * @param user_number
     * @return
     */


//        @Select("select * from publish where user_number in{#{user_number}}")
    /*@Select("<script>"
            + "select * from publish where user_number in"
            + "<foreach item='item' index='index' collection='user_number' open='('separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")*/

    /**
     * 注解方式传入list，
     * 真的是太麻烦了
     * @param user_number
     * @return
     */
    @Select("<script>"
            + "SELECT * FROM publish WHERE user_number IN "
            + "<foreach item='item' index='index' collection='user_number' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    public List<Publish> findAllByNumber(@Param("user_number") List<Integer> user_number);


}
