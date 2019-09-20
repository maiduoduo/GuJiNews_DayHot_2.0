package com.cnews.guji.smart.common.bean;

import java.util.List;

/**
 * author：JSYL-DCL
 * 更多热点
 */
public class SquareHotMoreBean {


    private ChConfigBean chConfig;
    private int expiredTime;
    private String listId;
    private int totalPage;
    private String type;
    private List<ItemBean> item;

    public ChConfigBean getChConfig() {
        return chConfig;
    }

    public void setChConfig(ChConfigBean chConfig) {
        this.chConfig = chConfig;
    }

    public int getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(int expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ChConfigBean {
        /**
         * chid : hot_out_id
         * chname : 热点聚焦
         * desc : 【热点聚焦】比尔盖茨谈犯的最大的错误
         * icon :
         * shareThumbnail : https://d.ifengimg.com/w300_h300/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp
         * thumbnail : https://d.ifengimg.com/w300_h300/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp
         * weburl : https://c2.m.ifeng.com/hotspot
         */

        private String chid;
        private String chname;
        private String desc;
        private String icon;
        private String shareThumbnail;
        private String thumbnail;
        private String weburl;

        public String getChid() {
            return chid;
        }

        public void setChid(String chid) {
            this.chid = chid;
        }

        public String getChname() {
            return chname;
        }

        public void setChname(String chname) {
            this.chname = chname;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getShareThumbnail() {
            return shareThumbnail;
        }

        public void setShareThumbnail(String shareThumbnail) {
            this.shareThumbnail = shareThumbnail;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getWeburl() {
            return weburl;
        }

        public void setWeburl(String weburl) {
            this.weburl = weburl;
        }
    }

    public static class ItemBean {
        /**
         * documentId : hotspot_比尔盖茨谈犯的最大的错误
         * id : hotspot_比尔盖茨谈犯的最大的错误
         * link : {"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%AF%94%E5%B0%94%E7%9B%96%E8%8C%A8%E8%B0%88%E7%8A%AF%E7%9A%84%E6%9C%80%E5%A4%A7%E7%9A%84%E9%94%99%E8%AF%AF&proid=ifengnews"}
         * payload : eyJkb2NpZCI6IjdubEVReVhRR1V5In0=
         * pv : 2348
         * recomToken : b183c74f-4512-4d2b-a8e4-818aa5699f32
         * reftype : ai|||
         * relation : [{"id":"ucms_7nlEQyXQGUy","title":"比尔·盖茨谈犯下的最大错误：让我损失4千亿美元"}]
         * staticId : hotspot_比尔盖茨谈犯的最大的错误
         * style : {"view":"hotspotbigimg"}
         * thumbnail : https://d.ifengimg.com/w698_h392/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp
         * title : 比尔盖茨谈犯的最大的错误
         * type : hotspotpoly
         * updateTime : 2019/06/24 08:58:52
         */

        private String documentId;
        private String id;
        private LinkBean link;
        private String payload;
        private String pv;
        private String recomToken;
        private String reftype;
        private String staticId;
        private StyleBean style;
        private String thumbnail;
        private String title;
        private String type;
        private String updateTime;
        private List<RelationBean> relation;

        public String getDocumentId() {
            return documentId;
        }

        public void setDocumentId(String documentId) {
            this.documentId = documentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public String getPv() {
            return pv;
        }

        public void setPv(String pv) {
            this.pv = pv;
        }

        public String getRecomToken() {
            return recomToken;
        }

        public void setRecomToken(String recomToken) {
            this.recomToken = recomToken;
        }

        public String getReftype() {
            return reftype;
        }

        public void setReftype(String reftype) {
            this.reftype = reftype;
        }

        public String getStaticId() {
            return staticId;
        }

        public void setStaticId(String staticId) {
            this.staticId = staticId;
        }

        public StyleBean getStyle() {
            return style;
        }

        public void setStyle(StyleBean style) {
            this.style = style;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<RelationBean> getRelation() {
            return relation;
        }

        public void setRelation(List<RelationBean> relation) {
            this.relation = relation;
        }

        public static class LinkBean {
            /**
             * type : hotspotlist
             * url : https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%AF%94%E5%B0%94%E7%9B%96%E8%8C%A8%E8%B0%88%E7%8A%AF%E7%9A%84%E6%9C%80%E5%A4%A7%E7%9A%84%E9%94%99%E8%AF%AF&proid=ifengnews
             */

            private String type;
            private String url;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class StyleBean {
            /**
             * view : hotspotbigimg
             */

            private String view;

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }
        }

        public static class RelationBean {
            /**
             * id : ucms_7nlEQyXQGUy
             * title : 比尔·盖茨谈犯下的最大错误：让我损失4千亿美元
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }


    /**
     * chConfig : {"chid":"hot_out_id","chname":"热点聚焦","desc":"【热点聚焦】比尔盖茨谈犯的最大的错误","icon":"","shareThumbnail":"https://d.ifengimg.com/w300_h300/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp","thumbnail":"https://d.ifengimg.com/w300_h300/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp","weburl":"https://c2.m.ifeng.com/hotspot"}
     * expiredTime : 180000
     * item : [{"documentId":"hotspot_比尔盖茨谈犯的最大的错误","id":"hotspot_比尔盖茨谈犯的最大的错误","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%AF%94%E5%B0%94%E7%9B%96%E8%8C%A8%E8%B0%88%E7%8A%AF%E7%9A%84%E6%9C%80%E5%A4%A7%E7%9A%84%E9%94%99%E8%AF%AF&proid=ifengnews"},"payload":"eyJkb2NpZCI6IjdubEVReVhRR1V5In0=","pv":"2348","recomToken":"b183c74f-4512-4d2b-a8e4-818aa5699f32","reftype":"ai|||","relation":[{"id":"ucms_7nlEQyXQGUy","title":"比尔·盖茨谈犯下的最大错误：让我损失4千亿美元"}],"staticId":"hotspot_比尔盖茨谈犯的最大的错误","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p1.ifengimg.com/2019_26/D9578AE8895D94CEC1EC204FCCE40F43CB7B4FB5_w480_h341.jpg.webp","title":"比尔盖茨谈犯的最大的错误","type":"hotspotpoly","updateTime":"2019/06/24 08:58:52"},{"documentId":"hotspot_华泰汽车三大基地全面停产","id":"hotspot_华泰汽车三大基地全面停产","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E5%8D%8E%E6%B3%B0%E6%B1%BD%E8%BD%A6%E4%B8%89%E5%A4%A7%E5%9F%BA%E5%9C%B0%E5%85%A8%E9%9D%A2%E5%81%9C%E4%BA%A7&proid=ifengnews"},"payload":"eyJkb2NpZCI6IjdubDhSeWhiY1pjIn0=","pv":"5639","recomToken":"7775ee00-ce95-4d45-9b4a-a3a3f07b2d4f","reftype":"ai|||","relation":[{"id":"ucms_7nl8RyhbcZc","title":"华泰汽车三大基地全面停产：欠薪已超700万"}],"staticId":"hotspot_华泰汽车三大基地全面停产","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p0.ifengimg.com/2019_26/22E92A14AA80D41E9A8B857D955FA880D08F3FD9_w550_h367.jpg.webp","title":"华泰汽车三大基地全面停产","type":"hotspotpoly","updateTime":"2019/06/24 07:36:20"},{"documentId":"hotspot_美伊冲突升级","id":"hotspot_美伊冲突升级","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E7%BE%8E%E4%BC%8A%E5%86%B2%E7%AA%81%E5%8D%87%E7%BA%A7&proid=ifengnews"},"payload":"eyJkb2NpZCI6IjdubDI2Q3lRakRzIn0=","pv":"6652","recomToken":"41ee19a1-c7b2-4ca9-aa2d-cb5c8d14eb72","reftype":"ai|||","relation":[{"id":"ucms_7nl26CyQjDs","title":"美国安顾问警告伊朗：别把美国的谨慎误作软弱"}],"staticId":"hotspot_美伊冲突升级","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p3.ifengimg.com/2019_26/B98DF273797CAC3E2E3C50048C3DEDF0482714AE_w720_h423.jpg.webp","title":"美伊冲突升级","type":"hotspotpoly","updateTime":"2019/06/24 06:12:07"},{"documentId":"hotspot_柬埔寨在建建筑倒塌","id":"hotspot_柬埔寨在建建筑倒塌","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%9F%AC%E5%9F%94%E5%AF%A8%E5%9C%A8%E5%BB%BA%E5%BB%BA%E7%AD%91%E5%80%92%E5%A1%8C&proid=ifengnews"},"payload":"eyJkb2NpZCI6Ijdua1VVZk9QSE5JIn0=","pv":"5504","recomToken":"1cca3607-9678-4266-823c-6658098d69c8","reftype":"ai|||","relation":[{"id":"ucms_7nkUUfOPHNI","title":"柬埔寨七层在建建筑倒塌已致18死 涉事中国公民被控制"}],"staticId":"hotspot_柬埔寨在建建筑倒塌","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p0.ifengimg.com/2019_26/40AA9DE1187218FF04A46B0CEB0D72C512357619_w230_h152.jpg.webp","title":"柬埔寨在建建筑倒塌","type":"hotspotpoly","updateTime":"2019/06/23 22:18:28"},{"documentId":"hotspot_西班牙引渡台湾诈骗犯","id":"hotspot_西班牙引渡台湾诈骗犯","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E8%A5%BF%E7%8F%AD%E7%89%99%E5%BC%95%E6%B8%A1%E5%8F%B0%E6%B9%BE%E8%AF%88%E9%AA%97%E7%8A%AF&proid=ifengnews"},"payload":"eyJkb2NpZCI6Ijdua1BlYzJrZFVDIn0=","pv":"6784","recomToken":"10397881-296a-4e86-9a75-1acb407c29ca","reftype":"ai|||","relation":[{"id":"ucms_7nkPec2kdUC","title":"从西班牙引渡台湾诈骗犯有多难：男警察痛哭女翻译嫁人"}],"staticId":"hotspot_西班牙引渡台湾诈骗犯","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/e0.ifengimg.com/11/2019/0623/96B36FEBE5330AB049A38700B6E2495D28A3FE8B_size41_w520_h694.jpeg.webp","title":"西班牙引渡台湾诈骗犯","type":"hotspotpoly","updateTime":"2019/06/23 23:39:23"},{"documentId":"hotspot_苏宁易购收购家乐福","id":"hotspot_苏宁易购收购家乐福","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E8%8B%8F%E5%AE%81%E6%98%93%E8%B4%AD%E6%94%B6%E8%B4%AD%E5%AE%B6%E4%B9%90%E7%A6%8F&proid=ifengnews"},"payload":"eyJkb2NpZCI6Ijdua05IVjRSeTdNIn0=","pv":"9816","recomToken":"66fc68cc-4c44-4e3b-b8f1-c47fd2174c70","reftype":"ai|||","relation":[{"id":"ucms_7nkNHV4Ry7M","title":"48亿卖给苏宁！又一巨头\u201c败走\u201d中国 已\u201c资不抵债\u201d"}],"staticId":"hotspot_苏宁易购收购家乐福","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p0.ifengimg.com/2019_26/FCE832A3CE342520570915B4E53F94DECAE55054_w480_h300.jpg.webp","title":"苏宁易购收购家乐福","type":"hotspotpoly","updateTime":"2019/06/23 20:43:18"},{"documentId":"hotspot_圆通快递员当街怒砸奥迪","id":"hotspot_圆通快递员当街怒砸奥迪","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E5%9C%86%E9%80%9A%E5%BF%AB%E9%80%92%E5%91%98%E5%BD%93%E8%A1%97%E6%80%92%E7%A0%B8%E5%A5%A5%E8%BF%AA&proid=ifengnews"},"payload":"eyJkb2NpZCI6Ijdua0FlQ0YxeUdSIn0=","pv":"5252","recomToken":"0b92db35-f407-483b-ba85-c6587e5e7250","reftype":"ai|||","relation":[{"id":"ucms_7nkAeCF1yGR","title":"汉中圆通快递小哥怒砸奥迪SUV被刑拘，为何不值得同情？"}],"staticId":"hotspot_圆通快递员当街怒砸奥迪","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p1.ifengimg.com/2019_26/D05C7610FBE56B292BADD4C51A7FA0F6A9E908E1_w900_h600.jpg.webp","title":"圆通快递员当街怒砸奥迪","type":"hotspotpoly","updateTime":"2019/06/23 18:00:28"},{"documentId":"hotspot_湖南操场埋尸案突破性进展","id":"hotspot_湖南操场埋尸案突破性进展","link":{"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E6%B9%96%E5%8D%97%E6%93%8D%E5%9C%BA%E5%9F%8B%E5%B0%B8%E6%A1%88%E7%AA%81%E7%A0%B4%E6%80%A7%E8%BF%9B%E5%B1%95&proid=ifengnews"},"payload":"eyJkb2NpZCI6IjduanBKdXNlcXYyIn0=","pv":"961","recomToken":"b04255c5-84b7-4fbb-a407-e44a78581cbb","reftype":"ai|||","relation":[{"id":"ucms_7njpJuseqv2","title":"湖南\u201c操场埋尸案\u201d原校长首度发声：在买菜 有问题问县里"}],"staticId":"hotspot_湖南操场埋尸案突破性进展","style":{"view":"hotspotbigimg"},"thumbnail":"https://d.ifengimg.com/w698_h392/p2.ifengimg.com/2019_26/13C61099272EA3B1A8B8E067ACB82CCB01501696_w455_h562.jpg.webp","title":"湖南操场埋尸案突破性进展","type":"hotspotpoly","updateTime":"2019/06/23 12:42:28"}]
     * listId : hotspot
     * totalPage : 1
     * type : list
     */
}
