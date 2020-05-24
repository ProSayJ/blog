package cn.prosayj.blog.manage.book.service.impl;

import cn.prosayj.blog.core.common.constants.enums.ModuleEnum;
import cn.prosayj.blog.core.dao.PageUtils;
import cn.prosayj.blog.core.dao.Query;
import cn.prosayj.blog.core.dao.domain.book.BookNote;
import cn.prosayj.blog.core.dao.domain.book.dto.BookNoteDTO;
import cn.prosayj.blog.core.dao.domain.book.vo.BookNoteVO;
import cn.prosayj.blog.core.dao.domain.operation.Category;
import cn.prosayj.blog.core.dao.mapper.book.BookNoteMapper;
import cn.prosayj.blog.manage.book.service.BookNoteService;
import cn.prosayj.blog.manage.book.service.BookService;
import cn.prosayj.blog.manage.operation.service.CategoryService;
import cn.prosayj.blog.manage.operation.service.TagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * BookNoteServiceImpl
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
@Service
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    /**
     * 分页查询笔记列表
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookNoteVO> page = new Query<BookNoteVO>(params).getPage();
        List<BookNoteVO> bookNoteList = baseMapper.listBookNoteVo(page, params);
        // 查询所有分类
        List<Category> categoryList = categoryService.list(new QueryWrapper<Category>().lambda().eq(Category::getType,ModuleEnum.BOOK.getValue()));
        // 封装BookNoteVo
        Optional.ofNullable(bookNoteList).ifPresent((bookNoteVos ->
                bookNoteVos.forEach(bookNoteVo -> {
                // 设置所属书本
                bookNoteVo.setBook(bookService.getById(bookNoteVo.getBookId()));
                // 设置类别
                bookNoteVo.setCategoryListStr(categoryService.renderCategoryArr(bookNoteVo.getCategoryId(),categoryList));
                // 设置标签列表
                bookNoteVo.setTagList(tagService.listByLinkId(bookNoteVo.getId(),ModuleEnum.BOOK_NOTE.getValue()));
            })));
        page.setRecords(bookNoteList);
        return new PageUtils(page);
    }



    /**
     * 保存笔记笔记
     *
     * @param bookNote
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBookNote(BookNoteDTO bookNote) {
        baseMapper.insert(bookNote);
        tagService.saveTagAndNew(bookNote.getTagList(),bookNote.getId(),ModuleEnum.BOOK_NOTE.getValue());
    }

    /**
     * 更新笔记
     *
     * @param bookNote
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBookNote(BookNoteDTO bookNote) {
        // 删除多对多所属标签
        tagService.deleteTagLink(bookNote.getId(),ModuleEnum.BOOK_NOTE.getValue());
        // 更新所属标签
        tagService.saveTagAndNew(bookNote.getTagList(),bookNote.getId(), ModuleEnum.BOOK_NOTE.getValue());
        // 更新笔记
        baseMapper.updateById(bookNote);
    }

    /**
     * 获取笔记对象
     *
     * @param bookNoteId
     * @return
     */
    @Override
    public BookNoteDTO getBookNote(Integer bookNoteId) {
        BookNoteDTO bookNoteDto = new BookNoteDTO();
        BookNote bookNote = this.baseMapper.selectById(bookNoteId);
        BeanUtils.copyProperties(bookNote,bookNoteDto);
        // 查询所属标签
        bookNoteDto.setTagList(tagService.listByLinkId(bookNoteId,ModuleEnum.BOOK_NOTE.getValue()));
        return bookNoteDto;
    }

    /**
     * 判断该类别下是否有笔记
     *
     * @param categoryId
     * @return
     */
    @Override
    public boolean checkByCategory(Integer categoryId) {
        return baseMapper.checkByCategory(categoryId) > 0;
    }

    /**
     * 批量删除
     *
     * @param bookNoteIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Integer[] bookNoteIds) {
        //先删除笔记标签多对多关联
        Arrays.stream(bookNoteIds).forEach(bookNoteId -> {
            tagService.deleteTagLink(bookNoteId,ModuleEnum.BOOK_NOTE.getValue());
        });
        this.baseMapper.deleteBatchIds(Arrays.asList(bookNoteIds));
    }


}
