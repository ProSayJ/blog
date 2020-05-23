package cn.prosayj.blog.core.common.constants.constant;

/**
 * RedisCacheNames
 *
 * @author prosayj@gmail.com
 * @date 2020-05-21 下午 01:48
 * @since 1.0.0
 */
public class RedisCacheNames {

    public final static String PROFIX = "DBBLOG:";

    /**
     * 文章缓存空间名称
     */
    public final static String ARTICLE = PROFIX + "ARTICLE";
    /**
     * 图书缓存空间名称
     */
    public final static String BOOK = PROFIX + "BOOK";

    /**
     * 笔记缓存空间名称
     */
    public final static String BOOKNOTE = PROFIX + "BOOKNOTE";

    /**
     * 友情链接列表
     */
    public final static String LINK = PROFIX + "LINK";

    /**
     * 推荐列表
     */
    public final static String RECOMMEND = PROFIX + "RECOMMEND";

    /**
     * 标签列表
     */
    public final static String TAG = PROFIX + "TAG";

    /**
     * 分类列表
     */
    public final static String CATEGORY = PROFIX +"CATEGORY";

    /**
     * 时光轴
     */
    public static final String TIMELINE =  PROFIX +"TIMELINE";

}
