package cn.prosayj.blog.manage.file.service.impl;

import cn.prosayj.blog.core.common.constants.constant.SysConstants;
import cn.prosayj.blog.core.common.constants.enums.FileEnum;
import cn.prosayj.blog.core.dao.domain.article.vo.ArticleVO;
import cn.prosayj.blog.core.dao.domain.fileresource.FileResourceDomain;
import cn.prosayj.blog.core.dao.mapper.article.ArticleMapper;
import cn.prosayj.blog.core.dao.mapper.fileresource.FileResourceDomainMapper;
import cn.prosayj.blog.core.util.BeanUtil;
import cn.prosayj.blog.core.util.FileUtils;
import cn.prosayj.blog.manage.file.dto.FileResourceDTO;
import cn.prosayj.blog.manage.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * FileServiceImpl
 *
 * @author yangjian@bubi.cn
 * @date 2020-05-24 下午 04:43
 * @since 1.0.0
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileResourceDomainMapper fileResourceDomainMapper;
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public String uploadFile(MultipartFile multipartFile, Long articleId, Boolean needUpLoad2ClassPath, FileEnum fileType) throws IOException {
        ArticleVO articleVO = articleMapper.getSimpleArticleVo(articleId.intValue());
        String fullName = multipartFile.getOriginalFilename();
        String suffix = fullName.substring(fullName.lastIndexOf(SysConstants.POINT));
        String trueFileName = fullName.substring(fullName.lastIndexOf(SysConstants.SEPARATOR) + 1, fullName.lastIndexOf(SysConstants.POINT));
        String fileName = trueFileName + "_" + System.currentTimeMillis() + "_" + suffix;
        if (needUpLoad2ClassPath) {
            FileUtils.upload2ClassPath(multipartFile, fileName);
        }
        FileResourceDomain fileResourceDomain = BeanUtil.initBeanProperties(new FileResourceDomain());
        fileResourceDomain.setUserId(-1L);
        fileResourceDomain.setArticleId(articleId);
        fileResourceDomain.setFileName(fileName);
        fileResourceDomain.setFileSuffix(suffix);
        fileResourceDomain.setFileDbUrl(SysConstants.ENPTY_STRING);
        fileResourceDomain.setFileStaticUrl("/static/images/upload/" + articleVO.getTitle());
        fileResourceDomain.setFileSource(FileUtils.inputStream2ByteArray(multipartFile.getInputStream()));
        fileResourceDomainMapper.insertSelective(fileResourceDomain);
        fileResourceDomain.setFileDbUrl("/file/img-download?id=" + fileResourceDomain.getId());
        fileResourceDomainMapper.updateByPrimaryKey(fileResourceDomain);
        return fileResourceDomain.getFileDbUrl();
    }

    @Override
    public void exoprtAllImgs() {

    }

    @Override
    public List<FileResourceDTO> getAllImgsDetails() {
        return null;
    }

    @Override
    public void downloadImage(Long id, HttpServletResponse response) throws IOException {
        FileResourceDomain fileResourceDomain = fileResourceDomainMapper.selectByPrimaryKey(id);
        byte[] imgSource = fileResourceDomain.getFileSource();
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        //// 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileResourceDomain.getFileName());
        OutputStream os = response.getOutputStream();
        os.write(imgSource);
    }
}
