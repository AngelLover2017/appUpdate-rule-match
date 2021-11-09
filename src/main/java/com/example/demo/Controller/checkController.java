package com.example.demo.Controller;

import com.example.demo.entity.Hit;
import com.example.demo.entity.Rule;
import com.example.demo.service.RuleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/check_update")
public class checkController {
    @Resource
    private RuleService ruleService;
    @GetMapping
    Hit update(@RequestParam String version, @RequestParam String device_platform,
               @RequestParam String device_id, @RequestParam Integer os_api,
               @RequestParam String channel, @RequestParam String version_code,
               @RequestParam String update_version_code, @RequestParam Integer aid,
               @RequestParam Integer cpu_arch){
        return ruleService.configureRule(version,device_platform,device_id,os_api,channel,version_code,update_version_code,aid,cpu_arch);
    }
}
