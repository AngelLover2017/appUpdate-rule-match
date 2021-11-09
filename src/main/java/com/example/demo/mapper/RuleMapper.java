package com.example.demo.mapper;

import com.example.demo.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Mapper
public interface RuleMapper {
    @Select("select * from rule where aid = #{aid} and platform = #{device_platform} and cpu_arch= #{cpu_arch} and channel=#{channel}")
    List<Rule> findCertainRules(Integer aid, String device_platform, String cpu_arch,String channel);
}
