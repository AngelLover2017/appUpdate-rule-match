package com.example.demo.entity;

import lombok.Data;

@Data
public class Rule {
    private Integer id;
    private String download_url;
    private String update_version_code;
    private String md5;
    private String max_update_version_code;
    private String min_update_version_code;
    private Integer max_os_api;
    private Integer min_os_api;
    private String title;
    private String update_tips;
    private Integer download_times;
}