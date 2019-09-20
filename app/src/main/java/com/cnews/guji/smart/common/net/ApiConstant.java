package com.cnews.guji.smart.common.net;

import com.cnews.guji.smart.api.HostType;

/**
 * @package: ApiConstant
 * @author： JSYL-DCL
 * @describe： 全局配置
 * @email： 11442865
 */
public class ApiConstant {
    public static String NORMAL_HOST = "https://suggest.taobao.com/";
    public static String NORMAL_163_HOST = "http://c.m.163.com/";
    public static String IFENG_HOST = "https://api.iclient.ifeng.com/";
    public static String IFENG_DETAIL_HOST = "http://api.3g.ifeng.com/";
    public static String IFENG_HOME_COMMENT_HOST = "https://comment.ifeng.com/";
    public static String ITOUTIAO_HOST = "https://api.iclient.ifeng.com/";
    public static String ISOHU_HOST = "http://p.cdn.sohu.com/";
    public static String IEPET_HOST = "https://mallcdn.api.epet.com/";
    public static String IEPET_IMG_HOST = "https://img1.epetbar.com/";
    public static String XUNSHI_HOST = "https://180.166.36.56:8433/";
    //MAOYAN
    public static String CATEYE_HOST = "http://api.maoyan.com/";
    //li视频
    public static String LIVIDEO_HOST = "http://app.pearvideo.com/";
    public static String OPENAPI_HOST = "https://www.apiopen.top/";
    public static int MARQUEE_STATUS = 100;
    public static final String CHANNEL_SELECTED = "dataSelected";
    public static final String CHANNEL_UNSELECTED = "dataUnselected";
    /**
     * 已选中频道的json
     */
//    public static final String SELECTED_CHANNEL_JSON = "selectedChannelJson";
    public static final String SELECTED_CH_JSON = "selectedChJson";
    public static final String UNSELECTED_CH_JSON = "selectedChJson";
    /**
     * w未选频道的json
     */
    public static final String UNSELECTED_CHANNEL_JSON = "unselectChannelJson";
    /**
     * 频道对应的请求参数
     */
    public static final String CHANNEL_CODE = "channelCode";
    public static final String CHANNEL_TITLE = "title";
    public static final String IS_VIDEO_LIST = "isVideoList";
    public static final String VIDEO_CHANNELCODE = "news_video";
    public static String HOME_TAB_SELECT = "";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String configHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.TYPE_XUNSHI://xunshi host
                host = XUNSHI_HOST;
                break;
            case HostType.TYPE_LIVIDEO://LIVIDEO host
                host = LIVIDEO_HOST;
                break;
            case HostType.TYPE_OPENAPI://小视频
                host = OPENAPI_HOST;
                break;
            case HostType.IFeng_NEWS_TYPE://凤凰新闻的host
                host = IFENG_HOST;
                break;
            case HostType.TYPE_IFeng_NEWS_DETAIL://凤凰新闻的新闻详情host
                host = IFENG_DETAIL_HOST;
                break;
            case HostType.TYPE_IFeng_NEWS_HOME_COMMENT://凤凰新闻的home评论
                host = IFENG_HOME_COMMENT_HOST;
                break;
            case HostType.ITOUTIAO_NEWS_TYPE://头条的host
                host = ITOUTIAO_HOST;
                break;
            case HostType.ISOHU_NEWS_TYPE://搜狐新闻的host
                host = ISOHU_HOST;
                break;
            case HostType.IEPET_NEWS_TYPE://epet的host
                host = IEPET_HOST;
                break;
            case HostType.IEPET_IMG_TYPE://epet图片的host
                host = IEPET_IMG_HOST;
                break;
            case HostType.NORMAL_TEST_TYPE://测试的host
                host = NORMAL_HOST;
                break;
            case HostType.NORMAL_163_TYPE://测试的host
                host = NORMAL_163_HOST;
                break;
            case HostType.TYPE_ALL_URL://测试的host
                host = "";
                break;
            case HostType.TYPE_CATEYE_URL://MAOYAN
                host = CATEYE_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }


    /**
     * 凤凰新闻板块Host拼接
     */
    public void hostListType() {
        //新闻+视频
        String NEWS_HOME_WITH_VIDEO = "nlist";
        //广场
        String NEWS_SQUARE = "news_square";
        //广场-24小时新闻精选
        String NEWS_SQUARE_24HOURS = "ClientNews";


    }

    /**
     * HOME
     */
    public static class HOME_HOST_PARAM {
        //新闻板块请求参数id目前是写死的
        public static final String HOME_HOST_PARAM_ID = getIdParam("要闻");
        //上拉加载
        public static final String HOME_HOST_PARAM_ACTION_UP = "up";
        //下拉刷新
        public static final String HOME_HOST_PARAM_ACTION_DOWN = "down";
        //初次进入
        public static final String HOME_HOST_PARAM_ACTION_DEFAULT = "default";
        //加载页数
        public static final int HOME_HOST_PARAM_PULLNUM = 1;

        public static final String HOME_HOST_PARAM_GV = "6.5.2";
        public static final String HOME_HOST_PARAM_AV = "6.5.2";
        //uid:当前使用设备的IMEI
        public static final String HOME_HOST_PARAM_UID = "860966030661400";
        public static final String HOME_HOST_PARAM_UID_RELATION = "6538247eb412db12";
        //deviceid:当前使用设备的IMEI
        public static final String HOME_HOST_PARAM_DEVICEID = "860966030661400";
        public static final String HOME_HOST_PARAM_DEVICEID_RELATION = "6538247eb412db12";
        //proid=ifengnews
        public static final String HOME_HOST_PARAM_PROID = "ifengnews";
        //OS:设备API版本
        public static final String HOME_HOST_PARAM_OS = "android_26";
        //df=androidphone:设备系统类型
        public static final String HOME_HOST_PARAM_DF = "androidphone";
        //VT
        public static final int HOME_HOST_PARAM_VT = 5;
        //screen屏幕像素比
        public static final String HOME_HOST_PARAM_SCREEN = "1080x1794";
        //publishid
        public static final String HOME_HOST_PARAM_PUBLISHID = "2006";
        //nw:网络类型
        public static final String HOME_HOST_PARAM_NW = "wifi";
        //请求时的时间戳
        public static final long HOME_HOST_PARAM_TIMESTMP = 201902464;
        //当前请求的版本
        public static final String HOME_HOST_PARAM_QV = "6.5.2";
        public static final String HOME_HOST_PARAM_ST = "6.5.2";
        public static final String HOME_HOST_PARAM_SN = "6.5.2";
    }

    /**
     * 广场
     */
    //https://api.iclient.ifeng.com/news_square?action=default&gv=6.5.5&av=6.5.5
    // &uid=6538247eb412db12&deviceid=6538247eb412db12
    // &proid=ifengnews&os=android_26&df=androidphone&vt=5&screen=1080x1794&publishid=2899
    public static class SQUARE_HOST_PARAM {
        //下拉刷新
        public static final String SQUARE_HOST_PARAM_ACTION_DEFAULT = "default";
        public static final String SQUARE_HOST_PARAM_ACTION_DOWN = "down";
        //上拉加载
        public static final String SQUARE_HOST_PARAM_ACTION_UP = "up";
        //加载页数
        public static final int SQUARE_HOST_PARAM_PULLNUM = 1;
        public static final String SQUARE_HOST_PARAM_GV = "6.5.5";
        public static final String SQUARE_HOST_PARAM_AV = "6.5.5";
        //uid:当前使用设备的IMEI
        public static final String SQUARE_HOST_PARAM_UID = "6538247eb412db12";
        //deviceid:当前使用设备的IMEI
        public static final String SQUARE_HOST_PARAM_DEVICEID = "6538247eb412db12";
        //proid=ifengnews
        public static final String SQUARE_HOST_PARAM_PROID = "ifengnews";
        //OS:设备API版本
        public static final String SQUARE_HOST_PARAM_OS = "android_26";
        //df=androidphone:设备系统类型
        public static final String SQUARE_HOST_PARAM_DF = "androidphone";
        //VT
        public static final int SQUAREHOST_PARAM_VT = 5;
        //screen屏幕像素比
        public static final String SQUARE_HOST_PARAM_SCREEN = "1080x1794";
        //publishid
        public static final String SQUARE_HOST_PARAM_PUBLISHID = "2899";
        //nw:网络类型
        public static final String SQUARE_HOST_PARAM_NW = "wifi";
        //请求时的时间戳
        public static final long SQUARE_HOST_PARAM_TIMESTMP = 201902464;
        //当前请求的版本
        public static final String SQUARE_HOST_PARAM_QV = "6.5.5";
    }

    /**
     * 小视频
     * 1：图/视频
     * 2：段子/神评
     * 3：图
     * 4：视频
     * 5：图/文/视频
     */
    //https://www.apiopen.top/satinApi?type=4&page=1
    public static class SMALL_VIDEO_HOST_PARAM {
        //图/视频类型
        public static final int SMALL_VIDEO_TYPE_PIC_VIDEO = 1;
        //段子/神评类型
        public static final int SMALL_VIDEO_TYPE_TEXT = 2;
        //图类型
        public static final int SMALL_VIDEO_TYPE_PIC = 3;
        //视频类型
        public static final int SMALL_VIDEO_TYPE_ALL_VIDEO = 4;
        //图/文/视频类型
        public static final int SMALL_VIDEO_TYPE_PIC_VIDEO_TEXT = 5;
    }


    /**
     * 精选视频参数
     */
    public static class CARECHOSEN_VIDEO_HOST_PARAM {
        //推荐/精选
        public static final int VIDEO_TYPE_CHOSEN_CARE_ISHOME = 1;
        public static final String VIDEO_TYPE_CHOSEN_CARE_ISHOME_CHANNELCODE = "320100";
    }


    /**
     * 新闻id获取类型
     *
     * @param id 新闻id
     * @return 新闻类型
     */
    public static String getType(String id) {
        switch (id) {
            case HEADLINE_ID:
                return HEADLINE_TYPE;
            case HOUSE_ID:
                return HOUSE_TYPE;
            default:
                break;
        }
        return OTHER_TYPE;
    }

    /**
     * 咕唧新闻页面不同类型新闻channel代码
     *
     * @param channel 频道名称
     * @return iddaima
     */
    public static String getIdParam(String channel) {
        switch (channel) {
            case "要闻":
                return "SYLB10,SYDT10,SYRECOMMEND";
            case "":
                return "";
            default:
                break;
        }
        return "SYLB10,SYDT10,SYRECOMMEND";
    }

    // 新闻详情
//    public static final String NEWS_DETAIL = NETEAST_HOST + "nc/article/";

    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";

    //    // 北京
    //    public static final String LOCAL_TYPE = "local";
    //    // 北京的Id
    //    public static final String BEIJING_ID = "5YyX5Lqs";
    //example：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html

    //服饰
    public static final String GOODS_TYPE_NAME = "裤子";
    public static final String GOODS_TYPE_CODE = "utf-8";
    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";
    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";
    // 足球
    public static final String FOOTBALL_ID = "T1399700447917";
    // 娱乐
    public static final String ENTERTAINMENT_ID = "T1348648517839";
    // 体育
    public static final String SPORTS_ID = "T1348649079062";
    // 财经
    public static final String FINANCE_ID = "T1348648756099";
    // 科技
    public static final String TECH_ID = "T1348649580692";
    // 电影
    public static final String MOVIE_ID = "T1348648650048";
    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String JOKE_ID = "T1350383429665";
    // 游戏
    public static final String GAME_ID = "T1348654151579";
    // 时尚
    public static final String FASHION_ID = "T1348650593803";
    // 情感
    public static final String EMOTION_ID = "T1348650839000";
    // 精选
    public static final String CHOICE_ID = "T1370583240249";
    // 电台
    public static final String RADIO_ID = "T1379038288239";
    // nba
    public static final String NBA_ID = "T1348649145984";
    // 数码
    public static final String DIGITAL_ID = "T1348649776727";
    // 移动
    public static final String MOBILE_ID = "T1351233117091";
    // 彩票
    public static final String LOTTERY_ID = "T1356600029035";
    // 教育
    public static final String EDUCATION_ID = "T1348654225495";
    // 论坛
    public static final String FORUM_ID = "T1349837670307";
    // 旅游
    public static final String TOUR_ID = "T1348654204705";
    // 手机
    public static final String PHONE_ID = "T1348649654285";
    // 博客
    public static final String BLOG_ID = "T1349837698345";
    // 社会
    public static final String SOCIETY_ID = "T1348648037603";
    // 家居
    public static final String FURNISHING_ID = "T1348654105308";
    // 暴雪游戏
    public static final String BLIZZARD_ID = "T1397016069906";
    // 亲子
    public static final String PATERNITY_ID = "T1397116135282";
    // CBA
    public static final String CBA_ID = "T1348649475931";
    // 消息
    public static final String MSG_ID = "T1371543208049";
    // 军事
    public static final String MILITARY_ID = "T1348648141035";

    /**
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
     */
    public static final String Video = "nc/video/list/";
    public static final String VIDEO_CENTER = "/n/";
    public static final String VIDEO_END_URL = "-10.html";
    // 热点视频
    public static final String VIDEO_HOT_ID = "V9LG4B3A0";
    // 娱乐视频
    public static final String VIDEO_ENTERTAINMENT_ID = "V9LG4CHOR";
    // 搞笑视频
    public static final String VIDEO_FUN_ID = "V9LG4E6VR";
    // 精品视频
    public static final String VIDEO_CHOICE_ID = "00850FRB";

    /**
     * 天气预报url
     */
    public static final String WEATHER_HOST = "http://wthrcdn.etouch.cn/";

    /**
     * 新浪图片新闻
     * http://gank.io/api/data/福利/{size}/{page}
     */
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";

    // 精选列表
    public static final String SINA_PHOTO_CHOICE_ID = "hdpic_toutiao";
    // 趣图列表
    public static final String SINA_PHOTO_FUN_ID = "hdpic_funny";
    // 美图列表
    public static final String SINA_PHOTO_PRETTY_ID = "hdpic_pretty";
    // 故事列表
    public static final String SINA_PHOTO_STORY_ID = "hdpic_story";

    // 图片详情
    public static final String SINA_PHOTO_DETAIL_ID = "hdpic_hdpic_toutiao_4";

    //底部4个Tab
    public static final int RB_MOVIE = 0;
    public static final int RB_CINEMA = 1;
    public static final int RB_DISCOVER = 2;
    public static final int RB_MINE = 3;

    //发现列表2种类型的item
    public static final int TYPE_DISCOVER_ONE_IMG = 0;
    public static final int TYPE_DISCOVER_MULTI_IMG = 1;
    public static final int TYPE_DISCOVER_BIG_IMG = 2;
    public static final int TYPE_DISCOVER_ONE_BIG_IMG = 3;


    //首页热映的2种item
    public static final int TYPE_HOT_HEADLINE = 0;//第一个热门
    public static final int TYPE_HOT_NORMAL = 1;//正常
    public static final int TYPE_HOT_PRE_SELL = 2;//预售

    //待映5种item
    public static final int TYPE_WAIT_DIVIDER = 0;//悬浮item
    public static final int TYPE_WAIT_HEADLINES = 1;//有专访的item
    public static final int TYPE_WAIT_TRAILER = 2;//预告片
    public static final int TYPE_WAIT_RECENT = 3;//近期受期待
    public static final int TYPE_WAIT_NORMAL = 4;//正常

    //分类查找
    public static final int TYPE_CLASSIFY_NORMAL = 0;//不在上映
    public static final int TYPE_CLASSIFY_WISH = 1;//想看
    public static final int TYPE_CLASSIFY_BUY = 2;//可以购票
    public static final int TYPE_CLASSIFY_PRESALE = 3;//预售

    //海外电影
    public static final int TYPE_OVERSEA_NORMAL = 0;//普通
    public static final int TYPE_OVERSEA_PRESALE = 1;//预售
    public static final int TYPE_OVERSEA_BUY = 2;//可以购票
    public static final int TYPE_OVERSEA_HEAD_LINE = 3;//有专题内容
    public static final int TYPE_OVERSEA_FOOTER = 4;//尾部点击查看更多

    //电影奖项
    public static final int TYPE_AWARDS_MOVIE = 0;//最佳电影
    public static final int TYPE_AWARDS_PEOPLE = 1;//最佳导演

    //视频/图片
    public static final int TYPE_MOVIE_DETAIL_VEDIO = 0;
    public static final int TYPE_MOVIE_DETAIL_PHOTO = 1;

    //视频评论
    public static final int TYPE_VIDEO_COMMENT_REPLY = 0;//有回复
    public static final int TYPE_VIDEO_COMMENT_NO_REPLY = 1;//没有回复

    //电影资讯
    public static final int TYPE_MOVIE_INFORMATION_ONE_IMG = 0;
    public static final int TYPE_MOVIE_INFORMATION_MULTI_IMG = 1;

    //电影话题
    public static final int TYPE_MOVIE_TOPIC_ONE_IMG = 0;
    public static final int TYPE_MOVIE_TOPIC_MULTI_IMG = 1;
    public static final int TYPE_MOVIE_TOPIC_NO_IMG = 2;


}
