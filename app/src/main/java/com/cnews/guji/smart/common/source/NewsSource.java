package com.cnews.guji.smart.common.source;

import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CategoryModel;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.constant.NewsConstant;
import com.cnews.guji.smart.ui.model.HomeNewsTabBean;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 新闻数据提取
 *
 * @author JSYL-DCL
 */
public class NewsSource<S> {
    //顶部banner新闻
    public static final String NAME_TYPE_BANNER = "focus";
    //置顶新闻
    public static final String NAME_TYPE_TOP = "top";
    //type=secondnav
    public static final String NAME_TYPE_SECONDNAV = "secondnav";
    //常规新闻
    public static final String NAME_TYPE_List = "list";
    //常规菜单
    public static final String NAME_TYPE_FINANCEHN = "financeHN";

    /**
     * TYPE
     */
    //文章类型
    public static final String NAME_TYPE_DOC = "doc";
    //视频类型
    public static final String NAME_TYPE_PHVIDEO = "phvideo";
    //广告类型
    public static final String NAME_TYPE_ADVERT = "advert";
    //图片类型
    public static final String NAME_TYPE_SLIDE = "slide";
    //话题指定
    public static final String NAME_TYPE_TOPIC2 = "topic2";
    //滚动类别
    public static final String NAME_TYPE_MARQUEE = "marquee";
    public static final String NAME_TYPE_WEB = "web";
    public static final String NAME_TYPE_HOTSPOT = "hotspot";
    public static final String NAME_TYPE_WEBMEDIA = "weMedia";
    public static final String NAME_TYPE_GREGNEWSLIST = "gregnewslist";
    public static final String NAME_TYPE_TEXTLIVE = "text_live";
    public static final String NAME_TYPE_SURVEY = "survey";

    /**
     * VIEW
     */
    //显示形式单图
    public static final String NAME_VIEW_TITLEIMG = "titleimg";
    //显示形式多图
    public static final String NAME_VIEW_SLIDEIMG = "slideimg";
    //置顶，无图
    public static final String NAME_VIEW_SINGLETITLE = "singletitle";

    //显示形式大图
    public static final String NAME_VIEW_BIGIMG = "bigimg";
    public static final String NAME_VIEW_LONGIMG = "longimg";

    public static final String NAME_VIEW_NORMAL = "normal";
    public static final String NAME_VIEW_SLIDES = "slides";
    //横向滚动列表
    public static final String NAME_VIEW_MARQUEE = "marquee";
    public static final String NAME_VIEW_HOTSPOT = "hotspot";
    public static final String NAME_VIEW_SUBSCRIBE = "subscribe";

    /**
     * 是否是常规列表数据
     * @param detail
     * @return
     */
    public static boolean isListNews(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_List);
    }

    /**
     * 是否是banner
     * @param detail
     * @return
     */
    public static boolean isBannerNews(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_BANNER);
    }

    /**
     * 是否存在
     * @param map
     * @return
     */
    public static boolean isExitBannerNews(HashMap map) {
        return map.containsValue(NAME_TYPE_BANNER);
    }
    public static boolean isExitSECONDNAV(HashMap map) {
        return  map.containsValue(NAME_TYPE_SECONDNAV);
    }

    public static boolean isExitListNews(HashMap map) {
        return  map.containsValue(NAME_TYPE_List);
    }

    /**
     * 是否第二导航菜单
     * @param detail
     * @return
     */
    public static boolean isSecondNAV(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_SECONDNAV);
    }

    /**
     * 是否是顶部类型数据
     * @param detail
     * @return
     */
    public static boolean isTopNews(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_TOP);
    }
    /**
     * 是否跑马灯新闻
     * @param detail
     * @return
     */
    public static boolean isFinanceHN(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_FINANCEHN);
    }


    /**
     * 是否是广告
     * @param detail
     * @return
     */
    public static boolean isAvertNews(HomeTopIFengBean detail) {
        return detail.getType().equals(NAME_TYPE_ADVERT);
    }



    /**
     * 要闻分类
     *
     * @param mContext
     * @return
     */
    public static List<CategoryModel> getFrontNewsCategory(Context mContext) {
        List<CategoryModel> list = new ArrayList<CategoryModel>();
        list.add(new CategoryModel(mContext.getString(R.string.txt_type0)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type1)));
        return list;
    }


    /**
     * 视频分类
     *
     * @param mContext
     * @return
     */
    public static List<CategoryModel> getVideoCategory(Context mContext) {

        List<CategoryModel> list = new ArrayList<CategoryModel>();

        list.add(new CategoryModel(mContext.getString(R.string.txt_type2)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type3)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type4)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type5)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type6)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type7)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type8)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type9)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type10)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type11)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type12)));
        list.add(new CategoryModel(mContext.getString(R.string.txt_type13)));

        return list;
    }

    /**
     * 视频随机获取分类
     *
     * @param mContext
     * @return
     */
    public static String[] getVideoCategoryStr(Context mContext) {
        List<String> list = new ArrayList<String>();
        list.add(mContext.getString(R.string.txt_type2));
        list.add(mContext.getString(R.string.txt_type3));
        list.add(mContext.getString(R.string.txt_type4));
        list.add(mContext.getString(R.string.txt_type5));
        list.add(mContext.getString(R.string.txt_type6));
        list.add(mContext.getString(R.string.txt_type7));
        list.add(mContext.getString(R.string.txt_type8));
        list.add(mContext.getString(R.string.txt_type9));
        list.add(mContext.getString(R.string.txt_type10));
        list.add(mContext.getString(R.string.txt_type11));
        list.add(mContext.getString(R.string.txt_type12));
        list.add(mContext.getString(R.string.txt_type13));
        String[] videoCategorysStr = list.toArray(new String[list.size()]);
        return videoCategorysStr;
    }


    /**
     * 获取当前页面初始刷新数据来源的标识文件名
     *
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getVideoRefreshTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_GAOXIAO;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MENGPET;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_FOOD;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_JUNSHI;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MUSIC;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YULE;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YUER;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MOVIE;
                break;
            case 9:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_KEJI;
                break;
            case 10:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_XIAOPIN;
                break;
            case 11:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_DANCES;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN;
                break;
        }
        return jsonNameStr;
    }

    /**
     * 获取当前页面加载更多数据来源的标识文件名
     *
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getVideoLoadMoreTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN_MORE;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_GAOXIAO_MORE;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MENGPET_MORE;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_FOOD_MORE;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_JUNSHI_MORE;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MUSIC_MORE;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YULE_MORE;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_YUER_MORE;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_MOVIE_MORE;
                break;
            case 9:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_KEJI_MORE;
                break;
            case 10:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_XIAOPIN_MORE;
                break;
            case 11:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_DANCES_MORE;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_VIDEO_NEWS_TUIJIAN_MORE;
                break;
        }
        return jsonNameStr;
    }


    /**
     * 获取默认tab标签列表
     *
     * @param tabType 0:我的  1:更多
     * @param context context
     * @return
     */
    public static List getTabCurrentShowData(int tabType, Context context) {
        HomeNewsTabBean multiIndexJsonData = (HomeNewsTabBean) getMultiIndexJsonData(context, NewsConstant.ASSET_HOME_NEWS_TABS);
        if (multiIndexJsonData != null) {
            if (tabType == 0) {
                List<HomeNewsTabBean.Data.My> my = multiIndexJsonData.data.my;
                return my;
            } else if (tabType == 1) {
                List<HomeNewsTabBean.Data.More> more = multiIndexJsonData.data.more;
                return more;
            } else {
                String[] tabtitles = new String[]{"头条", "娱乐"};
                return Arrays.asList(tabtitles);

            }
        } else {
            return null;
        }
    }


    /**
     * 首页
     * 获取当前页面初始刷新数据来源的标识文件名
     *
     * @param currentLoadPos 当前页面位置
     * @return
     */
    public static String getHomeRefreshTypeSource(int currentLoadPos) {
        String jsonNameStr = "";
        switch (currentLoadPos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_HOME_HOT_TOP;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 3://视频
                jsonNameStr = NewsConstant.ASSET_HOME_VIDEO;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
        }
        return jsonNameStr;
    }


    /**
     * 首页
     * 获取当前页面加载更多数据来源的标识文件名
     *
     * @param currentLoadMorePos 当前页面位置
     * @return
     */
    public static String getHomeLoadMoreTypeSource(int currentLoadMorePos) {
        String jsonNameStr = "";
        switch (currentLoadMorePos) {
            case 0:
                jsonNameStr = NewsConstant.ASSET_HOME_HOT_TOP;
                break;
            case 1:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 2:
                jsonNameStr = NewsConstant.ASSET_HOME_VIDEO;
                break;
            case 3:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 4:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 5:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 6:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 7:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            case 8:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
            default:
                jsonNameStr = NewsConstant.ASSET_HOME_IMPORTANT;
                break;
        }
        return jsonNameStr;
    }


    /**
     * 解析json
     */
    public static HomeNewsTabBean getMultiIndexJsonData(Context context, final String fileName) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        Type type = new TypeToken<HomeNewsTabBean>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    /**
     * 首页
     * 获取当前页面初始刷新数据URL ip为nlist的参数Id的值
     *
     * @param currentLoadName 当前页签name
     * @return
     */
    public static String getHomeNListIDSource(String currentLoadName) {
        String nListID = "";
        switch (currentLoadName) {
            case "头条":
                nListID = "SYLB10,SYDT10";
                break;
            case "关注":
                nListID = "";
                break;
            case "互联网":
                nListID = "";
                break;
            case "无人机":
                nListID = "";
                break;
            case "腾讯":
                nListID = "";
                break;
            case "视频":
                nListID = "RECOMVIDEO";
                break;
            case "百度":
                nListID = "";
                break;
            case "财经":
                nListID = "CJ33,FOCUSCJ33,HNCJ33";
                break;
            case "娱乐":
                nListID = "YL53,FOCUSYL53,SECNAVYL53";
                break;
            case "本地":
                nListID = "ClientNewsRegion?id=LOCAL,FOCUSLOCAL";
                break;
            case "新时代":
                nListID = "19METTING";
                break;
            case "舍得讲堂":
                nListID = "SDJT,FOCUSSDJT";
                break;
            case "军事":
                nListID = "JS83,FOCUSJS83";
                break;
            case "科技":
                nListID = "KJ123,FOCUSKJ123,SECNAVKJ123";
                break;
            case "美食":
                nListID = "DELIC,FOCUSDELIC";
                break;
            case "FUN来了":
                nListID = "DZPD,FOCUSDZPD";
                break;
            case "推荐"://特殊
                nListID = "";
                break;
            case "体育":
                nListID = "TY43,FOCUSTY43,TYTOPIC";
                break;
            case "历史":
                nListID = "LS153,FOCUSLS153";
                break;
            case "有料":
                nListID = "MATERIAL";
                break;
            case "小说":
                nListID = "https://fhxw.iyc.ifeng.com/?cid=70004&gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&os=android_26&df=androidphone&vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=&st=15564309476332&sn=88045beefe9c4a8c05d8854c7b8a2128&dlt=32.083912&dln=118.920639&dcy=%E5%8D%97%E4%BA%AC%E5%B8%82&dpr=%E6%B1%9F%E8%8B%8F%E7%9C%81";
                break;
            case "要闻":
                nListID = "YAOWEN223";
                break;
            case "汽车":
                nListID = "";
                break;
            case "台湾":
                nListID = "TW73,FOCUSTW73";
                break;
            case "暖新闻":
                nListID = "NXWPD,FOCUSNXWPD";
                break;
            case "房产":
                nListID = "";
                break;
            case "国际":
                nListID = "GJPD,FOCUSGJPD";
                break;
            case "热文":
                nListID = "CIRCLEHOT";
                break;
            case "健康":
                nListID = "JK36";
                break;
            case "图片":
                nListID = "PICPD";
                break;
            case "时尚":
                nListID = "SS78,FOCUSSS78,SECNAVSS78";
                break;
            case "旅游":
                nListID = "LY67,FOCUSLY67,SECNAVLY67";
                break;
            case "咕唧号":
                nListID = "VAMPIRE,FOCUSVAMPIRE,SECNAVVAMPIRE";
                break;
            case "佛教":
                nListID = "FJ31,FOCUSFJ31,SECNAVFJ31";
                break;
            case "养生":
                nListID = "HEALTHLIVE";
                break;
            case "育儿":
                nListID = "PARENT";
                break;
            case "小视频":
                nListID = "VIDEOSHORT";
                break;
            case "原创":
                nListID = "https://api.iclient.ifeng.com/soleColumnList?soleId=25031&page=1&os=android_26&gv=6.5.5&proid=ifengnews";
                break;
            case "炒股大赛":
                nListID = "";
                break;
            case "改开":
                nListID = "GGKFPD";
                break;
            case "火力无限":
                nListID = "https://api.iclient.ifeng.com/hlwxindex";
                break;
            case "区块链":
                nListID = "BLOCKCHAIN,FOCUSBLOCKCHAIN";
                break;
            case "车科技":
                nListID = "KJCKJ,FOCUSKJCKJ";
                break;
            case "凤翼雄安":
                nListID = "FYXA21,FOCUSFYXA21";
                break;
            case "F1赛事":
                nListID = "F1MATCH,FOCUSF1MATCH";
                break;
            case "游戏":
                nListID = "YX11,FOCUSYX11";
                break;
            case "港股":
                nListID = "HKSTOCKS,FOCUSHKSTOCKS";
                break;
            case "知之":
                nListID = "";
                break;
            case "收藏":
                nListID = "COLLECT";
                break;
            case "故事":
                nListID = "STORY";
                break;
            case "手游中心":
                nListID = "";
                break;
            case "音乐":
                nListID = "";
                break;
            case "NBA":
                nListID = "NBAPD,FOCUSNBAPD";
                break;
            case "萌宠":
                nListID = "MENGCHONG,FOCUSMENGCHONG";
                break;
            case "政务":
                nListID = "ZHENGWUPD,FOCUSZHENGWUPD";
                break;
            case "数码":
                nListID = "SM66,FOCUSSM66";
                break;
            case "港澳":
                nListID = "GA18,FOCUSGA18";
                break;
            case "文化":
                nListID = "WH25,FOCUSWH25";
                break;
            case "读书":
                nListID = "DS57,FOCUSDS57";
                break;
            case "星座":
                nListID = "XZ09,FOCUSXZ09";
                break;
            case "评论":
                nListID = "PL40,FOCUSPL40";
                break;
            case "电影":
                nListID = "DYPD";
                break;
            case "跑步":
                nListID = "PBPD,PBPDFOCUS";
                break;
            case "酒业":
                nListID = "JYPD,FOCUSJYPD";
                break;
            case "原生营销":
                nListID = "FHYX,FOCUSFHYX";
                break;
            case "国学":
                nListID = "GXPD,FOCUSGXPD";
                break;
            case "公益":
                nListID = "GYPD,FOCUSGYPD";
                break;
            case "家居":
                nListID = "JJPD,FOCUSJJPD";
                break;
            case "未来":
                nListID = "https://i.audi-future.ifeng.com/article/articlelist?page=1&pagesize=10";
                break;
            case "中韩交流":
                nListID = "ZHJL,FOCUSZHJL";
                break;
            case "智库":
                nListID = "ZK30,FOCUSZK30";
                break;
            case "彩票":
                nListID = "https://yj.fcai.com/ifengIndex?gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&os=android_26&df=androidphone&vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=&st=15564335122096&sn=abdd37039bccebce2370cfbeb865f79a&dlt=32.083912&dln=118.920639&dcy=%E5%8D%97%E4%BA%AC%E5%B8%82&dpr=%E6%B1%9F%E8%8B%8F%E7%9C%81";
                break;
            case "政能量":
                nListID = "ZNL345";
                break;
            case "教育":
                nListID = "JY90";
                break;
            case "动漫":
                nListID = "https://anime.ifeng.com/newsAnimeChannel/animeChannel.jsp?gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&os=android_26&df=androidphone&vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=&st=15564301125720&sn=3136ceb4663890d774a0316eb09ffeac&dlt=32.083912&dln=118.920639&dcy=%E5%8D%97%E4%BA%AC%E5%B8%82&dpr=%E6%B1%9F%E8%8B%8F%E7%9C%81";
                break;
            case "奇闻轶事":
                nListID = "";
                break;
            default:
                nListID = "SYLB10,SYDT10";
                break;
        }
        return nListID;
    }

    /**
     * 获取当前页面初始刷新数据URL ip为ClientNewsRegion的参数Id的值
     *
     * @param currentLoadName
     * @return
     */
    public static String getHomeClientNewsRegionIDSource(String currentLoadName) {
        switch (currentLoadName) {
            case "汽车":
                return "QC45,FOCUSQC45,SECNAVQC45";
            default:
                break;
        }
        return null;
    }
}
