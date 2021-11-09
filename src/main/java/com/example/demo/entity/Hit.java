package com.example.demo.entity;

import lombok.Data;

@Data
public class Hit {
    private String download_url;
    private String update_version_code;
    private String md5;
    private String title;
    private String update_tips;
}
