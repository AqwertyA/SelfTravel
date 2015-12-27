package org.selftravel.http;

/**
 * 存放接口的类
 * 使用时可用{@link String#format(String, Object...)}替换url中的%s,%d等内容
 */
public class HttpUrl {
    private HttpUrl() {
    }

    public static final String BASE_URL = "http://www.ziyo.me/interface.php/";
    public static final String PIC_BASE_URL = "http://www.ziyo.me";
    //(拼接内容的img地址)
    /**
     * 首页：
     */

    /**
     * 头:
     * <p/>
     * param limit
     * <p/>
     * param type
     */
    public static final String HOME_HEADER = "http://www.ziyo.me/interface.php/Specialevents/index?limit=10&type=1";
    //longitude：
    /**
     * 内容：
     * <p/>
     * param longitude 经度
     * <p/>
     * param latitude 纬度
     * <p/>
     * param start
     * <p/>
     * param limit
     */
    public static final String HOME_DETAIL = "http://www.ziyo.me/interface.php/Destination/getAround?longitude=104.05436706542969&latitude=30.54062843322754&start=0&limit=9";

    /**
     * 目的地：
     */
    //热门城市、热门景区：

    public static final String HOT_ATTRACTIONS ="http://www.ziyo.me/interface.php/Destination/index?type=5&limit=9&pid=0";
    public static final String HOT_CITY = "http://www.ziyo.me/interface.php/Destination/index?type=3&limit=2&pid=0";
    public static final String HOT_SPOT = "http://www.ziyo.me/interface.php/Destination/index?type=4&limit=4&pid=0";
    /**
     * 本月推荐：
     * <p/>
     * param type
     * <p/>
     * param limit
     */

    public static final String DESTINATION_RECOMMEND = "http://www.ziyo.me/interface.php/Destination/getRecommendScenic?type=4&limit=2";

    /**
     * 发现：
     * <p/>
     * param limit
     * <p/>
     */
    public static final String FIND = "http://www.ziyo.me/interface.php/Specialevents/index?limit=%d&type=%d";

    /**
     * 内容界面，相关图片URL
     */
    public static final String CONTENT_PIC_START = "http://www.ziyo.me/interface.php/Photo/getPhotoBySid?id=";
    public static final String CONTENT_PIC_END = "&type=1&start=0&limit=4&imgwidth=360&imgheight=360";
    /**
     * 更多图片URL，需要拼接一个ID
     * start:用于上拉加载更多
     */
    public static final String MORE_PIC_START = "http://www.ziyo.me/interface.php/Photo/getPhotoBySid?id=";
    public static final String MORE_PIC_MID = "&type=0&start=";
    public static final String MORE_PIC_END = "&limit=16&imgwidth=360&imgheight=360";

    public static final String MORE_PIC_DETAIL_MID = "&type=0&start=0&limit=";
    public static final String MORE_PIC_DETAIL_END = "&imgwidth=360&imgheight=360";

    /**     * param type
     * 内容界面，风景评论
     */
    public static final String CONTENT_MESSAGE_START = "http://www.ziyo.me/interface.php/Message/index?id=";
    public static final String CONTENT_MESSAGE_MID = "&type=2&start=";
    public static final String CONTENT_MESSAGE_END = "&type=2&limit=5";
    public static final String CONTENT_MESSAGE_END_MORE = "&type=2&limit=10";
    /**
     * 内容界面，头部多图加载
     */
    public static final String CONTENT_HEAD_PIC = "http://www.ziyo.me/interface.php/Destination/getScenicInfo?id=";
    /**
     * 内容，详情按钮
     */
    public static final String CONTENT_INFO = "/interface.php/Destination/getInfoPage?id=";
    /**
     * 景点URL，需要拼接
     */
    public static final String CONTENT_PLACE_START = "http://www.ziyo.me/interface.php/Destination/getAttractionsBySid?id=";
    public static final String CONTENT_PLACE_END = "&imgwidth2=400&imgheight2=280&imgwidth3=1120&imgheight3=480&mark=0";
    /**
     * 搜索-周边
     */
    public static String SEARCH_AROUND = "http://www.ziyo.me/interface.php/Destination/getAround?longitude=104.05436706542969&latitude=30.54062843322754&start=0&limit=9";
    /**
     * 搜索-热门搜索
     */
    public static String SEARCH_HOT = "http://www.ziyo.me/interface.php/Destination/index?type=5&limit=9&pid=0";
    /**
     * 登录
     */
//{"password":"186e1599830fb9799e7c6e3279c32350","account":"18588419414"},post请求
    public static String LOGIN = "http://www.ziyo.me/interface.php/User/login";
    public static String GET_LOGIN_INFO = "http://www.ziyo.me/interface.php/User/getUserinfo?id=0";
    /**
     * 退出登录
     */
    public static String LOG_OUT = "http://www.ziyo.me/interface.php/User/logout";
    /**
     * 关注
     */
    public static String MY_CARE = "http://www.ziyo.me/interface.php/User/myCare";
    /**
     * 粉丝
     */
    public static String CARE_ME = "http://www.ziyo.me/interface.php/User/careMe";
    /**
     * 相册
     */
//index
    public static String PHOTO_INDEX = "http://www.ziyo.me/interface.php/Photo/index?uid=688&imgwidth=140&imgheight=140";
    //detail
    public static String PHOTO_DETAIL = "http://www.ziyo.me/interface.php/Photo/getPhotoInfoById?id=419&imgwidth=140&imgheight=140";
    //上传
    public static String PHOTO_UPLOAD = "http://www.ziyo.me/interface.php/Photo/uploadimg";
/**消息*/

    /**
     * 喜欢
     */
    public static String MY_LIKE = "http://www.ziyo.me/interface.php/Destination/getMyMark?uid=0&type=2&start=0&limit=2147483647&gotype=4";
    /**
     * 足迹
     */
    //足迹概要
    public static String FOOTPRINT = "http://www.ziyo.me/interface.php/User/getUserBeenInfo?uid=0";
    //去过的景点
    public static String FOOTPRINT_SCENE = "http://www.ziyo.me/interface.php/Destination/getMyMark?uid=0&type=1&start=0&limit=2147483647&gotype=4";
}
