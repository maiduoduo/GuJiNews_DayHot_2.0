package com.cnews.guji.smart.common.bean;

/**
 * CN:      VideoShareBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/8/16
 * Des:    TODO:
 */
public class VideoShareBean {
    private VideoShareFriendBean friendBean;
    private VideoShareAppBean appBean;
    private VideoShareLinkBean linkBean;

    public static class VideoShareFriendBean {
        public Integer icoUrl;
        public String sharename;

        public VideoShareFriendBean(Integer icoUrl, String sharename) {
            this.icoUrl = icoUrl;
            this.sharename = sharename;
        }
    }

    public static class VideoShareAppBean {
        public Integer icoUrl;
        public String sharename;

        public VideoShareAppBean(Integer icoUrl, String sharename) {
            this.icoUrl = icoUrl;
            this.sharename = sharename;
        }
    }

    public static class VideoShareLinkBean {
        public Integer icoUrl;
        public String sharename;

        public VideoShareLinkBean(Integer icoUrl, String sharename) {
            this.icoUrl = icoUrl;
            this.sharename = sharename;
        }
    }
}
