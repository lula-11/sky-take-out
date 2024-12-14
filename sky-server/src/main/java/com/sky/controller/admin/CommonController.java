package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


//common接口
@RestController
@RequestMapping("/admin/common")
@Api(tags = "common接口")
@Slf4j
public class CommonController {
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传：{}", file);
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //新文件名称
            String objectName = UUID.randomUUID().toString() + extension;
            String filepath = "/Users/lula/Downloads/Study/JavaStudy/" + objectName;
            file.transferTo(new File(filepath));

            return Result.success(filepath);
        }
        catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }
        return  Result.error(MessageConstant.UPLOAD_FAILED);
    }
}


