package cn.unowen.mapper;

import cn.unowen.pojo.BookLog;

public interface BookLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookLog record);

    int insertSelective(BookLog record);

    BookLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookLog record);

    int updateByPrimaryKey(BookLog record);
}