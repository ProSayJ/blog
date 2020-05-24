package cn.prosayj.blog.manage.oss.controller;


import cn.prosayj.blog.core.common.Result;
import cn.prosayj.blog.core.common.constants.enums.FileEnum;
import cn.prosayj.blog.core.common.exception.BussinessException;
import cn.prosayj.blog.core.dao.domain.oss.OssResource;
import cn.prosayj.blog.manage.file.service.FileService;
import cn.prosayj.blog.manage.oss.service.CloudStorageService;
import cn.prosayj.blog.manage.oss.service.OssResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 云存储资源表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/oss/resource")
public class OssResourceController {
    @Autowired
    private OssResourceService ossResourceService;
    @Autowired
    private CloudStorageService cloudStorageService;
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result uploadCover(MultipartFile file) throws Exception {
        if (file != null && file.isEmpty()) {
            throw new BussinessException("上传文件不能为空");
        }
        //上传文件
//        String suffix = fileMultipart.getOriginalFilename().substring(fileMultipart.getOriginalFilename().lastIndexOf("."));
//        String url = cloudStorageService.uploadSuffix(fileMultipart.getBytes(), suffix);
//        OssResource ossResource = new OssResource(url, fileMultipart.getOriginalFilename());
//        ossResourceService.save(ossResource);

        String imgDbUrl = fileService.uploadFile(file, 6L, Boolean.FALSE, FileEnum.IMG);
        OssResource ossResource = new OssResource("http://localhost:9999/blog-server" + imgDbUrl, "文件名称.png");

        return Result.ok().put("resource", ossResource);
    }
}
