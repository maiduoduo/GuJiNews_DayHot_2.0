package com.cnews.guji.smart.ui.model.source;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.VideoShareBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 资源整合器
 * @author JSYL-DCL
 * @param
 */
public class NewsQuerySourceHelper {

    public static int getSplashGif() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(R.mipmap.splash_gif_a);
        list.add(R.mipmap.splash_gif_c);
        list.add(R.mipmap.splash_gif_d);
        list.add(R.mipmap.splash_gif_e);
        list.add(R.mipmap.splash_gif_f);
        list.add(R.mipmap.splash_gif_g);
        list.add(R.mipmap.splash_gif_h);
        list.add(R.mipmap.splash_gif_i);
        list.add(R.mipmap.splash_gif_j);


        return list.get(new Random().nextInt(list.size()));
    }


    /**
     * 视频分享-好友相关列表
     * @return
     */
    public static List<VideoShareBean.VideoShareFriendBean> getFriendShareData() {
        List<VideoShareBean.VideoShareFriendBean> friendList = new ArrayList<>();
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_a,"老化看房"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_b,"五子登科树"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_c,"DS大彪"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_d,"ashui-AS"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_e,"唐梓蕊Hazei"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_f,"雪泡儿Morzan"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_g,"H黄亮"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_h,"LemonHarryCarrol"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_i,"盘盘yvonne"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_j,"设计写真馆"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_k,"linKon大卫·龙亦非"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_m,"谈谈"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_n,"树上的一只拧檬果"));
        friendList.add(new VideoShareBean.VideoShareFriendBean(R.mipmap.share_f_more,""));
        return friendList;
    }

    /**
     * 视频分享-其他平台相关列表
     * @return
     */
    public static List<VideoShareBean.VideoShareAppBean> getAppShareData() {
        List<VideoShareBean.VideoShareAppBean> appList = new ArrayList<>();
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_sixin,"私信和群"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_wechat,"微信好友"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_weixin_circle,"朋友圈"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_dingd,"钉钉"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_zhifubao,"支付宝好友"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_tecent_qq,"QQ"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_qzone,"QQ空间"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_sms,"短信"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_email,"邮件分享"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_wx_program,"外部链接"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_sina_weibo,"微博"));
        appList.add(new VideoShareBean.VideoShareAppBean(R.mipmap.share_to_netpan,"网盘"));
        return appList;
    }

    /**
     * 视频分享-长图、复制链接、收藏等
     * @return
     */
    public static List<VideoShareBean.VideoShareLinkBean> getLinkShareData() {
        List<VideoShareBean.VideoShareLinkBean> linkList = new ArrayList<>();
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_longimg,"生成长图"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_xinxian,"新鲜事投稿"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_collect,"收藏"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_top,"帮上头条"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_link,"复制链接"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_pingb,"屏蔽"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_tousu,"投诉"));
        linkList.add(new VideoShareBean.VideoShareLinkBean(R.mipmap.share_to_link_backtohome,"返回首页"));
        return linkList;
    }
}
