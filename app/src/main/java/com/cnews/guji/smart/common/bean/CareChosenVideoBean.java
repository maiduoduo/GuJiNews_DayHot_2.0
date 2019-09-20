package com.cnews.guji.smart.common.bean;

import com.cnews.guji.smart.util.ILog;
import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * CN:      CareChosenVideoBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Des:   精选视频
 */
public class CareChosenVideoBean {
    private static final String TAG1 = "CareChosenVideoBean";
    private String nextUrl;
    private String reqId;
    private String resultCode;
    private String resultMsg;
    private String systemTime;
    private List<AreaListBean> areaList;
    private List<DataListBean> dataList;

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class AreaListBean {
        /**
         * area_id : 100001
         * expInfo : {"algorighm_exp_id":"","front_exp_id":"0","s_value":"b2746868-a438-4f85-8349-cbbcccf47940_20986770453363274"}
         */

        private String area_id;
        private ExpInfoBean expInfo;

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public ExpInfoBean getExpInfo() {
            return expInfo;
        }

        public void setExpInfo(ExpInfoBean expInfo) {
            this.expInfo = expInfo;
        }

        public static class ExpInfoBean {
            /**
             * algorighm_exp_id :
             * front_exp_id : 0
             * s_value : b2746868-a438-4f85-8349-cbbcccf47940_20986770453363274
             */

            private String algorighm_exp_id;
            private String front_exp_id;
            private String s_value;

            public String getAlgorighm_exp_id() {
                return algorighm_exp_id;
            }

            public void setAlgorighm_exp_id(String algorighm_exp_id) {
                this.algorighm_exp_id = algorighm_exp_id;
            }

            public String getFront_exp_id() {
                return front_exp_id;
            }

            public void setFront_exp_id(String front_exp_id) {
                this.front_exp_id = front_exp_id;
            }

            public String getS_value() {
                return s_value;
            }

            public void setS_value(String s_value) {
                this.s_value = s_value;
            }
        }
    }

    public static class DataListBean implements MultiItemEntity {
        //头条->轮播/纵向滚动新闻简讯
        public static final int TYPE_TOP_HEADEAR = 1;
        //横向视频列表
        public static final int TYPE_HORIZONTAL_VIDEOLIST = 4;
        //未知
        public static final int TYPE_NONE= 11;
        //图片标题横向列表
        public static final int TYPE_IMAGE_ALBUMLIST = 12;
        //大封面视频:cornerLabelDesc:广告->显示广告标志
        public static final int TYPE_VIDEO_BIGIMG= 13;


        @Override
        public int getItemType() {
            if ("12".equals(nodeType)){
                ILog.e(TAG1,"TYPE-------------------------:"+12);
                return TYPE_IMAGE_ALBUMLIST;
            } else if ("1".equals(nodeType)){
                ILog.e(TAG1,"TYPE-------------------------:"+1);
                return TYPE_TOP_HEADEAR;
            }else if ("4".equals(nodeType)){
                ILog.e(TAG1,"TYPE-------------------------:"+4);
                return TYPE_HORIZONTAL_VIDEOLIST;
            }else if ("11".equals(nodeType)){
                ILog.e(TAG1,"TYPE-------------------------:"+11);
                return TYPE_NONE;
            }else if ("13".equals(nodeType)){
                ILog.e(TAG1,"TYPE-------------------------:"+13);//1  11 13  4
                return TYPE_VIDEO_BIGIMG;
            }else {
                return TYPE_NONE;
            }
        }


        private DayHotInfoBean dayHotInfo;
        private String nodeName;
        private String nodeType;
        private String moreId;
        private List<ContListBeanX> contList;
        private List<ActivityListBean> activityList;

        public List<ActivityListBean> getActivityList() {
            return activityList;
        }

        public void setActivityList(List<ActivityListBean> activityList) {
            this.activityList = activityList;
        }

        public static class ActivityListBean {
            /**
             * activityId : 188
             * allAwardMoney :
             * awardStrategy : 2
             * backgroundImg : http://imageugc1.pearvideo.com/activity/20190621/188-notitle-bg-232339.png
             * beginTime : 2019.6.27
             * endTime : 2019.7.4
             * name : “我为车咖”新宝骏RS-5全国说车挑战赛
             * runStatus : 0
             */

            private String activityId;
            private String allAwardMoney;
            private String awardStrategy;
            private String backgroundImg;
            private String beginTime;
            private String endTime;
            private String name;
            private String runStatus;

            public String getActivityId() {
                return activityId;
            }

            public void setActivityId(String activityId) {
                this.activityId = activityId;
            }

            public String getAllAwardMoney() {
                return allAwardMoney;
            }

            public void setAllAwardMoney(String allAwardMoney) {
                this.allAwardMoney = allAwardMoney;
            }

            public String getAwardStrategy() {
                return awardStrategy;
            }

            public void setAwardStrategy(String awardStrategy) {
                this.awardStrategy = awardStrategy;
            }

            public String getBackgroundImg() {
                return backgroundImg;
            }

            public void setBackgroundImg(String backgroundImg) {
                this.backgroundImg = backgroundImg;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getRunStatus() {
                return runStatus;
            }

            public void setRunStatus(String runStatus) {
                this.runStatus = runStatus;
            }
        }

        public DayHotInfoBean getDayHotInfo() {
            return dayHotInfo;
        }

        public void setDayHotInfo(DayHotInfoBean dayHotInfo) {
            this.dayHotInfo = dayHotInfo;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getMoreId() {
            return moreId;
        }

        public void setMoreId(String moreId) {
            this.moreId = moreId;
        }

        public List<ContListBeanX> getContList() {
            return contList;
        }

        public void setContList(List<ContListBeanX> contList) {
            this.contList = contList;
        }


        public static class DayHotInfoBean {
            /**
             * barName : 24H热门
             * contList : [{"contId":"1572392","cornerLabel":"","cornerLabelDesc":"","name":"小伙花4万整成鼻畸形:感觉老十几岁"},{"contId":"1572369","cornerLabel":"","cornerLabelDesc":"","name":"外卖员往食物里吐痰?顾客:可验DNA"},{"contId":"1572334","cornerLabel":"","cornerLabelDesc":"","name":"日本捕鲸重启后，首头被捕鲸鱼画面"}]
             * lastUpdateTime : 09:00
             * unreadNum : 0
             */

            private String barName;
            private String lastUpdateTime;
            private String unreadNum;
            private List<ContListBean> contList;

            public String getBarName() {
                return barName;
            }

            public void setBarName(String barName) {
                this.barName = barName;
            }

            public String getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(String lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public String getUnreadNum() {
                return unreadNum;
            }

            public void setUnreadNum(String unreadNum) {
                this.unreadNum = unreadNum;
            }

            public List<ContListBean> getContList() {
                return contList;
            }

            public void setContList(List<ContListBean> contList) {
                this.contList = contList;
            }

            public static class ContListBean {
                /**
                 * contId : 1572392
                 * cornerLabel :
                 * cornerLabelDesc :
                 * name : 小伙花4万整成鼻畸形:感觉老十几岁
                 */

                private String contId;
                private String cornerLabel;
                private String cornerLabelDesc;
                private String name;

                public String getContId() {
                    return contId;
                }

                public void setContId(String contId) {
                    this.contId = contId;
                }

                public String getCornerLabel() {
                    return cornerLabel;
                }

                public void setCornerLabel(String cornerLabel) {
                    this.cornerLabel = cornerLabel;
                }

                public String getCornerLabelDesc() {
                    return cornerLabelDesc;
                }

                public void setCornerLabelDesc(String cornerLabelDesc) {
                    this.cornerLabelDesc = cornerLabelDesc;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class ContListBeanX {
            /**
             * adExpMonitorUrl :
             * contId : 1572316
             * cornerLabel :
             * cornerLabelDesc :
             * coverVideo : http://video.pearvideo.com/head/20190701/cont-1572316-14077391.mp4
             * duration : 04'00
             * forwordType : 1
             * geo : {"address":"","latitude":0,"loc":"0.0,0.0|中国,云南省,昆明市,五华区","longitude":0,"namePath":"中国,云南省,昆明市,五华区","placeName":"","showName":"拍客云南梨·发自云南省昆明市"}
             * isAppoint : 0
             * link : http://
             * linkType : 0
             * liveStartTime :
             * liveStatus :
             * name : 最新出炉!4分钟盘点2019年网络热词
             * pic : http://image2.pearvideo.com/cont/20190701/cont-1572316-12020378.png
             * praiseTimes : 638
             * pv : 0
             * tagInfo : {}
             * userInfo : {"level":"1","nickname":"云南梨","pic":"http://imageugc.pearvideo.com/user/20170912/10000118-125418.jpg","userId":"10000118"}
             * videoType : 1
             */

            private String adExpMonitorUrl;
            private String contId;
            private String cornerLabel;
            private String cornerLabelDesc;
            private String coverVideo;
            private String duration;
            private String forwordType;
            private GeoBean geo;
            private String isAppoint;
            private String link;
            private String linkType;
            private String liveStartTime;
            private String liveStatus;
            private String name;
            private String pic;
            private String praiseTimes;
            private String pv;
            private TagInfoBean tagInfo;
            private UserInfoBean userInfo;
            private String videoType;
            private String summary;
            private String commentTimes;

            public String getCommentTimes() {
                return commentTimes;
            }

            public void setCommentTimes(String commentTimes) {
                this.commentTimes = commentTimes;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getAdExpMonitorUrl() {
                return adExpMonitorUrl;
            }

            public void setAdExpMonitorUrl(String adExpMonitorUrl) {
                this.adExpMonitorUrl = adExpMonitorUrl;
            }

            public String getContId() {
                return contId;
            }

            public void setContId(String contId) {
                this.contId = contId;
            }

            public String getCornerLabel() {
                return cornerLabel;
            }

            public void setCornerLabel(String cornerLabel) {
                this.cornerLabel = cornerLabel;
            }

            public String getCornerLabelDesc() {
                return cornerLabelDesc;
            }

            public void setCornerLabelDesc(String cornerLabelDesc) {
                this.cornerLabelDesc = cornerLabelDesc;
            }

            public String getCoverVideo() {
                return coverVideo;
            }

            public void setCoverVideo(String coverVideo) {
                this.coverVideo = coverVideo;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getForwordType() {
                return forwordType;
            }

            public void setForwordType(String forwordType) {
                this.forwordType = forwordType;
            }

            public GeoBean getGeo() {
                return geo;
            }

            public void setGeo(GeoBean geo) {
                this.geo = geo;
            }

            public String getIsAppoint() {
                return isAppoint;
            }

            public void setIsAppoint(String isAppoint) {
                this.isAppoint = isAppoint;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getLinkType() {
                return linkType;
            }

            public void setLinkType(String linkType) {
                this.linkType = linkType;
            }

            public String getLiveStartTime() {
                return liveStartTime;
            }

            public void setLiveStartTime(String liveStartTime) {
                this.liveStartTime = liveStartTime;
            }

            public String getLiveStatus() {
                return liveStatus;
            }

            public void setLiveStatus(String liveStatus) {
                this.liveStatus = liveStatus;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPraiseTimes() {
                return praiseTimes;
            }

            public void setPraiseTimes(String praiseTimes) {
                this.praiseTimes = praiseTimes;
            }

            public String getPv() {
                return pv;
            }

            public void setPv(String pv) {
                this.pv = pv;
            }

            public TagInfoBean getTagInfo() {
                return tagInfo;
            }

            public void setTagInfo(TagInfoBean tagInfo) {
                this.tagInfo = tagInfo;
            }

            public UserInfoBean getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(UserInfoBean userInfo) {
                this.userInfo = userInfo;
            }

            public String getVideoType() {
                return videoType;
            }

            public void setVideoType(String videoType) {
                this.videoType = videoType;
            }

            public static class GeoBean {
                    private String namePath;
                    private String showName;
                    private String address;
                    private String loc;
                    private String placeName;
                    private double longitude;
                    private double latitude;
                    public void setNamePath(String namePath) {
                        this.namePath = namePath;
                    }
                    public String getNamePath() {
                        return namePath;
                    }

                    public void setShowName(String showName) {
                        this.showName = showName;
                    }
                    public String getShowName() {
                        return showName;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }
                    public String getAddress() {
                        return address;
                    }

                    public void setLoc(String loc) {
                        this.loc = loc;
                    }
                    public String getLoc() {
                        return loc;
                    }

                    public void setPlaceName(String placeName) {
                        this.placeName = placeName;
                    }
                    public String getPlaceName() {
                        return placeName;
                    }

                    public void setLongitude(double longitude) {
                        this.longitude = longitude;
                    }
                    public double getLongitude() {
                        return longitude;
                    }

                    public void setLatitude(double latitude) {
                        this.latitude = latitude;
                    }
                    public double getLatitude() {
                        return latitude;
                    }
            }

            public static class TagInfoBean {
            }

            public static class UserInfoBean {
                /**
                 * level : 1
                 * nickname : 云南梨
                 * pic : http://imageugc.pearvideo.com/user/20170912/10000118-125418.jpg
                 * userId : 10000118
                 */

                private String level;
                private String nickname;
                private String pic;
                private String userId;

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }
            }
        }

        /**
         * contList : [{"adExpMonitorUrl":"","contId":"1572316","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1572316-14077391.mp4","duration":"04'00","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,云南省,昆明市,五华区","longitude":0,"namePath":"中国,云南省,昆明市,五华区","placeName":"","showName":"拍客云南梨·发自云南省昆明市"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"最新出炉!4分钟盘点2019年网络热词","pic":"http://image2.pearvideo.com/cont/20190701/cont-1572316-12020378.png","praiseTimes":"638","pv":"0","tagInfo":{},"userInfo":{"level":"1","nickname":"云南梨","pic":"http://imageugc.pearvideo.com/user/20170912/10000118-125418.jpg","userId":"10000118"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1572295","cornerLabel":"3","cornerLabelDesc":"独播","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1572295-14077415.mp4","duration":"02'07","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,江苏省,镇江市,丹阳市","longitude":0,"namePath":"中国,江苏省,镇江市,丹阳市","placeName":"","showName":"拍客大大大太阳☀·发自江苏省镇江市"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"这个眼镜城，承包了全国80%的镜片","pic":"http://image1.pearvideo.com/cont/20190701/cont-1572295-12020389.png","praiseTimes":"693","pv":"0","tagInfo":{},"userInfo":{"level":"1","nickname":"大大大太阳☀","pic":"http://imageugc.pearvideo.com/user/20180301/11645086-144054-864072031997012.jpg","userId":"11645086"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1571968","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190630/cont-1571968-14074090.mp4","duration":"01'59","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"首日客流30万+,网红地标又出MV","pic":"http://image1.pearvideo.com/cont/20190630/cont-1571968-12018910.jpg","praiseTimes":"2512","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"品牌","pic":"http://image.pearvideo.com/node/140-10050957-logo.jpg ","userId":"11549140"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1569881","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1569881-14076931.mp4","duration":"04'10","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|新西兰","longitude":0,"namePath":"新西兰","placeName":"坎特伯雷","showName":"发自新西兰坎特伯雷"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"警告！新西兰淡水百年内将无法饮用","pic":"http://image1.pearvideo.com/cont/20190701/cont-1569881-12020212.png","praiseTimes":"256","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"OMG!","pic":"http://image.pearvideo.com/node/35-10030502-logo.jpg","userId":"11549111"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1567048","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1567048-14076021.mp4","duration":"18'48","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"大V李永乐教你选择更安全的电动车","pic":"http://image.pearvideo.com/cont/20190701/cont-1567048-12019806.jpg","praiseTimes":"280","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"麻辣车评","pic":"http://image.pearvideo.com/node/17-10027898-logo.jpg","userId":"11549147"},"videoType":"1"}]
         * dayHotInfo : {"barName":"24H热门","contList":[{"contId":"1572392","cornerLabel":"","cornerLabelDesc":"","name":"小伙花4万整成鼻畸形:感觉老十几岁"},{"contId":"1572369","cornerLabel":"","cornerLabelDesc":"","name":"外卖员往食物里吐痰?顾客:可验DNA"},{"contId":"1572334","cornerLabel":"","cornerLabelDesc":"","name":"日本捕鲸重启后，首头被捕鲸鱼画面"}],"lastUpdateTime":"09:00","unreadNum":"0"}
         * nodeName : 头条
         * nodeType : 1
         * moreId : 103
         */

    }

    /**
     * areaList : [{"area_id":"100001","expInfo":{"algorighm_exp_id":"","front_exp_id":"0","s_value":"b2746868-a438-4f85-8349-cbbcccf47940_20986770453363274"}},{"area_id":"100042","expInfo":{"algorighm_exp_id":"","front_exp_id":"0","s_value":"b2746868-a438-4f85-8349-cbbcccf47940_20986770453363274"}}]
     * dataList : [{"contList":[{"adExpMonitorUrl":"","contId":"1572316","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1572316-14077391.mp4","duration":"04'00","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,云南省,昆明市,五华区","longitude":0,"namePath":"中国,云南省,昆明市,五华区","placeName":"","showName":"拍客云南梨·发自云南省昆明市"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"最新出炉!4分钟盘点2019年网络热词","pic":"http://image2.pearvideo.com/cont/20190701/cont-1572316-12020378.png","praiseTimes":"638","pv":"0","tagInfo":{},"userInfo":{"level":"1","nickname":"云南梨","pic":"http://imageugc.pearvideo.com/user/20170912/10000118-125418.jpg","userId":"10000118"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1572295","cornerLabel":"3","cornerLabelDesc":"独播","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1572295-14077415.mp4","duration":"02'07","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,江苏省,镇江市,丹阳市","longitude":0,"namePath":"中国,江苏省,镇江市,丹阳市","placeName":"","showName":"拍客大大大太阳☀·发自江苏省镇江市"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"这个眼镜城，承包了全国80%的镜片","pic":"http://image1.pearvideo.com/cont/20190701/cont-1572295-12020389.png","praiseTimes":"693","pv":"0","tagInfo":{},"userInfo":{"level":"1","nickname":"大大大太阳☀","pic":"http://imageugc.pearvideo.com/user/20180301/11645086-144054-864072031997012.jpg","userId":"11645086"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1571968","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190630/cont-1571968-14074090.mp4","duration":"01'59","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"首日客流30万+,网红地标又出MV","pic":"http://image1.pearvideo.com/cont/20190630/cont-1571968-12018910.jpg","praiseTimes":"2512","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"品牌","pic":"http://image.pearvideo.com/node/140-10050957-logo.jpg ","userId":"11549140"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1569881","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1569881-14076931.mp4","duration":"04'10","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|新西兰","longitude":0,"namePath":"新西兰","placeName":"坎特伯雷","showName":"发自新西兰坎特伯雷"},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"警告！新西兰淡水百年内将无法饮用","pic":"http://image1.pearvideo.com/cont/20190701/cont-1569881-12020212.png","praiseTimes":"256","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"OMG!","pic":"http://image.pearvideo.com/node/35-10030502-logo.jpg","userId":"11549111"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1567048","cornerLabel":"","cornerLabelDesc":"","coverVideo":"http://video.pearvideo.com/head/20190701/cont-1567048-14076021.mp4","duration":"18'48","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"大V李永乐教你选择更安全的电动车","pic":"http://image.pearvideo.com/cont/20190701/cont-1567048-12019806.jpg","praiseTimes":"280","pv":"0","tagInfo":{},"userInfo":{"level":"2","nickname":"麻辣车评","pic":"http://image.pearvideo.com/node/17-10027898-logo.jpg","userId":"11549147"},"videoType":"1"}],"dayHotInfo":{"barName":"24H热门","contList":[{"contId":"1572392","cornerLabel":"","cornerLabelDesc":"","name":"小伙花4万整成鼻畸形:感觉老十几岁"},{"contId":"1572369","cornerLabel":"","cornerLabelDesc":"","name":"外卖员往食物里吐痰?顾客:可验DNA"},{"contId":"1572334","cornerLabel":"","cornerLabelDesc":"","name":"日本捕鲸重启后，首头被捕鲸鱼画面"}],"lastUpdateTime":"09:00","unreadNum":"0"},"nodeName":"头条","nodeType":"1"},{"moreId":"103","nodeName":"","nodeType":"11"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"194","contId":"1546793","cornerLabel":"","cornerLabelDesc":"","duration":"02'30","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isDownload":"0","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"日本流行\u201c超迷你\u201d公寓，不足10平","pic":"http://image.pearvideo.com/cont/20190424/cont-1546793-11935944.jpg","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1525687&contId=1546793","postId":"1525687","praiseTimes":"3579","sharePic":"http://image1.pearvideo.com/cont/20190424/cont-1546793-11935945.jpg","shareUrl":"https://www.pearvideo.com/detail_1546793","summary":"在寸土寸金的东京\u201c超迷你\u201d公寓受到越来越多独居年轻人欢迎，入住率达99%。这种公寓一般不足10平，麻雀虽小五脏俱全，厨房独立卫浴阳台一应俱全。租金也比较nice，折合人民币不到5000元。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：日本","tagId":"1034"},{"name":"不想看：东京","tagId":"9069"}],"userInfo":{"isFollow":"0","level":"2","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","userId":"11549091"},"videoType":"1","videos":[{"desc":"","duration":"150","fileSize":"38740325","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546793-13841153_pkg-fhd.mp4","videoId":"13841213"},{"desc":"","duration":"150","fileSize":"6395109","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546793-13841153_pkg-ld.mp4","videoId":"13841212"},{"desc":"","duration":"150","fileSize":"19800439","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546793-13841153_pkg-hd.mp4","videoId":"13841211"},{"desc":"","duration":"150","fileSize":"10771131","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546793-13841153_pkg-sd.mp4","videoId":"13841210"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","contId":"1561752","cornerLabel":"3","cornerLabelDesc":"独播","duration":"00'59","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"高校600学子组方阵:为祖国庆生","pic":"http://image1.pearvideo.com/cont/20190602/cont-1561752-11984326.png","praiseTimes":"1468","pv":"0","userInfo":{"level":"2","nickname":"梨杭州","pic":"http://image.pearvideo.com/node/2435-10777838-logo.png","userId":"11549083"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1561170","cornerLabel":"3","cornerLabelDesc":"独播","duration":"01'13","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"最牛地理老师!用900学生摆中国地图","pic":"http://image2.pearvideo.com/cont/20190531/cont-1561170-11982386.png","praiseTimes":"1918","pv":"0","userInfo":{"level":"1","nickname":"马小军","pic":"http://imageugc.pearvideo.com/user/20181001/12191228-234615.jpg","userId":"12191228"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1562835","cornerLabel":"","cornerLabelDesc":"","duration":"03'00","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"东财万名师生同唱《我和我的祖国》","pic":"http://image1.pearvideo.com/main/20190605/13236954-160158-1.png","praiseTimes":"741","pv":"0","userInfo":{"level":"2","nickname":"@雷锋","pic":"http://image.pearvideo.com/node/3392-11866684-logo.png","userId":"13236954"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1557265","cornerLabel":"","cornerLabelDesc":"","duration":"01'48","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"母女缝制国旗，网友接力带旗看祖国","pic":"http://image.pearvideo.com/cont/20190521/cont-1557265-11969982.png","praiseTimes":"750","pv":"0","userInfo":{"level":"2","nickname":"梨南京","pic":"http://image.pearvideo.com/node/2433-10777841-logo.png","userId":"11549082"},"videoType":"1"}],"moreId":"100475","nodeName":"我们的70年","nodeType":"4"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"68","contId":"1541732","cornerLabel":"","cornerLabelDesc":"","duration":"01'23","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,湖北省,武汉市","longitude":0,"namePath":"中国,湖北省,武汉市","placeName":"","showName":"拍客我妈最棒·发自湖北省武汉市"},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"男子送快递7年,同事发现他竟是富豪","pic":"http://image2.pearvideo.com/cont/20190412/cont-1541732-11918543.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1520478&contId=1541732","postId":"1520478","praiseTimes":"4798","sharePic":"http://image.pearvideo.com/cont/20190412/cont-1541732-11918544.png","shareUrl":"https://www.pearvideo.com/detail_1541732","summary":"湖北武汉，柯大勇做了7年的快递员，获得过该物流最\u201c五星配送员\u201d的称号。但是他同事介绍，他还是一位非常低调的富豪，同事去他家才知道他住小别墅。柯大勇说，他年轻时做生意赚了些钱，现在做快递小哥觉得蛮好。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：湖北","tagId":"1014"},{"name":"不想看：武汉","tagId":"1603"}],"userInfo":{"isFollow":"0","level":"1","nickname":"我妈最棒","pic":"http://imageugc.pearvideo.com/user/20180329/11858478-190856.png","userId":"11858478"},"videoType":"1","videos":[{"desc":"","duration":"83","fileSize":"12238158","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541732-13795558_pkg-hd.mp4","videoId":"13795600"},{"desc":"","duration":"83","fileSize":"6738573","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541732-13795558_pkg-sd.mp4 ","videoId":"13795599"},{"desc":"","duration":"83","fileSize":"3965417","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541732-13795558_pkg-ld.mp4","videoId":"13795598"},{"desc":"","duration":"83","fileSize":"24717990","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541732-13795558_pkg-fhd.mp4","videoId":"13795597"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"86","contId":"1563119","cornerLabel":"3","cornerLabelDesc":"独播","duration":"02'18","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,黑龙江省,哈尔滨市,道里区","longitude":0,"namePath":"中国,黑龙江省,哈尔滨市,道里区","placeName":"","showName":"拍客wilson·发自黑龙江省哈尔滨市"},"isDownload":"0","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"烤全牛4壮汉上阵,24小时出炉肉鲜嫩","pic":"http://image.pearvideo.com/cont/20190606/cont-1563119-11988633.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1540256&contId=1563119","postId":"1540256","praiseTimes":"2132","sharePic":"http://image1.pearvideo.com/cont/20190606/cont-1563119-11988634.png","shareUrl":"https://www.pearvideo.com/detail_1563119","summary":"黑龙江哈尔滨，宋玮酷爱烧烤，为了挑战自己烧烤技术， 他尝试用西方料理烧烤全牛。 300 多斤的整牛需要4位壮汉一起挪动， 为了让牛肉鲜嫩多汁， 他配用了两盆苹果酒， 经过24小时烘烤后， 上百食客同时享用。 ","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：美食","tagId":"709"},{"name":"不想看：黑龙江","tagId":"1090"}],"userInfo":{"isFollow":"0","level":"2","nickname":"淘宝吃货","pic":"http://image.pearvideo.com/node/3422-11898616-logo.png","userId":"12158197"},"videoType":"1","videos":[{"desc":"","duration":"138","fileSize":"54919307","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190606/cont-1563119-13987478_pkg-fhd.mp4","videoId":"13987520"},{"desc":"","duration":"138","fileSize":"29852776","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190606/cont-1563119-13987478_pkg-hd.mp4","videoId":"13987519"},{"desc":"","duration":"138","fileSize":"8793734","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190606/cont-1563119-13987478_pkg-ld.mp4","videoId":"13987518"},{"desc":"","duration":"138","fileSize":"15629361","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190606/cont-1563119-13987478_pkg-sd.mp4","videoId":"13987517"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"37","contId":"1556638","cornerLabel":"3","cornerLabelDesc":"独播","duration":"01'54","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,青海省,西宁市,城东区","longitude":0,"namePath":"中国,青海省,西宁市,城东区","placeName":"","showName":"拍客晴天小仓·发自青海省西宁市"},"isDownload":"0","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"青海炮仗面吓懵游客，开水锅里切面","pic":"http://image.pearvideo.com/cont/20190520/cont-1556638-11967984.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1539701&contId=1556638","postId":"1539701","praiseTimes":"1205","sharePic":"http://image1.pearvideo.com/cont/20190520/cont-1556638-11967985.png","shareUrl":"https://www.pearvideo.com/detail_1556638","summary":"炮仗，顾名思义就是爆竹，而青海西宁炮仗却是一种汤面，然而这个名字着实让外地游客吓了一跳。撒拉族小伙韩斌说，做炮仗的关键，是要在滚烫的开水锅里用刀切面，要切的很快，长短粗细均匀，让面的形状似炮仗。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想美食 ","tagId":"709"},{"name":"不想看：青海","tagId":"2367"}],"userInfo":{"isFollow":"0","level":"2","nickname":"淘宝吃货","pic":"http://image.pearvideo.com/node/3422-11898616-logo.png","userId":"12158197"},"videoType":"1","videos":[{"desc":"","duration":"114","fileSize":"20956031","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190520/cont-1556638-13927372_pkg-hd.mp4","videoId":"13927458"},{"desc":"","duration":"114","fileSize":"6547951","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190520/cont-1556638-13927372_pkg-ld.mp4","videoId":"13927457"},{"desc":"","duration":"114","fileSize":"41418177","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190520/cont-1556638-13927372_pkg-fhd.mp4","videoId":"13927456"},{"desc":"","duration":"114","fileSize":"11308205","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190520/cont-1556638-13927372_pkg-sd.mp4","videoId":"13927455"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"1","contId":"1541966","cornerLabel":"","cornerLabelDesc":"","duration":"03'45","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"《歌手》落幕,杨坤:要\u201c颠覆\u201d歌曲","pic":"http://image.pearvideo.com/cont/20190412/cont-1541966-11919276.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1520915&contId=1541966","postId":"1520915","praiseTimes":"1466","sharePic":"http://image.pearvideo.com/cont/20190412/cont-1541966-11919279.png","shareUrl":"https://www.pearvideo.com/detail_1541966","summary":"《歌王之战》落幕，\u201c四冠王\u201d杨坤再次惊艳全场。赛前杨坤接受梨视频专访称，自己在本次《歌手》中改编是最多的，这对歌曲是一种\u201c颠覆\u201d。他说观众也希望看到更多的\u201c颠覆\u201d。他还跟网友大方分享自己的健身经验。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：歌曲","tagId":"1562"},{"name":"不想看：歌手","tagId":"2112"}],"userInfo":{"isFollow":"0","level":"2","nickname":"梨北京","pic":"http://image.pearvideo.com/node/2370-10760244-logo.png","userId":"11549145"},"videoType":"1","videos":[{"desc":"","duration":"225","fileSize":"16699180","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541966-13797324_pkg-hd.mp4","videoId":"13797459"},{"desc":"","duration":"225","fileSize":"35313777","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541966-13797324_pkg-fhd.mp4","videoId":"13797458"},{"desc":"","duration":"225","fileSize":"6124040","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541966-13797324_pkg-ld.mp4","videoId":"13797457"},{"desc":"","duration":"225","fileSize":"9577058","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190412/cont-1541966-13797324_pkg-sd.mp4","videoId":"13797456"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","contId":"1567031","cornerLabel":"","cornerLabelDesc":"","duration":"02'47","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"90后小伙10辆自行车怀旧风迎亲","pic":"http://image1.pearvideo.com/cont/20190617/cont-1567031-12001612.jpg","praiseTimes":"1995","pv":"0","userInfo":{"level":"2","nickname":"微辣Video","pic":"http://image.pearvideo.com/node/9-10027910-logo.jpg","userId":"11549090"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1557817","cornerLabel":"","cornerLabelDesc":"","duration":"02'22","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"法国妹纸花露水当香水：像爱马仕","pic":"http://image1.pearvideo.com/cont/20190523/cont-1557817-11971876.jpg","praiseTimes":"1767","pv":"0","userInfo":{"level":"2","nickname":"微辣Video","pic":"http://image.pearvideo.com/node/9-10027910-logo.jpg","userId":"11549090"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1553596","cornerLabel":"","cornerLabelDesc":"","duration":"02'17","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"90岁奶奶用雪花膏70年肤似少女","pic":"http://image2.pearvideo.com/cont/20190513/cont-1553596-11958578.jpg","praiseTimes":"2374","pv":"0","userInfo":{"level":"2","nickname":"微辣Video","pic":"http://image.pearvideo.com/node/9-10027910-logo.jpg","userId":"11549090"},"videoType":"1"},{"adExpMonitorUrl":"","contId":"1552268","cornerLabel":"","cornerLabelDesc":"","duration":"01'26","forwordType":"1","isAppoint":"0","link":"http://","linkType":"0","liveStartTime":"","liveStatus":"","name":"拼多多启动上海老字号新电商计划","pic":"http://image2.pearvideo.com/cont/20190509/cont-1552268-11954096.jpg","praiseTimes":"1179","pv":"0","userInfo":{"level":"2","nickname":"微辣Video","pic":"http://image.pearvideo.com/node/9-10027910-logo.jpg","userId":"11549090"},"videoType":"1"}],"moreId":"102632","nodeName":"老拼呃，老字号新故事","nodeType":"4"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"1","commentTimes":"38","contId":"1550251","cornerLabel":"3","cornerLabelDesc":"独播","duration":"01'53","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,河北省,石家庄市,正定县","longitude":0,"namePath":"中国,河北省,石家庄市,正定县","placeName":"","showName":"拍客梨北河·发自河北省石家庄市"},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"车主曝新买奥迪才6天,高速刹车失效","pic":"http://image2.pearvideo.com/cont/20190503/cont-1550251-11947400.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1528314&contId=1550251","postId":"1528314","praiseTimes":"810","sharePic":"http://image.pearvideo.com/cont/20190503/cont-1550251-11947401.png","shareUrl":"https://www.pearvideo.com/detail_1550251","summary":"河北石家庄，车主杨先生称新买的奥迪A8才第6天，在高速上刹车失效，4S店检测出左侧马达制动故障。杨先生表示希望退换车遭到厂家拒绝。4S店称，之前车主同意维修，奔驰事件发生后车主要求赔偿，目前正在协商。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：河北","tagId":"1093"},{"name":"不想看：维修","tagId":"4148"}],"userInfo":{"isFollow":"0","level":"1","nickname":"梨北河","pic":"http://imageugc.pearvideo.com/user/20180717/11671142-225621-861742034658190.jpg","userId":"11671142"},"videoType":"1","videos":[{"desc":"","duration":"113","fileSize":"8062046","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190503/cont-1550251-13871917_pkg-sd.mp4","videoId":"13871947"},{"desc":"","duration":"113","fileSize":"26883645","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190503/cont-1550251-13871917_pkg-fhd.mp4","videoId":"13871946"},{"desc":"","duration":"113","fileSize":"4876795","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190503/cont-1550251-13871917_pkg-ld.mp4","videoId":"13871945"},{"desc":"","duration":"113","fileSize":"14279453","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190503/cont-1550251-13871917_pkg-hd.mp4","videoId":"13871944"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"44","contId":"1546908","cornerLabel":"","cornerLabelDesc":"","duration":"01'10","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国","longitude":0,"namePath":"中国","placeName":"","showName":""},"isDownload":"0","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"BBC纪录片心碎一幕：猩猩挡推土机","pic":"http://image2.pearvideo.com/cont/20190424/cont-1546908-11936324.jpg","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1525113&contId=1546908","postId":"1525113","praiseTimes":"3024","sharePic":"http://image.pearvideo.com/cont/20190424/cont-1546908-11936325.jpg","shareUrl":"https://www.pearvideo.com/detail_1546908","summary":"BBC近日的纪录片《气候变化：事实真相》出现最令人心碎一幕：一只红毛猩猩爬上被伐倒的大树，徒劳地挥拳与摧毁自己家园的推土机搏斗。网友对这一幕表示\u201c心碎\u201d、\u201c恶心\u201d。砍伐雨林是为了种植利润较高的棕榈树。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：红毛猩猩","tagId":"2352"},{"name":"不想看：雨林","tagId":"26550"}],"userInfo":{"isFollow":"0","level":"2","nickname":"眼镜儿","pic":"http://image.pearvideo.com/node/19-10027896-logo.jpg","userId":"11549091"},"videoType":"1","videos":[{"desc":"","duration":"70","fileSize":"11037515","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546908-13842090_pkg-hd.mp4","videoId":"13842141"},{"desc":"","duration":"70","fileSize":"3674180","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546908-13842090_pkg-ld.mp4","videoId":"13842140"},{"desc":"","duration":"70","fileSize":"20083862","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546908-13842090_pkg-fhd.mp4","videoId":"13842139"},{"desc":"","duration":"70","fileSize":"6246021","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190424/cont-1546908-13842090_pkg-sd.mp4","videoId":"13842138"}]}],"nodeType":"13"},{"moreId":"84","nodeName":"","nodeType":"11"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"1","commentTimes":"84","contId":"1557595","cornerLabel":"","cornerLabelDesc":"","duration":"00'39","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,黑龙江省,牡丹江市,爱民区","longitude":0,"namePath":"中国,黑龙江省,牡丹江市,爱民区","placeName":"","showName":"拍客梨黑龙江·发自黑龙江省牡丹江市"},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"他街头暴打纹身男，亮证自称武警","pic":"http://image.pearvideo.com/cont/20190522/cont-1557595-11971032.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1534948&contId=1557595","postId":"1534948","praiseTimes":"3637","sharePic":"http://image1.pearvideo.com/cont/20190522/cont-1557595-11971033.png","shareUrl":"https://www.pearvideo.com/detail_1557595","summary":"5月20日，黑龙江牡丹江市。两名男子街头打架，蓝衣男子骑在纹身男子身上，手举证件称自己是\u201c武警\u201d。警方介入后了解到，该男子是一名精神疾病患者，并非武警或武警退役人员，举起的证件是精神疾病三级残疾证。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：黑龙江","tagId":"1090"},{"name":"不想看：打架","tagId":"1185"}],"userInfo":{"isFollow":"0","level":"1","nickname":"梨黑龙江","pic":"http://imageugc.pearvideo.com/user/20180216/10108005-161559.jpg","userId":"10108005"},"videoType":"1","videos":[{"desc":"","duration":"39","fileSize":"7445154","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190522/cont-1557595-13936279_pkg-hd.mp4","videoId":"13936311"},{"desc":"","duration":"39","fileSize":"2470863","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190522/cont-1557595-13936279_pkg-ld.mp4","videoId":"13936310"},{"desc":"","duration":"39","fileSize":"14332577","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190522/cont-1557595-13936279_pkg-fhd.mp4","videoId":"13936309"},{"desc":"","duration":"39","fileSize":"4207814","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190522/cont-1557595-13936279_pkg-sd.mp4","videoId":"13936308"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"60","contId":"1565947","cornerLabel":"","cornerLabelDesc":"","duration":"02'15","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,江苏省,南京市,栖霞区","longitude":0,"namePath":"中国,江苏省,南京市,栖霞区","placeName":"","showName":"拍客倩女幽魂·发自江苏省南京市"},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"女孩家中衣柜惊现摄像头，正对床！","pic":"http://image.pearvideo.com/cont/20190613/cont-1565947-11998176.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1542426&contId=1565947","postId":"1542426","praiseTimes":"1376","sharePic":"http://image1.pearvideo.com/cont/20190613/cont-1565947-11998177.png","shareUrl":"https://www.pearvideo.com/detail_1565947","summary":"近日，江苏南京张小姐在家中衣柜里，发现一个正对卧室的摄像头，吓得不轻。民警调查发现，安装摄像头的竟然是张小姐的前男友李某，李某说自己太爱对方，被爱冲昏头脑，恋爱期间看到一个装修工跟女友暧昧，做出此举。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：南京","tagId":"1574"},{"name":"不想看：江苏","tagId":"1883"}],"userInfo":{"isFollow":"0","level":"1","nickname":"倩女幽魂","pic":"http://imageugc.pearvideo.com/user/20180716/11865875-131111.jpg","userId":"11865875"},"videoType":"1","videos":[{"desc":"","duration":"135","fileSize":"5089757","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190613/cont-1565947-14014849_pkg-ld.mp4","videoId":"14014927"},{"desc":"","duration":"135","fileSize":"8691313","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190613/cont-1565947-14014849_pkg-sd.mp4","videoId":"14014926"},{"desc":"","duration":"135","fileSize":"16806937","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190613/cont-1565947-14014849_pkg-hd.mp4","videoId":"14014925"},{"desc":"","duration":"135","fileSize":"35031767","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190613/cont-1565947-14014849_pkg-fhd.mp4","videoId":"14014924"}]}],"nodeType":"13"},{"contList":[{"adExpMonitorUrl":"","adLogo":"","adName":"","aspectRatio":"0","commentTimes":"6","contId":"1569425","cornerLabel":"3","cornerLabelDesc":"独播","duration":"01'20","forwordType":"1","geo":{"address":"","latitude":0,"loc":"0.0,0.0|中国,河北省,石家庄市,长安区","longitude":0,"namePath":"中国,河北省,石家庄市,长安区","placeName":"","showName":"拍客许愿树·发自河北省石家庄市"},"isDownload":"1","isFavorited":"0","isVideoPlus":"0","isVr":"0","link":"http://","linkType":"0","liveStatus":"","name":"他们醉酒干的事，醒后不敢看第二遍","pic":"http://image2.pearvideo.com/cont/20190623/cont-1569425-12009887.png","postHtml":"https://app.pearvideo.com/clt/page/v4/post.jsp?postId=1545619&contId = 1569425 ","postId":"1545619","praiseTimes":"994","sharePic":"http://image.pearvideo.com/cont/20190623/cont-1569425-12009888.png","shareUrl":"https://www.pearvideo.com/detail_1569425","summary":"进入夏季，喝酒撸串成了很多人的最爱，这也导致很多醉酒事件的发生。在此，我们剪辑了各地多起市民酒后需救助的报警：有醉汉倒在公厕蹲位上熟睡的，有骑车磕掉牙的。醉酒风险大，喝酒量力而行。","tags":[{"name":"内容质量差","tagId":"0"},{"name":"不想看：河北","tagId":"1093"},{"name":"不想看：醉酒","tagId":"1678"}],"userInfo":{"isFollow":"0","level":"1","nickname":"许愿树","pic":"http://imageugc.pearvideo.com/user/20190618/10945273-195558-867011034689090.jpg","userId":"10945273"},"videoType":"1","videos":[{"desc":"","duration":"80","fileSize":"3141854","format":"mp4","name":"","tag":"ld","url":"http://video.pearvideo.com/mp4/short/20190623/cont-1569425-14048446_pkg-ld.mp4","videoId":"14048477"},{"desc":"","duration":"80","fileSize":"9009521","format":"mp4","name":"","tag":"hd","url":"http://video.pearvideo.com/mp4/short/20190623/cont-1569425-14048446_pkg-hd.mp4","videoId":"14048476"},{"desc":"","duration":"80","fileSize":"16459446","format":"mp4","name":"","tag":"fhd","url":"http://video.pearvideo.com/mp4/short/20190623/cont-1569425-14048446_pkg-fhd.mp4","videoId":"14048475"},{"desc":"","duration":"80","fileSize":"5219692","format":"mp4","name":"","tag":"sd","url":"http://video.pearvideo.com/mp4/short/20190623/cont-1569425-14048446_pkg-sd.mp4","videoId":"14048474"}]}],"nodeType":"13"}]
     * nextUrl : http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=320100&start=10
     * reqId : b2746868-a438-4f85-8349-cbbcccf47940
     * resultCode : 1
     * resultMsg : success
     * systemTime : 1562030127059
     */

}
