package com.example.demo.service;

import com.example.demo.entity.Hit;
import com.example.demo.entity.Rule;
import com.example.demo.mapper.RuleMapper;
import com.example.demo.util.RuleComparator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RuleService {
    @Resource
    private RuleMapper ruleMapper;
    @Resource
    private RedisTemplate<String, HashSet<String>> redisTemplate;
    public Hit configureRule( String version,  String device_platform,
                               String device_id,  Integer os_api,
                               String channel,  String version_code,
                               String update_version_code,  Integer aid,
                               Integer cpu_arch){
        List<Rule> rules= ruleMapper.findCertainRules(aid,device_platform,cpu_arch.toString(),channel);
        ValueOperations<String,HashSet<String>>  operations = redisTemplate.opsForValue();
        Hit hit=new Hit();
        RuleComparator ruleComparator=new RuleComparator();
        rules.sort(ruleComparator);
        for (Rule r : rules) {
            boolean isInCache = redisTemplate.hasKey(r.getId().toString());
            if(!isInCache){
                String[] whiteList=r.getDevice_id_list().split(" ");
                HashSet<String> set = new HashSet<>(Arrays.asList(whiteList));
                operations.set(r.getId().toString(),set);
            }
            boolean hasKey = Objects.requireNonNull(operations.get(r.getId().toString())).contains(device_id);
            if (hasKey) {
                hit.setDownload_url(r.getDownload_url());
                hit.setMd5(r.getMd5());
                hit.setUpdate_version_code(r.getUpdate_version_code());
                hit.setTitle(r.getTitle());
                hit.setUpdate_tips(r.getUpdate_tips());
                return hit;
            }

            if(ruleComparator.compareId(update_version_code,r.getMin_update_version_code())==-1 || ruleComparator.compareId(update_version_code,r.getMax_update_version_code())==1)
                continue;
            if(device_platform.equals("Android")&&(os_api<r.getMin_os_api()||os_api>r.getMax_os_api()))
                continue;
            hit.setDownload_url(r.getDownload_url());
            hit.setMd5(r.getMd5());
            hit.setUpdate_version_code(r.getUpdate_version_code());
            hit.setTitle(r.getTitle());
            hit.setUpdate_tips(r.getUpdate_tips());
            return hit;
        }
        return hit;
    }
}
