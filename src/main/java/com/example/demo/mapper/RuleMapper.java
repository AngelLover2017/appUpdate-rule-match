package com.example.demo.mapper;

import com.example.demo.entity.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Mapper
public interface RuleMapper {
    @Select("select id,download_url,update_version_code,update_version_code,md5,"
            +"max_update_version_code,min_update_version_code,max_os_api,min_os_api,title,update_tips,download_times "
            +"from rule "
            +"where aid = #{aid} and platform = #{device_platform} and cpu_arch= #{cpu_arch} and channel=#{channel} and status=0")
    List<Rule> findCertainRules(Integer aid, String device_platform, String cpu_arch,String channel);
    @Update("update rule set download_times=#{download_times} where id=#{id}")
    void updateDownloadTimes(Integer id , Integer download_times);
}
