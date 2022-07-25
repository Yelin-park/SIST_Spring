package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

// p94        NoticeDao.java 인터페이스
public interface TimeMapper {
   
   @Select("SELECT sysdate FROM dual")
   public String getTime(); // Mapper.xml 파일 필요 없음
   
   public String getTimeXML(); // TimeMapper.xml 연동

}