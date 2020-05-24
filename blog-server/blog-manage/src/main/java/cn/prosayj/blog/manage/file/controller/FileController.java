package cn.prosayj.blog.manage.file.controller;

import cn.prosayj.blog.core.common.constants.enums.FileEnum;
import cn.prosayj.blog.core.common.vo.IdVO;
import cn.prosayj.blog.core.util.FileUtils;
import cn.prosayj.blog.manage.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 文件上传控制器
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-10 下午 09:21
 * @since 1.0.0
 */
@Api(value = "file-controller", tags = "file-controller", description = "文件上传控制器")
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "图片上传")
    @PostMapping("/img-upload")
    @ResponseBody
    public Map imgUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile fileMultipart, HttpServletRequest request) throws Exception {
        Long editorId = Long.parseLong(request.getParameter("editorId"));
        String imgDbUrl = fileService.uploadFile(fileMultipart, editorId, Boolean.FALSE, FileEnum.IMG);

        Map<String, Object> res = new HashMap<>();
//        res.put("url", "http://localhost/static/images/upload/" + fileName);//静态资源路径
//        res.put("url", "http://localhost/file/img-download?id=" + fileId);//db路径
        res.put("url", "http://localhost" + imgDbUrl);//db路径
        res.put("success", 1);
        res.put("message", "upload success!");
        return res;
    }


    @ApiOperation(value = "图片下载")
    @RequestMapping(value = "/img-download", method = RequestMethod.GET)
    public void downloadImage(IdVO idVO, HttpServletResponse response) {
        try {
            fileService.downloadImage(idVO.getId(), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation(value = "导出所有图片到本地")
    @PostMapping(value = "/img-expport-all")
    @ResponseBody
    public boolean exportAllImgs() {
        fileService.exoprtAllImgs();
        return true;
    }

    @ApiOperation(value = "修复所有文件的静态资源路径位置")
    @PostMapping(value = "/img-staticsrc-repair")
    @ResponseBody
    public boolean staticSrcRepair() {
        return true;
    }
}