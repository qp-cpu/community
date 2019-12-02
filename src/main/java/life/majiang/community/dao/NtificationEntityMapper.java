package life.majiang.community.dao;

import java.util.List;
import life.majiang.community.entity.NtificationEntity;
import life.majiang.community.entity.NtificationEntityExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NtificationEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int countByExample(NtificationEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int deleteByExample(NtificationEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int insert(NtificationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int insertSelective(NtificationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    List<NtificationEntity> selectByExample(NtificationEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    NtificationEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") NtificationEntity record, @Param("example") NtificationEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") NtificationEntity record, @Param("example") NtificationEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(NtificationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ntification
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(NtificationEntity record);


    List<NtificationEntity> selectAll(@Param("id") Long id,@Param("ofsize") Integer ofsize, @Param("size") Integer size);
}