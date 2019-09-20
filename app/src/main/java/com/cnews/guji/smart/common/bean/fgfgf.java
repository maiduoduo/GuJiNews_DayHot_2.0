package com.cnews.guji.smart.common.bean;


import java.util.List;

/**
 * CN:      fgfgf
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/6
 * Des:    TODO:
 */
public class fgfgf {

    /**
     * activityList : [{"activityId":"188","allAwardMoney":"","awardStrategy":"2","backgroundImg":"http://imageugc1.pearvideo.com/activity/20190621/188-notitle-bg-232339.png","beginTime":"2019.6.27","endTime":"2019.7.4","name":"\u201c我为车咖\u201d新宝骏RS-5全国说车挑战赛","runStatus":"0"},{"activityId":"186","allAwardMoney":"","awardStrategy":"1","backgroundImg":"http://imageugc.pearvideo.com/activity/20190410/186-notitle-bg-115640.png","beginTime":"2019.4.12","endTime":"2019.7.26","name":"时代新人说-微视频大赛","runStatus":"0"},{"activityId":"184","allAwardMoney":"","awardStrategy":"1","backgroundImg":"http://imageugc2.pearvideo.com/activity/20190404/184-notitle-bg-112238.png","beginTime":"2019.4.16","endTime":"2019.7.31","name":"新中国70年，镇馆之宝70件","runStatus":"0"},{"activityId":"178","allAwardMoney":"奖励18700元","awardStrategy":"1","backgroundImg":"http://imageugc2.pearvideo.com/activity/20181217/178-notitle-bg-211423.png","beginTime":"2018.12.17","endTime":"2019.10.31","name":"中国人的信心从何而来","runStatus":"0"}]
     * nodeName : 全民拍客活动
     * nodeType : 12
     */

    private String nodeName;
    private String nodeType;
    private List<ActivityListBean> activityList;

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
}
