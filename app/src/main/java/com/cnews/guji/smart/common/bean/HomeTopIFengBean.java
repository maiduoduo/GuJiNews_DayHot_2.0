package com.cnews.guji.smart.common.bean;

import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.github.library.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

/**
 * |- 推荐
 * ifeng.news
 *
 * @author JSYL-DCL
 */
public class HomeTopIFengBean{
    public int currentPage;
    public DownAdDataBean downAdData;
    public int expiredTime;
    public String listId;
    public int showAdvert;
    public int syRetainOldNew;
    public int topsize;
    public int totalPage;
    public String type;
    public List<ItemBean> item;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public static class DownAdDataBean {
        /**
         * adError : 1
         * errorText : no  addata
         * pid : 10000210
         * type : advert
         */

        public int adError;
        public String errorText;
        public String pid;
        public String type;

        public int getAdError() {
            return adError;
        }

        public void setAdError(int adError) {
            this.adError = adError;
        }

        public String getErrorText() {
            return errorText;
        }

        public void setErrorText(String errorText) {
            this.errorText = errorText;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class ItemBean implements MultiItemEntity {
        //文档类型---------->
        //1.三图
        public static final int TYPE_DOC_SLIDEIMG = 1;
        //2.单图
        public static final int TYPE_DOC_TITLEIMG = 2;
        //3.标题无图
        public static final int TYPE_DOC_SINGLETITLE= 3;

        //视频类型------------->
        //1.单图
        public static final int TYPE_PHVIDEO_TITLEIMAGE = 4;
        //2.单张大图
        public static final int TYPE_PHVIDEO_BIGIMAGE = 5;

        //广告类型----------->
        public static final int TYPE_ADVERT_SLIDEIMG = 6;
        public static final int TYPE_ADVERT_BIGIMAGE = 7;
        public static final int TYPE_ADVERT_TITLEIMAGE = 8;

        //topic2类型-------->
        //1.无图
        public static final int TYPE_TOPIC2_SINGLETITLE = 9;


        //slide类型---------->
        //1.三图图集缩略
        public static final int TYPE_SLIDE_SLIDEIMG = 10;
        public static final int TYPE_SLIDE_NORMAL = 15;
        public static final int TYPE_SLIDE_TITLEIMG = 20;

        //marquee类型------------->
        //1.
        public static final int TYPE_MARQUEE_MARQUEE = 11;


        //显示形式多图
        //置顶
        public static final int TYPE_WEB_TITLEIMG = 12;
        public static final int TYPE_MOREIMG = 13;
        //热闻焦点
        public static final int TYPE_HOTSPOT = 14;
        public static final int TYPE_FINANCEHN_SINGLETITLE = 16;
        public static final int TYPE_GREGNEWSLIST_TITLEIMG = 17;
        public static final int TYPE_TEXTLIVE_TITLEIMG = 18;
        public static final int TYPE_SURVEY_TITLEIMG  = 19;
        public static final int TYPE_FASTMESSAGESCROLL_FASTMESSAGESCROLL  = 21;
        public static final int TYPE_HOTSPOT_HOTSPOT  = 22;



        /**
         * comments : 53
         * commentsUrl : ucms_7m277jFlKPS
         * commentsall : 274
         * documentId : ucms_7m277jFlKPS
         * id : ucms_7m277jFlKPS
         * intro :
         * link : {"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m277jFlKPS&channelKey=&category=%E6%97%B6%E6%94%BF&imId=183342482","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m277jFlKPS"}
         * payload : eyJkb2NpZCI6IjdtMjc3akZsS1BTIn0=
         * recomToken : c88137b5-139f-49e5-a238-73f4e30fc061
         * reftype : jppool|||
         * showType : 0
         * simId : clusterId_2157604
         * source : 澎湃新闻
         * staticId : ucms_7m277jFlKPS
         * style : {"backreason":["0_来源:澎湃新闻","c_时政"],"defaultreason":"0_不感兴趣","view":"titleimg"}
         * subscribe : {"cateid":"310821","catename":"澎湃新闻","type":"vampire"}
         * thumbnail : https://d.ifengimg.com/w230_h152_q100_aix0_aiy0_aiw600_aih365_webp/e0.ifengimg.com/08/2019/0420/4DBFA7FAA7C14997AC7F892A1BF00E1F5CC85A7D_size63_w600_h365.jpeg.webp
         * title : 宣布参选澳门特首的贺一诚请辞全国人大代表职务
         * type : doc
         * updateTime : 2019/04/21 10:05:59
         * relation : [{"comments":"1","commentsUrl":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","commentsall":"1","documentId":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","id":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","intro":"","link":{"type":"phvideo","url":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=0113f7ce-dc1e-464a-8e85-7bb01c3423f6"},"phvideo":{"channelName":"风视频","columnid":"543545","filesize":"10514","length":67,"path":"51-52"},"recomToken":"cd0177c1-d99f-4670-9991-b2e18ba70f00","reftype":"ai|||","showType":"0","simId":"clusterId_115052972","staticId":"video_0113f7ce-dc1e-464a-8e85-7bb01c3423f6","style":{"backreason":["0_来源:风视频"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"543545","catename":"风视频","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix15_aiy0_aiw816_aih540_webp/p0.ifengimg.com/pmop/2019/04/22/wf2_5127735_091959_sizeb_w960_h540.jpg.webp","title":"斯里兰卡爆炸地前后对比：遍地爆炸碎片 有教堂屋顶被炸飞","type":"phvideo","updateTime":""},{"comments":"50","commentsUrl":"ucms_7m46IuQAt0Q","commentsall":"171","documentId":"ucms_7m46IuQAt0Q","id":"ucms_7m46IuQAt0Q","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m46IuQAt0Q&channelKey=&category=&imId=183407940","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m46IuQAt0Q"},"recomToken":"50837fa1-9656-4f6f-b99c-57976a203119","reftype":"ai|||","showType":"0","simId":"clusterId_3347499","source":"新京报","staticId":"ucms_7m46IuQAt0Q","style":{"backreason":["0_来源:新京报"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"新京报","catename":"新京报","type":"source"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy0_aiw229_aih152_webp/p1.ifengimg.com/2019_17/3786F1F5D1ACB0F9CE17BB513A77EE298B8FE3EC_w230_h152.png.webp","title":"中科院师生在斯里兰卡遇袭受伤 游客睡梦中被震醒","type":"doc","updateTime":"2019/04/22 04:11:54"}]
         * adId : iis_feather_13650_32792_1520270_1555898345002_ncyked
         * adPositionId : 10000250
         * appSource :
         * icon : {"showIcon":1,"text":"广告"}
         * pid : 10000250
         * phvideo : {"channelName":"三秦大地","columnid":"1214865","filesize":"5741","length":105,"path":"140-141"}
         * hasSlide : true
         */

        public String comments;
        public String commentsUrl;
        public String commentsall;
        public String documentId;
        public String id;
        public String intro;
        public LinkBean link;
        public String payload;
        public String recomToken;
        public String reftype;
        public String showType;
        public String simId;
        public String source;
        public String staticId;
        public StyleBean style;
        public SubscribeBean subscribe;
        public String thumbnail;
        public String title;
        public String titleIcon;
        public String type;
        public String updateTime;
        public String adId;
        public String adPositionId;
        public String appSource;
        public IconBean icon;
        public String pid;
        public PhvideoBean phvideo;
        public boolean hasSlide;
        public List<RelationBean> relation;
        public List<MarqueeListBean> marqueeList;
        public int itemType;
        public String errorText;
        public int adError;



        @Override
        public int getItemType() {
            try {
                //DOC
                if (NewsSource.NAME_TYPE_DOC.equals(type)) {
                    if (style.view != null) {
                        if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                            itemType = TYPE_DOC_TITLEIMG;
                        } else if (NewsSource.NAME_VIEW_SLIDEIMG.equals(style.view)) {
                            itemType = TYPE_DOC_SLIDEIMG;
                        } else if (NewsSource.NAME_VIEW_SINGLETITLE.equals(style.view)) {
                            itemType = TYPE_DOC_SINGLETITLE;
                        } else {
                            itemType = TYPE_DOC_SINGLETITLE;
                        }
                    }
                }
                //phvideo
                else if (NewsSource.NAME_TYPE_PHVIDEO.equals(type)) {
                    if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_PHVIDEO_TITLEIMAGE;
                    } else if (NewsSource.NAME_VIEW_BIGIMG.equals(style.view)) {
                        itemType = TYPE_PHVIDEO_BIGIMAGE;
                    } else {
                        itemType = TYPE_PHVIDEO_TITLEIMAGE;
                    }
                }
                //advert
                else if (NewsSource.NAME_TYPE_ADVERT.equals(type)) {
                    if (NewsSource.NAME_VIEW_SLIDEIMG.equals(style.view)) {
                        itemType = TYPE_ADVERT_SLIDEIMG;
                    } else if (NewsSource.NAME_VIEW_BIGIMG.equals(style.view)) {
                        itemType = TYPE_ADVERT_BIGIMAGE;
                    } else if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_ADVERT_TITLEIMAGE;
                    } else {
                        itemType = TYPE_ADVERT_TITLEIMAGE;
                    }
                }
                //topic2
                else if (NewsSource.NAME_TYPE_TOPIC2.equals(type)) {
                    if (NewsSource.NAME_VIEW_SINGLETITLE.equals(style.view)) {
                        itemType = TYPE_TOPIC2_SINGLETITLE;
                    } else {
                        itemType = TYPE_TOPIC2_SINGLETITLE;
                    }
                }
                //slide
                else if (NewsSource.NAME_TYPE_SLIDE.equals(type)) {
                    if (NewsSource.NAME_VIEW_NORMAL.equals(style.view)) {
                        itemType = TYPE_SLIDE_NORMAL;
                    } else if (NewsSource.NAME_VIEW_SLIDEIMG.equals(style.view)) {
                        itemType = TYPE_SLIDE_SLIDEIMG;
                    } else if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_SLIDE_TITLEIMG;
                    } else {
                        itemType = TYPE_SLIDE_TITLEIMG;
                    }
                }
                //marquee
                else if (NewsSource.NAME_TYPE_MARQUEE.equals(type)) {
                    if (NewsSource.NAME_VIEW_MARQUEE.equals(style.view)) {
                        itemType = TYPE_MARQUEE_MARQUEE;
                    } else {
                        itemType = TYPE_DOC_TITLEIMG;
                    }
                }
                //WEB
                else if (NewsSource.NAME_TYPE_WEB.equals(type)) {
                    if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_WEB_TITLEIMG;
                    } else {
                        itemType = TYPE_WEB_TITLEIMG;
                    }
                }
                //type=financeHN
                else if (NewsSource.NAME_TYPE_FINANCEHN.equals(type)) {
                    if (NewsSource.NAME_VIEW_SINGLETITLE.equals(style.view)) {
                        itemType = TYPE_FINANCEHN_SINGLETITLE;
                    } else {
                        itemType = TYPE_FINANCEHN_SINGLETITLE;
                    }
                }  else if (NewsSource.NAME_TYPE_GREGNEWSLIST.equals(type)) {
                    if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_GREGNEWSLIST_TITLEIMG;
                    } else {
                        itemType = TYPE_GREGNEWSLIST_TITLEIMG;
                    }
                }
                //text_live 直播回顾
                else if (NewsSource.NAME_TYPE_TEXTLIVE.equals(type)) {
                    if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_TEXTLIVE_TITLEIMG;
                    } else {
                        itemType = TYPE_TEXTLIVE_TITLEIMG;
                    }
                }
                //SURVE 调查
                else if (NewsSource.NAME_TYPE_SURVEY.equals(type)) {
                    if (NewsSource.NAME_VIEW_TITLEIMG.equals(style.view)) {
                        itemType = TYPE_SURVEY_TITLEIMG;
                    } else {
                        itemType = TYPE_SURVEY_TITLEIMG;
                    }
                }

                //SURVE 调查
                else if (NewsSource.NAME_TYPE_FASTMESSAGESCROLL.equals(type)) {
                    if (NewsSource.NAME_VIEW_FASTMESSAGESCROLL.equals(style.view)) {
                        itemType = TYPE_FASTMESSAGESCROLL_FASTMESSAGESCROLL;
                    } else {
                        itemType = TYPE_FASTMESSAGESCROLL_FASTMESSAGESCROLL;
                    }
                }

                //SURVE 调查
                else if (NewsSource.NAME_TYPE_HOTSPOT.equals(type)) {
                    if (NewsSource.NAME_VIEW_HOTSPOT.equals(style.view)) {
                        itemType = TYPE_HOTSPOT_HOTSPOT;
                    } else {
                        itemType = TYPE_HOTSPOT_HOTSPOT;
                    }
                }

                else {
                }
            }catch (Exception e){
                e.printStackTrace();
                itemType = TYPE_DOC_TITLEIMG;
            }
            return itemType;
        }


        public static class MarqueeListBean {
            public String comments;
            public String commentsUrl;
            public String commentsall;
            public String documentId;
            public Object intro;
            public LinkBean link;
            public String recomToken;
            public String reftype;
            public String simId;
            public String remark;
            public String source;
            public String staticId;
            public StyleBean style;
            public String thumbnail;
            public String title;
            public String type;

            public static class StyleBean {
                /**
                 * backreason : ["0_来源:澎湃新闻","c_时政"]
                 * defaultreason : 0_不感兴趣
                 * view : titleimg
                 */

                public String defaultreason;
                public String view;
                public List<String> images;
                public List<String> backreason;


            }

            public static class LinkBean {
                /**
                 * openType : 1
                 * type : doc
                 * url : https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m277jFlKPS&channelKey=&category=%E6%97%B6%E6%94%BF&imId=183342482
                 * weburl : https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m277jFlKPS
                 */

                public String openType;
                public String type;
                public String url;
                public String weburl;

            }

        }


        public static class LinkBean {
            /**
             * openType : 1
             * type : doc
             * url : https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m277jFlKPS&channelKey=&category=%E6%97%B6%E6%94%BF&imId=183342482
             * weburl : https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m277jFlKPS
             */

            public String openType;
            public String type;
            public String url;
            public String weburl;

        }

        public static class StyleBean {
            /**
             * backreason : ["0_来源:澎湃新闻","c_时政"]
             * defaultreason : 0_不感兴趣
             * view : titleimg
             */

            public String defaultreason;
            public String view;
            public int slideCount;
            public List<String> images;
            public List<String> backreason;
            public RecomReasonBean recomreason;

            public static class RecomReasonBean {
                public int pos;
                public String reasonName;
                public int reasonStyle;


            }

          
        }




        public static class SubscribeBean {
            /**
             * cateid : 310821
             * catename : 澎湃新闻
             * type : vampire
             */

            public String cateid;
            public String catename;
            public String type;

        
        }

        public static class IconBean {
            /**
             * showIcon : 1
             * text : 广告
             */

            public int showIcon;
            public String text;

         
        }

        public static class PhvideoBean {
            /**
             * channelName : 三秦大地
             * columnid : 1214865
             * filesize : 5741
             * length : 105
             * path : 140-141
             */

            public String channelName;
            public String columnid;
            public String filesize;
            public int length;
            public String path;

         
        }

        public static class RelationBean {
            /**
             * comments : 1
             * commentsUrl : 0113f7ce-dc1e-464a-8e85-7bb01c3423f6
             * commentsall : 1
             * documentId : 0113f7ce-dc1e-464a-8e85-7bb01c3423f6
             * id : 0113f7ce-dc1e-464a-8e85-7bb01c3423f6
             * intro :
             * link : {"type":"phvideo","url":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=0113f7ce-dc1e-464a-8e85-7bb01c3423f6"}
             * phvideo : {"channelName":"风视频","columnid":"543545","filesize":"10514","length":67,"path":"51-52"}
             * recomToken : cd0177c1-d99f-4670-9991-b2e18ba70f00
             * reftype : ai|||
             * showType : 0
             * simId : clusterId_115052972
             * staticId : video_0113f7ce-dc1e-464a-8e85-7bb01c3423f6
             * style : {"backreason":["0_来源:风视频"],"defaultreason":"0_不感兴趣","view":"titleimg"}
             * subscribe : {"cateid":"543545","catename":"风视频","type":"vampire"}
             * thumbnail : https://d.ifengimg.com/w230_h152_q100_aix15_aiy0_aiw816_aih540_webp/p0.ifengimg.com/pmop/2019/04/22/wf2_5127735_091959_sizeb_w960_h540.jpg.webp
             * title : 斯里兰卡爆炸地前后对比：遍地爆炸碎片 有教堂屋顶被炸飞
             * type : phvideo
             * updateTime :
             * source : 新京报
             */

            public String comments;
            public String commentsUrl;
            public String commentsall;
            public String documentId;
            public String id;
            public String intro;
            public LinkBeanX link;
            public PhvideoBeanX phvideo;
            public String recomToken;
            public String reftype;
            public String showType;
            public String simId;
            public String staticId;
            public StyleBeanX style;
            public SubscribeBeanX subscribe;
            public String thumbnail;
            public String title;
            public String type;
            public String updateTime;
            public String source;

           

            public static class LinkBeanX {
                /**
                 * type : phvideo
                 * url : 0113f7ce-dc1e-464a-8e85-7bb01c3423f6
                 * weburl : https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=0113f7ce-dc1e-464a-8e85-7bb01c3423f6
                 */

                public String type;
                public String url;
                public String weburl;


            }

            public static class PhvideoBeanX {
                /**
                 * channelName : 风视频
                 * columnid : 543545
                 * filesize : 10514
                 * length : 67
                 * path : 51-52
                 */

                public String channelName;
                public String columnid;
                public String filesize;
                public int length;
                public String path;

            }

            public static class StyleBeanX {
                /**
                 * backreason : ["0_来源:风视频"]
                 * defaultreason : 0_不感兴趣
                 * view : titleimg
                 */

                public String defaultreason;
                public String view;
                public List<String> backreason;

            }

            public static class SubscribeBeanX {
                /**
                 * cateid : 543545
                 * catename : 风视频
                 * type : vampire
                 */

                public String cateid;
                public String catename;
                public String type;

            }
        }
    }


    /**
     * currentPage : 1
     * downAdData : {"adError":1,"errorText":"no  addata","pid":"10000210","type":"advert"}
     * expiredTime : 180000
     * item : [{"comments":"53","commentsUrl":"ucms_7m277jFlKPS","commentsall":"274","documentId":"ucms_7m277jFlKPS","id":"ucms_7m277jFlKPS","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m277jFlKPS&channelKey=&category=%E6%97%B6%E6%94%BF&imId=183342482","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m277jFlKPS"},"payload":"eyJkb2NpZCI6IjdtMjc3akZsS1BTIn0=","recomToken":"c88137b5-139f-49e5-a238-73f4e30fc061","reftype":"jppool|||","showType":"0","simId":"clusterId_2157604","source":"澎湃新闻","staticId":"ucms_7m277jFlKPS","style":{"backreason":["0_来源:澎湃新闻","c_时政"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"310821","catename":"澎湃新闻","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy0_aiw600_aih365_webp/e0.ifengimg.com/08/2019/0420/4DBFA7FAA7C14997AC7F892A1BF00E1F5CC85A7D_size63_w600_h365.jpeg.webp","title":"宣布参选澳门特首的贺一诚请辞全国人大代表职务","type":"doc","updateTime":"2019/04/21 10:05:59"},{"comments":"0","commentsUrl":"111111","commentsall":"0","documentId":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%96%AF%E9%87%8C%E5%85%B0%E5%8D%A1%E8%BF%9E%E7%8E%AF%E7%88%86%E7%82%B8%E6%AD%BB%E4%BC%A4%E6%83%A8%E9%87%8D","id":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%96%AF%E9%87%8C%E5%85%B0%E5%8D%A1%E8%BF%9E%E7%8E%AF%E7%88%86%E7%82%B8%E6%AD%BB%E4%BC%A4%E6%83%A8%E9%87%8D","intro":"","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%96%AF%E9%87%8C%E5%85%B0%E5%8D%A1%E8%BF%9E%E7%8E%AF%E7%88%86%E7%82%B8%E6%AD%BB%E4%BC%A4%E6%83%A8%E9%87%8D"},"payload":"eyJkb2NpZCI6ImhvdHNwb3RfMTU1NTg5ODM0NDg5OSJ9","recomToken":"e33ae403-39d5-4cb9-a8ff-ea9839be54d9","reftype":"ai|||","relation":[{"comments":"1","commentsUrl":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","commentsall":"1","documentId":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","id":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","intro":"","link":{"type":"phvideo","url":"0113f7ce-dc1e-464a-8e85-7bb01c3423f6","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=0113f7ce-dc1e-464a-8e85-7bb01c3423f6"},"phvideo":{"channelName":"风视频","columnid":"543545","filesize":"10514","length":67,"path":"51-52"},"recomToken":"cd0177c1-d99f-4670-9991-b2e18ba70f00","reftype":"ai|||","showType":"0","simId":"clusterId_115052972","staticId":"video_0113f7ce-dc1e-464a-8e85-7bb01c3423f6","style":{"backreason":["0_来源:风视频"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"543545","catename":"风视频","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix15_aiy0_aiw816_aih540_webp/p0.ifengimg.com/pmop/2019/04/22/wf2_5127735_091959_sizeb_w960_h540.jpg.webp","title":"斯里兰卡爆炸地前后对比：遍地爆炸碎片 有教堂屋顶被炸飞","type":"phvideo","updateTime":""},{"comments":"50","commentsUrl":"ucms_7m46IuQAt0Q","commentsall":"171","documentId":"ucms_7m46IuQAt0Q","id":"ucms_7m46IuQAt0Q","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m46IuQAt0Q&channelKey=&category=&imId=183407940","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m46IuQAt0Q"},"recomToken":"50837fa1-9656-4f6f-b99c-57976a203119","reftype":"ai|||","showType":"0","simId":"clusterId_3347499","source":"新京报","staticId":"ucms_7m46IuQAt0Q","style":{"backreason":["0_来源:新京报"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"新京报","catename":"新京报","type":"source"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy0_aiw229_aih152_webp/p1.ifengimg.com/2019_17/3786F1F5D1ACB0F9CE17BB513A77EE298B8FE3EC_w230_h152.png.webp","title":"中科院师生在斯里兰卡遇袭受伤 游客睡梦中被震醒","type":"doc","updateTime":"2019/04/22 04:11:54"}],"staticId":"hotspot_hotspot","style":{"backreason":[],"defaultreason":"0_不感兴趣","view":"hotspot"},"thumbnail":"","title":"斯里兰卡连环爆炸死伤惨重","type":"hotspot","updateTime":""},{"adId":"iis_feather_13650_32792_1520270_1555898345002_ncyked","adPositionId":"10000250","appSource":"","icon":{"showIcon":1,"text":"广告"},"intro":"","link":{"async_click":["https://iis1.deliver.ifeng.com/c?p=Ttye4RoN6-JNZNWgtEKR9yoHPFTi44jfyqXsjEqbLKtNQQP4HYbPfETcdWFtr8XHXUoOqDmzC9gwBXxcA6Zg15mvs2vfQnjUgqsdM_dGjEiZUh3m_gxMKw44AgnrC-ELW6LglJFGAEg6o7579ph72fS1ommDR5RpdfwzvkcR0_v6aIi4chNPYgEBobsvyGDDmmak3FFYNY8DkkdGrkd8T96TugUxuiX40OTJtkqn8PQtFzfSA_mAhG8QlkBrFNap"],"pagemonitor_close":"https://ua.deliver.ifeng.com/monitor/cm.gif?adid=13650&type=c&reqid=65c52b2bd0f9c0eb557c1195acebe6b8","pagemonitor_open":"https://ua.deliver.ifeng.com/monitor/cm.gif?adid=13650&type=o&reqid=65c52b2bd0f9c0eb557c1195acebe6b8","pvurl":["https://iis1.deliver.ifeng.com/ids/mnt/imp?args=FRBmL4Gt7D561tdOYFBVVGZE5Pz-aVwgXIuCi2VqSJdSIluTLhG6L8P10SLqrzZxh1Xvil-1iLtbRtdAyLkoWnWXaDGM2UWBKv_Z-uREvhtw1oKrZ1M-9WJKDmZHko6S_CWLrDK7wZxBPaUUZfIhVVFx4pIt3B4jFCq4A4gPajptElld0yLgLYdvAnQLWGTq2BnkIQikUX9ic2BWP2Yzcn11tfMdJbOA6_j5uZOeizC0BicLiajaDOUH0z-MFnwOxghsk1eyzrK4LJ0lI6ETuXXwvyS0uxwxaSs_0NrjILQBrdETVUp0FTovXEqROsstW_hEm1egwisGOZtSu0GrlRvEomiB35SalWFvo_-FL7KJ0nVl3h0us-W-xapjBKKdmfdWlPsgKkhVOzH05eXCVNc_dffr7uZdyoXpVAdWF2ANl0Ukobp0LdWRKKI3tfgX58iKhfn1qpZ3xnQ5dqFpzhCT_2dieiGrk56znJ2mSLBM8r2SZpftC01EVGQgxAonKgX8U62yQeyE_HPt95_y3zJbIbf9CCWOKjOFrSHvFqCEqWKrj1xYEuZ-cYLWcTJ2FQYXZ3ZJ5-la3tgiiRMpXM0-hZjtatJE79vNuiWZRDgN53HUDmlXiNw0LqUeO2H86LH2lUrh_EhEAJ19W1n570npgWLl9_KRI69erUvUfvYRXTiz0-p7ninpUFCLDsVqvFOj__Wz0vOsEYFwPwOHsVm0ZLEEqxqPe6TyUW7-O-qqfkUB2MKOg1NrC1wmafO9dGw8AauOxPMv5nGmrEUvsO3RCxVZoW-nanLUh31c2MdQW8QBmk7f7YKIaDfUlLwQ1agR_y60QE5AXfRyQFndNB0ISOd0XyqbX73yp1UVWJg&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA","https://iis1.deliver.ifeng.com/i?p=MUHL0xdKQ_1GzJo7EQmiJkBuEV6uLEl4-ajEou308d0ynRGLiRvn8VWu5gsrCHwlBQ41219c1Hpoya0TEoPbImYon64BMjvKDiA64nMUXploJyl4gRlJi7hQQqavwaygEkFvpqueTpDdDUDGCC0kYcAxV4m_cxJPBknTleWd1Z7Q2zmAqhD3rQgO7oIEaLJqeDa9Z_Yo2NEZBgfNDYwDlaocX3FJDmYEhVjwZHoeHzJ-4t-bzXHYzxQgHocdoHy7wlTdQTurJQnVAzPvcUlCqw"],"type":"web","url":"https://iis1.deliver.ifeng.com/ids/mnt/cli?args=py_a_UT3ZKNwIDHm5bgLslVl-VmDC37jEHIV4dn6Z84NTn4IczcELj0GSFsn13pLuW25MThTLfxfwKJYROfX4K4tvRaamNtBvZTAlH1z6cxw1oKrZ1M-9WJKDmZHko6SHywYbkLWWsL2AfFnYkpQy4TkFnU3OSjuNlUEM4Wj8va1RTXL8jKwKr059erXiQH92BnkIQikUX9ic2BWP2Yzcr_n7iVv7yO9oOwiNFXIMz-xx7o__YFME1LPBe9h8Q8trjqq3aHNJaxq3FVUYpvlC_lbLldOdqO3641w3dw36j0TQOlG_Ikv_APVPSMZvCurD7P7AC--uS2QjfN4W27hH8La-oGIEbpAK4Bw-17PuTGxX3Q6hFe9CrDUhyecoC5zXEuufQaHRdX3ino2JYGHOFS-uc5wsjM69ns6qMac6qhsBXPHaXtLh6VAiTFiMYkn2N8mwnv09VBqtGSrTSM_Sf8L0TqVxaKbxcmcDdq5d3PmfJfo8hfrI8hK-oMIf2IrrviUgIeTd7W8W_pAl1lgsTM0nRyuUKnYs_TIlka30qw0r5BKwYHYrQIcy-F9kGK52xaYo-xRMbEUfBF_KfFzKjLlMOWLsMCnT6_5aNawbWuLXz8zojAcwJ4Nmw0ymptLDWLm6NM2Gma12nJbuRjhurky1qqarPHwrfhJuMvnif7kmLjpEi9jEa43xNloaopABaXHmuj131n2_hNTeiS-N19OKCOBWSy-IMoTwRmQxIKi_AmWkTja9v6-WBy30FEV2rkKwwRs2RGA2fzhTBNLJF2W1rUiYY0Z8PsB9ta7rCdWsGR_fJJhG3pXuH4fza_xIOmTyLjZCohOi30SWz-8AKgx0cI176npd0lm2U0Hm4s&jmp=3GY4fR8mm8b9LGQWZKNkXDQYGp0Qgl3PFTXBw6mL_e4a7pfJHVeq9AgZCwh2JBwc&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA","weburl":"https://iis1.deliver.ifeng.com/ids/mnt/cli?args=py_a_UT3ZKNwIDHm5bgLslVl-VmDC37jEHIV4dn6Z84NTn4IczcELj0GSFsn13pLuW25MThTLfxfwKJYROfX4K4tvRaamNtBvZTAlH1z6cxw1oKrZ1M-9WJKDmZHko6SHywYbkLWWsL2AfFnYkpQy4TkFnU3OSjuNlUEM4Wj8va1RTXL8jKwKr059erXiQH92BnkIQikUX9ic2BWP2Yzcr_n7iVv7yO9oOwiNFXIMz-xx7o__YFME1LPBe9h8Q8trjqq3aHNJaxq3FVUYpvlC_lbLldOdqO3641w3dw36j0TQOlG_Ikv_APVPSMZvCurD7P7AC--uS2QjfN4W27hH8La-oGIEbpAK4Bw-17PuTGxX3Q6hFe9CrDUhyecoC5zXEuufQaHRdX3ino2JYGHOFS-uc5wsjM69ns6qMac6qhsBXPHaXtLh6VAiTFiMYkn2N8mwnv09VBqtGSrTSM_Sf8L0TqVxaKbxcmcDdq5d3PmfJfo8hfrI8hK-oMIf2IrrviUgIeTd7W8W_pAl1lgsTM0nRyuUKnYs_TIlka30qw0r5BKwYHYrQIcy-F9kGK52xaYo-xRMbEUfBF_KfFzKjLlMOWLsMCnT6_5aNawbWuLXz8zojAcwJ4Nmw0ymptLDWLm6NM2Gma12nJbuRjhurky1qqarPHwrfhJuMvnif7kmLjpEi9jEa43xNloaopABaXHmuj131n2_hNTeiS-N19OKCOBWSy-IMoTwRmQxIKi_AmWkTja9v6-WBy30FEV2rkKwwRs2RGA2fzhTBNLJF2W1rUiYY0Z8PsB9ta7rCdWsGR_fJJhG3pXuH4fza_xIOmTyLjZCohOi30SWz-8AKgx0cI176npd0lm2U0Hm4s&jmp=3GY4fR8mm8b9LGQWZKNkXDQYGp0Qgl3PFTXBw6mL_e4a7pfJHVeq9AgZCwh2JBwc&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA"},"pid":"10000250","source":"云顶购物APP","style":{"attribute":"广告","backreason":["不感兴趣","虚假广告","看过了","档次太低"],"defaultreason":"0_不感兴趣","images":["https://c1.ifengimg.com/feather/images/32792/2019/02/27/15512518921103879.jpg","https://c1.ifengimg.com/feather/images/32792/2019/02/27/15512518947092929.jpg","https://c1.ifengimg.com/feather/images/32792/2019/02/27/15512518974019344.jpg"],"view":"slideimg"},"thumbnail":"https://c1.ifengimg.com/feather/images/32792/2019/02/27/15512518921103879.jpg","title":"大牌耐克降价只卖一天限4月22日","type":"advert"},{"comments":"81","commentsUrl":"ucms_7m3XLErErPK","commentsall":"93","documentId":"ucms_7m3XLErErPK","id":"ucms_7m3XLErErPK","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m3XLErErPK&channelKey=&category=%E6%97%B6%E6%94%BF&imId=183392076","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m3XLErErPK"},"payload":"eyJkb2NpZCI6IjdtM1hMRXJFclBLIn0=","recomToken":"ce4273b1-da93-4778-ac96-1289a1f476d7","reftype":"ai|||","showType":"0","simId":"clusterId_35542463","source":"红星新闻","staticId":"ucms_7m3XLErErPK","style":{"backreason":["0_来源:红星新闻","c_时政","et_每日镜报","sc_国际"],"defaultreason":"0_不感兴趣","recomReason":{"pos":"1","reasonName":"热点","reasonStyle":"12"},"view":"titleimg"},"subscribe":{"cateid":"7244","catename":"红星新闻","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy9_aiw640_aih423_webp/e0.ifengimg.com/08/2019/0421/93EA5DD9C4CEF8E81C7896CA85FC8A789A541E69_size48_w640_h480.jpeg.webp","title":"斯里兰卡连环爆炸2名嫌犯提前1天入住酒店 用25公斤炸药","type":"doc","updateTime":"2019/04/21 09:46:28"},{"comments":"0","commentsUrl":"517e9c23-be45-4a08-9ec7-8fc0984793f2","commentsall":"0","documentId":"517e9c23-be45-4a08-9ec7-8fc0984793f2","id":"517e9c23-be45-4a08-9ec7-8fc0984793f2","intro":"","link":{"type":"phvideo","url":"517e9c23-be45-4a08-9ec7-8fc0984793f2","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=517e9c23-be45-4a08-9ec7-8fc0984793f2"},"payload":"eyJkb2NpZCI6IjEyOTM5NzUzNCJ9","phvideo":{"channelName":"三秦大地","columnid":"1214865","filesize":"5741","length":105,"path":"140-141"},"recomToken":"779f257e-7cbc-4873-a1c1-3896460612fe","reftype":"ai||videoCmppSpider|","showType":"1","simId":"clusterId_108071047","staticId":"video_517e9c23-be45-4a08-9ec7-8fc0984793f2","style":{"backreason":["0_来源:三秦大地","c_生活","sc_小百科"],"defaultreason":"0_不感兴趣","view":"bigimg"},"subscribe":{"cateid":"1214865","catename":"三秦大地","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w696_h392_q100_webp/e0.ifengimg.com/11/2019/0223/810EB1E6892BBA4DD88A0331110DC46CC10D3102_size4_w172_h120.jpeg.webp","title":"烧水壶内有厚厚的\u201c水垢\u201d，水还能喝吗？专业测试解开多年疑惑","type":"phvideo","updateTime":""},{"comments":"66","commentsUrl":"ucms_7m1qDzfXMfY","commentsall":"110","documentId":"ucms_7m1qDzfXMfY","hasSlide":true,"id":"ucms_7m1qDzfXMfY","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m1qDzfXMfY&channelKey=&category=&imId=183329967","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m1qDzfXMfY"},"payload":"eyJkb2NpZCI6IjdtMXFEemZYTWZZIn0=","recomToken":"63e50dee-4f81-4d87-b695-d7ec4ce791b0","reftype":"ai|||","showType":"0","simId":"clusterId_48505853","source":"汪小碗时尚ing","staticId":"ucms_7m1qDzfXMfY","style":{"backreason":["0_来源:汪小碗时尚ing"],"defaultreason":"0_不感兴趣","images":["https://d.ifengimg.com/w230_h152_q100_aix0_aiy77_aiw419_aih276_webp/e0.ifengimg.com/12/2019/0420/ED6893F253F313C387AB736F4354D48EC15DCFB2_size31_w419_h600.jpeg.webp","https://d.ifengimg.com/w230_h152_q100_aix0_aiy78_aiw606_aih399_webp/e0.ifengimg.com/07/2019/0420/0DAA61718F417CEA79533058743935A0B9C07CA9_size75_w606_h899.jpeg.webp","https://d.ifengimg.com/w230_h152_q100_aix0_aiy105_aiw640_aih422_webp/e0.ifengimg.com/12/2019/0420/D379A8955C5F00CC899FE3D80A2248B58463F121_size51_w640_h960.jpeg.webp"],"slideCount":5,"type":"slides","view":"slideimg"},"subscribe":{"cateid":"1214179","catename":"汪小碗时尚ing","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy77_aiw419_aih276_webp/e0.ifengimg.com/12/2019/0420/ED6893F253F313C387AB736F4354D48EC15DCFB2_size31_w419_h600.jpeg.webp","title":"宋佳真厉害，上身只用一块蚊帐遮住，网友：真想一把掀掉！","type":"doc","updateTime":"2019/04/20 16:52:54"},{"comments":"5","commentsUrl":"ucms_7m3RRoVWElh","commentsall":"7","documentId":"ucms_7m3RRoVWElh","id":"ucms_7m3RRoVWElh","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m3RRoVWElh&channelKey=&category=&imId=183383528","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m3RRoVWElh"},"payload":"eyJkb2NpZCI6IjdtM1JSb1ZXRWxoIn0=","recomToken":"69002d26-d244-4f89-953c-baaca76ac82e","reftype":"ai|||","showType":"0","simId":"clusterId_114976519","source":"台海网","staticId":"ucms_7m3RRoVWElh","style":{"backreason":["0_来源:台海网"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"1224047","catename":"台海网","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix70_aiy0_aiw510_aih338_webp/e0.ifengimg.com/06/2019/0421/EE9D9492675F360EF9B52A376DFA19ECF404FF65_size93_w600_h338.jpeg.webp","title":"传当蔡英文副手被民进党吃豆腐？柯文哲：台北市长可以做比较多事","type":"doc","updateTime":"2019/04/21 15:20:00"},{"comments":"0","commentsUrl":"377a24c6-7a65-43e5-82b6-9034be209890","commentsall":"0","documentId":"377a24c6-7a65-43e5-82b6-9034be209890","id":"377a24c6-7a65-43e5-82b6-9034be209890","intro":"","link":{"type":"phvideo","url":"377a24c6-7a65-43e5-82b6-9034be209890","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=377a24c6-7a65-43e5-82b6-9034be209890"},"payload":"eyJkb2NpZCI6IjdtMXRIaE95RHNxIn0=","phvideo":{"channelName":"荣耀张大仙","columnid":"785301","filesize":"37800","length":206,"path":"172-176"},"recomToken":"b71fed75-c8ea-4b8c-b2de-57fe967e5943","reftype":"ai||videoCmppEditor|","showType":"1","simId":"clusterId_114857231","staticId":"video_377a24c6-7a65-43e5-82b6-9034be209890","style":{"backreason":["0_来源:荣耀张大仙","c_游戏","sc_手游"],"defaultreason":"0_不感兴趣","view":"bigimg"},"subscribe":{"cateid":"785301","catename":"荣耀张大仙","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w696_h392_q100_webp/img1.ugc.ifeng.com/newugc/20190420/16/wemedia/39a8443baf6592dc88211039310370970adb7115_size388_w640_h360.png.webp","title":"仙放送，大仙怒氪80级战令只为了6个紫星币，快救救孩子吧！","type":"phvideo","updateTime":""},{"adId":"iis_feather_13652_34996_1585896_1555898345002_lm1f4r","adPositionId":"10000100","appSource":"","icon":{"showIcon":1,"text":"广告"},"intro":"","link":{"async_click":["https://iis1.deliver.ifeng.com/c?p=Ttye4RoN6-JNZNWgtEKR9yoHPFTi44jfyqXsjEqbLKuZbK7_cc5nfWsp-AM6P3F-XUoOqDmzC9gwBXxcA6Zg15mvs2vfQnjUgqsdM_dGjEiZUh3m_gxMKw44AgnrC-ELW6LglJFGAEg6o7579ph72fS1ommDR5RpdfwzvkcR0_v6aIi4chNPYgEBobsvyGDDmmak3FFYNY8DkkdGrkd8T96TugUxuiX40OTJtkqn8PQtFzfSA_mAhG8QlkBrFNap"],"pagemonitor_close":"https://ua.deliver.ifeng.com/monitor/cm.gif?adid=13652&type=c&reqid=65c52b2bd0f9c0eb557c1195acebe6b8","pagemonitor_open":"https://ua.deliver.ifeng.com/monitor/cm.gif?adid=13652&type=o&reqid=65c52b2bd0f9c0eb557c1195acebe6b8","pvurl":["https://iis1.deliver.ifeng.com/ids/mnt/imp?args=SkF28c6-A3fYrWmLphRzk-fAVhAtuWqQZnbsevSECHrd0M7bP8Dk5zWAxJm7JKBtDtZvlMsMja_7cgt238cL4KTowU9leJeujyYCNC9OrrLAiqJGqKdaThdwJAy_StE8_CWLrDK7wZxBPaUUZfIhVXHzTFfjOji2gFvCcyWH82l1rPOIXfvZ6K6YpPU2fO6iMv-iZ0TsDsoy_SGmf-JU48VUZX5y4-k2NorilVvfolMo5-cyBXqZHRXGRJS-8MiTsqZtCilZsnNm1NTKi407nHXwvyS0uxwxaSs_0NrjILTt94XYUw1IrHejSs_wPXaGs4rt1VfIX1thqhEqja9f9ieiaVXjP4uUEwND31sc1gdzVNJcHySmbpvLGewbTtDf2kI90nol3Xf_FKzuFnOigtAoyXx9Ynp54jfH-C0W7_A97lDfDqgGZ0eAt680kKVTdawJ4ymXFSMrpkMhHn8FcON4lmJbG_pMh-2jDcsuseoHx_UV1yf__DZeSsefV0YaY4AzhDIWE8r9ZLeKARkWSQXU_HlpTArOcgNkNLz_A3eVDGo0P2o7HcNTSb3ayjY4VIGMbJFqZn5iZav5Ep3m0q4FtKJ7kp1seK7V3jlVo8PcgtYXEexPH4YRRbx7LBGZbbd9RK9DzvCJvA044D63eVzgojM05kByuBicZwAm9oJI_cDS_mKmA0BI_kSN_H5CGpLnby25-Q6KHni1JQaSHc8zmeuI6l8VLabmDLV9bJOMe9aDv7ncMDAztXLHGbmGEUZKKkCDyNL6yFXhwWJUHrh5lVx-dYov-ePvI233YuA_zVqQyEpgAGlj2pTYHSbNy2wO-Nklds7vMBv_r0D9KIKykUNgX5H00e7irS-81pM&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA","https://iis1.deliver.ifeng.com/i?p=MUHL0xdKQ_1GzJo7EQmiJkBuEV6uLEl4-ajEou308d3CvwvhZt55HyP2RkTtM85zBQ41219c1Hpoya0TEoPbImYon64BMjvKDiA64nMUXploJyl4gRlJi7hQQqavwaygEkFvpqueTpDdDUDGCC0kYcAxV4m_cxJPBknTleWd1Z7Q2zmAqhD3rQgO7oIEaLJqeDa9Z_Yo2NEZBgfNDYwDlaocX3FJDmYEhVjwZHoeHzJ-4t-bzXHYzxQgHocdoHy7wlTdQTurJQnVAzPvcUlCqw"],"type":"web","url":"https://iis1.deliver.ifeng.com/ids/mnt/cli?args=VprFUVgyKhxgYwlUh_CxVGtcyivq5_vFPhMV0T1fPpzt7FqwStnCTNI7GrrgYWc1oX_0CjlGRfOrQDu9G35dd7o8ASa_eZYD3kzuW5DekmzAiqJGqKdaThdwJAy_StE8qf9Q7i9rdQQhs3VU-2xpX5PueNA-DR3C5uFX__cVuYvEnW38eQU_FbQIguu2sleDMv-iZ0TsDsoy_SGmf-JU4_LQF3E54jgphn3bOG3izWmxx7o__YFME1LPBe9h8Q8tpREWIXjDrhlMkAo67NPHAy2qHcC3g8oOZk8hSIvTfEdIfdOQ9CeXGAq-ndPNoezRmE8wtbxp0c_uestshLO9NqXApPwHGy12LZlrvs-tJjumR89UAPSL7d9gGxfuyXgGaf-WPMIdkbQBkfWpluieN3UHOM1Hl1xgv7VlCe5_6qaLvNXIzuj9ToyVmth2uKenRXwqzokkzOWqI6gltGp_-RR06m-zRQ8UoHrIiktAPCW5Kur7A7Ma-XE4tXZQ5wkQOroogLLNzpmLb0Bt4ceGg8kaWzpWMzzPI21qj9p3-zBzAoVutIl_YyqPazGN_scsT_AkCjtZhHhrSE4YMrFkeW_aUXwHaHKslcvRgjARKE5KVPwlMnapL5WwyWHtkrxaln-y5i2TBKOdRZ-xU2GnAt2AxxSeEylihB04mOO9FM9J6YFi5ffykSOvXq1L1H72KLeWiRhSUxEtq6-H3VgaOg4HATywqv0UdhDd8GqTZgwwa2FCi8bLbkgg4XHnsU0VzUXQa6OHBeAYqg04_Is6j9q5CsMEbNkRgNn84UwTSyR-aAzGU7_oSIxoDnJt3jnMWWMPkZWpeAa6xUIgh0WZF17S-M2y7IyYR9Gt1DN47qQoY2a4i7qjI9b2XmWuatZB&jmp=p5zF6sMF-XReeKSQxW8QInW5ngEagR87oARjuWwVtDWuo1B_y4cnAOhjAgjzh6qR&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA","weburl":"https://iis1.deliver.ifeng.com/ids/mnt/cli?args=VprFUVgyKhxgYwlUh_CxVGtcyivq5_vFPhMV0T1fPpzt7FqwStnCTNI7GrrgYWc1oX_0CjlGRfOrQDu9G35dd7o8ASa_eZYD3kzuW5DekmzAiqJGqKdaThdwJAy_StE8qf9Q7i9rdQQhs3VU-2xpX5PueNA-DR3C5uFX__cVuYvEnW38eQU_FbQIguu2sleDMv-iZ0TsDsoy_SGmf-JU4_LQF3E54jgphn3bOG3izWmxx7o__YFME1LPBe9h8Q8tpREWIXjDrhlMkAo67NPHAy2qHcC3g8oOZk8hSIvTfEdIfdOQ9CeXGAq-ndPNoezRmE8wtbxp0c_uestshLO9NqXApPwHGy12LZlrvs-tJjumR89UAPSL7d9gGxfuyXgGaf-WPMIdkbQBkfWpluieN3UHOM1Hl1xgv7VlCe5_6qaLvNXIzuj9ToyVmth2uKenRXwqzokkzOWqI6gltGp_-RR06m-zRQ8UoHrIiktAPCW5Kur7A7Ma-XE4tXZQ5wkQOroogLLNzpmLb0Bt4ceGg8kaWzpWMzzPI21qj9p3-zBzAoVutIl_YyqPazGN_scsT_AkCjtZhHhrSE4YMrFkeW_aUXwHaHKslcvRgjARKE5KVPwlMnapL5WwyWHtkrxaln-y5i2TBKOdRZ-xU2GnAt2AxxSeEylihB04mOO9FM9J6YFi5ffykSOvXq1L1H72KLeWiRhSUxEtq6-H3VgaOg4HATywqv0UdhDd8GqTZgwwa2FCi8bLbkgg4XHnsU0VzUXQa6OHBeAYqg04_Is6j9q5CsMEbNkRgNn84UwTSyR-aAzGU7_oSIxoDnJt3jnMWWMPkZWpeAa6xUIgh0WZF17S-M2y7IyYR9Gt1DN47qQoY2a4i7qjI9b2XmWuatZB&jmp=p5zF6sMF-XReeKSQxW8QInW5ngEagR87oARjuWwVtDWuo1B_y4cnAOhjAgjzh6qR&auctionPrice=c_ZJjplxOQ9GKbV_hOjPKA"},"pid":"10000100","source":"野生海参","style":{"attribute":"广告","backreason":["不感兴趣","虚假广告","看过了","档次太低"],"defaultreason":"0_不感兴趣","view":"bigimg"},"thumbnail":"https://c1.ifengimg.com/feather/images/34996/2019/04/09/15547979715508909.jpg","title":"大连渔民告诉你一斤海参多少钱，中间商利润高的吓人","type":"advert"},{"comments":"75","commentsUrl":"ucms_7m3tved26ic","commentsall":"122","documentId":"ucms_7m3tved26ic","id":"ucms_7m3tved26ic","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m3tved26ic&channelKey=&category=&imId=183405596","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m3tved26ic"},"payload":"eyJkb2NpZCI6IjdtM3R2ZWQyNmljIn0=","recomToken":"59edf009-9c87-4529-a645-37690f461708","reftype":"ai|||","showType":"0","simId":"clusterId_115024611","source":"观察者网","staticId":"ucms_7m3tved26ic","style":{"backreason":["0_来源:观察者网"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"311993","catename":"观察者网","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy13_aiw582_aih384_webp/e0.ifengimg.com/09/2019/0421/722BF3158AE4D9B8DB25FD953D4A63EFD9A410A5_size32_w582_h398.jpeg.webp","title":"\u201c民主不能当饭吃\u201d被蔡英文\u201c挖坟\u201d批斗，郭台铭回应遭台网民辱骂 郭台铭这句话被蔡英文\u201c挖坟\u201d，发文反击又被骂惨","type":"doc","updateTime":"2019/04/21 23:09:46"},{"comments":"5","commentsUrl":"ucms_7m3uQXyGEGG","commentsall":"12","documentId":"ucms_7m3uQXyGEGG","id":"ucms_7m3uQXyGEGG","intro":"","link":{"openType":"1","type":"doc","url":"https://api.iclient.ifeng.com/getNewsDocs?aid=ucms_7m3uQXyGEGG&channelKey=&category=&imId=183405652","weburl":"https://share.iclient.ifeng.com/shareNews?ch=qd_sdk_dl1&aid=ucms_7m3uQXyGEGG"},"payload":"eyJkb2NpZCI6IjdtM3VRWHlHRUdHIn0=","recomToken":"129a71fa-59f4-46c4-b6f5-f01b88cb6fe9","reftype":"ai|||","showType":"0","simId":"clusterId_15024563","source":"台海网","staticId":"ucms_7m3uQXyGEGG","style":{"backreason":["0_来源:台海网"],"defaultreason":"0_不感兴趣","view":"titleimg"},"subscribe":{"cateid":"1224047","catename":"台海网","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w230_h152_q100_aix0_aiy0_aiw640_aih422_webp/e0.ifengimg.com/02/2019/0421/EA94B0548961018B49D40FDF8D79C6E0AA480483_size46_w640_h461.jpeg.webp","title":"最新民调：蔡赖2020党内初选对决呈现一面倒 赖清德完胜蔡英文！","type":"doc","updateTime":"2019/04/21 23:20:00"},{"comments":"2","commentsUrl":"6055835e-6e45-4992-a6d3-c56d4f06e095","commentsall":"3","documentId":"6055835e-6e45-4992-a6d3-c56d4f06e095","id":"6055835e-6e45-4992-a6d3-c56d4f06e095","intro":"","link":{"type":"phvideo","url":"6055835e-6e45-4992-a6d3-c56d4f06e095","weburl":"https://ishare.iclient.ifeng.com/news/shareNews?ch=qd_sdk_dl1&guid=6055835e-6e45-4992-a6d3-c56d4f06e095"},"payload":"eyJkb2NpZCI6IjEwMzY0ODUyMDMifQ==","phvideo":{"channelName":"小学数学园地","columnid":"582244","filesize":"7641","length":139,"path":"94-96"},"recomToken":"b3567273-967f-4d3b-aff5-f7d167bc8ee0","reftype":"ai||videoCmppSpider|","showType":"1","simId":"clusterId_110681544","staticId":"video_6055835e-6e45-4992-a6d3-c56d4f06e095","style":{"backreason":["0_来源:小学数学园地","c_教育","sc_中小学"],"defaultreason":"0_不感兴趣","view":"bigimg"},"subscribe":{"cateid":"582244","catename":"小学数学园地","type":"vampire"},"thumbnail":"https://d.ifengimg.com/w696_h392_q100_webp/e0.ifengimg.com/12/2019/0316/D38922481875F01D8B23DBA6C002ADA7E7386B92_size55_w955_h637.jpeg.webp","title":"小学数学五年级奥数 是否觉得盈亏问题难理解 学会用方程就简单了","type":"phvideo","updateTime":""}]
     * listId : SYLB10NEW_DOWN
     * showAdvert : 1
     * syRetainOldNew : -1
     * topsize : 0
     * totalPage : 1
     * type : list
     */
}
