package com.cnews.guji.smart.common.bean;

import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * |- 广场
 * ifeng.news
 *
 * @author JSYL-DCL
 */
public class SquareMainBean {

    /**
     * type : square_navigation
     * item : [{"id":"ifenglive","staticId":"ifenglive","type":"web","title":"风直播","link":{"type":"web","url":"https://izhibo.ifeng.com/home.html?webkit=1","title":"风直播"},"thumbnail":"http://p3.ifengimg.com/a/2019/0505/56af4accf16cfabsize13_w150_h150.png"},{"id":"ysh","staticId":"ysh","type":"web","title":"有声书","link":{"type":"web","url":"https://ios.yc.ifeng.com/fm-h5/index.html?webkit=1","title":"有声书"},"thumbnail":"http://p3.ifengimg.com/a/2019/0505/b058d30c32f190esize15_w150_h150.png"},{"id":"sole","staticId":"sole","type":"solecolumn","title":"原创","link":{"type":"solecolumn","url":"https://api.iclient.ifeng.com/soleColumnList?soleId=25031","title":"原创"},"thumbnail":"http://p3.ifengimg.com/a/2019/0505/3df6c0276a2e1cfsize14_w150_h150.png"},{"id":"zmt","staticId":"zmt","type":"newlist","title":"大风号","link":{"type":"newlist","url":"http://api.iclient.ifeng.com/ClientNews?id=VAMPIRE,FOCUSVAMPIRE,SECNAVVAMPIRE","title":"大风号"},"thumbnail":"http://p3.ifengimg.com/a/2019/0505/e4099fe25b34c80size13_w150_h150.png"},{"id":"novel","staticId":"novel","type":"web","title":"小说","link":{"type":"web","url":"http://fhxw.iyc.ifeng.com/?cid=70005&webkit=1","title":"小说"},"thumbnail":"http://p3.ifengimg.com/a/2019/0505/235b14e3d3dbaf7size13_w150_h150.png"}]
     * chConfig : {"titleIcon":"http://p3.ifengimg.com/a/2018/1227/c1b462dcd5422c6size2_w72_h72.png","link":{"type":"hotspot","url":"https://api.iclient.ifeng.com/hotSpotPolyList"},"desc":"热议"}
     */

    private String type;
    private ChConfigBean chConfig;
    private List<ItemBean> item;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ChConfigBean getChConfig() {
        return chConfig;
    }

    public void setChConfig(ChConfigBean chConfig) {
        this.chConfig = chConfig;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class ChConfigBean {
        /**
         * titleIcon : http://p3.ifengimg.com/a/2018/1227/c1b462dcd5422c6size2_w72_h72.png
         * link : {"type":"hotspot","url":"https://api.iclient.ifeng.com/hotSpotPolyList"}
         * desc : 热议
         */

        private String titleIcon;
        private LinkBean link;
        private String desc;

        public String getTitleIcon() {
            return titleIcon;
        }

        public void setTitleIcon(String titleIcon) {
            this.titleIcon = titleIcon;
        }

        public LinkBean getLink() {
            return link;
        }

        public void setLink(LinkBean link) {
            this.link = link;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static class LinkBean {
            /**
             * type : hotspot
             * url : https://api.iclient.ifeng.com/hotSpotPolyList
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
    }

    public static class ItemBean implements MultiItemEntity {
        //DOC
        public static final int SQUARE_TYPE_DOC_TITLEIMGBACK2 = 1;
        //视频大图
        public static final int SQUARE_TYPE_HOTSPOT_HOTSPOT2 = 2;
        //原创精品solecolumn
        public static final int SQUARE_TYPE_SOLECOLUMN_SOLECOLUMN= 3;
        //
        public static final int SQUARE_TYPE_PHVIDEO_BIGIMG = 4;

        /**
         * id : ifenglive
         * staticId : ifenglive
         * type : web
         * title : 风直播
         * link : {"type":"web","url":"https://izhibo.ifeng.com/home.html?webkit=1","title":"风直播"}
         * thumbnail : http://p3.ifengimg.com/a/2019/0505/56af4accf16cfabsize13_w150_h150.png
         */

        private String id;
        private LinkBeanX link;
        private String staticId;
        private String thumbnail;
        private String title;
        public String titleIcon;
        private String type;
        public String comments;
        public String commentsUrl;
        public String commentsall;
        public String documentId;
        public String intro;
        public String notShowDislikeReason;
        public String reftype;
        public String showType;
        public String source;
        public StyleBean style;
        public SubscribeBean subscribe;
        public String updateTime;
        public String navigationTitle;
        public String navigationIcon;
        public int itemType;
        public PhvideoBean phvideo;
        public String recomToken;
        public String simId;
        //子类列表
        public List<RelationBeanX> relation;


        public String styleType;
        public List<NewslistBean> newslist;


        @Override
        public int getItemType() {
            try {
                //DOC
                if (NewsSource.NAME_TYPE_DOC.equals(type)) {
                    if (style.view != null) {
                        if (NewsSource.NAME_VIEW_TITLEIMGBACK2.equals(style.view)) {
                            itemType = SQUARE_TYPE_DOC_TITLEIMGBACK2;
                        } else {
                            itemType = SQUARE_TYPE_DOC_TITLEIMGBACK2;
                        }
                    }
                }
                //phvideo
                else if (NewsSource.NAME_TYPE_PHVIDEO.equals(type)) {
                    if (NewsSource.NAME_VIEW_BIGIMG.equals(style.view)) {
                        itemType = SQUARE_TYPE_PHVIDEO_BIGIMG;
                    } else {
                        itemType = SQUARE_TYPE_PHVIDEO_BIGIMG;
                    }
                }
                //HOTSPOT
                else if (NewsSource.NAME_TYPE_HOTSPOT.equals(type)) {
                    if (NewsSource.NAME_VIEW_HOTSPOT2.equals(style.view)) {
                        itemType = SQUARE_TYPE_HOTSPOT_HOTSPOT2;
                    } else {
                        itemType = SQUARE_TYPE_HOTSPOT_HOTSPOT2;
                    }
                }
                //SOLECOLUMN
                else if (NewsSource.NAME_TYPE_SOLECOLUMN.equals(type)) {
                    if (NewsSource.NAME_VIEW_SOLECOLUMN.equals(style.view)) {
                        itemType = SQUARE_TYPE_SOLECOLUMN_SOLECOLUMN;
                    } else {
                        itemType = SQUARE_TYPE_SOLECOLUMN_SOLECOLUMN;
                    }
                } else {
                    return SQUARE_TYPE_DOC_TITLEIMGBACK2;
                }
            }catch (Exception e){
                e.printStackTrace();
                itemType = SQUARE_TYPE_DOC_TITLEIMGBACK2;
            }
            return itemType;
        }

        public static class NewslistBean {
            /**
             * documentId : cmpp_17007_728_62612_column
             * id : cmpp_17007_728_62612_column
             * link : {"type":"solenewslist","url":"https://api.iclient.ifeng.com/soleNewsList?columnId=Y21wcF8xNzAwN183MjhfNjI2MTJfY29sdW1u&columnName=%E5%87%B0%E5%AE%B6%E8%AF%84%E6%B5%8B","weburl":"https://share.iclient.ifeng.com/negative#/list?columnId=Y21wcF8xNzAwN183MjhfNjI2MTJfY29sdW1u&webkit=1&columnName=%E5%87%B0%E5%AE%B6%E8%AF%84%E6%B5%8B"}
             * staticId : cmpp_17007_728_62612_column
             * thumbnail : https://d.ifengimg.com/w336_h190_q100_webp/p0.ifengimg.com/2019_12/12D2F5C1E3F08920155F876F69D2D59043A789EC_w495_h269.png.webp
             * title : vivo X27：一部没有855我也愿意用的“旗舰”手机 | 凰家评测
             * type : web
             * wordThumbnail : https://p2.ifengimg.com/a/2018_51/856cf6c5d73dfb5_size21_w549_h309.png
             */

            private String documentId;
            private String id;
            private LinkBeanX link;
            private String staticId;
            private String thumbnail;
            private String title;
            private String type;
            private String wordThumbnail;

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

            public LinkBeanX getLink() {
                return link;
            }

            public void setLink(LinkBeanX link) {
                this.link = link;
            }

            public String getStaticId() {
                return staticId;
            }

            public void setStaticId(String staticId) {
                this.staticId = staticId;
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

            public String getWordThumbnail() {
                return wordThumbnail;
            }

            public void setWordThumbnail(String wordThumbnail) {
                this.wordThumbnail = wordThumbnail;
            }

            public static class LinkBeanX {
                /**
                 * type : solenewslist
                 * url : https://api.iclient.ifeng.com/soleNewsList?columnId=Y21wcF8xNzAwN183MjhfNjI2MTJfY29sdW1u&columnName=%E5%87%B0%E5%AE%B6%E8%AF%84%E6%B5%8B
                 * weburl : https://share.iclient.ifeng.com/negative#/list?columnId=Y21wcF8xNzAwN183MjhfNjI2MTJfY29sdW1u&webkit=1&columnName=%E5%87%B0%E5%AE%B6%E8%AF%84%E6%B5%8B
                 */

                private String type;
                private String url;
                private String weburl;

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

                public String getWeburl() {
                    return weburl;
                }

                public void setWeburl(String weburl) {
                    this.weburl = weburl;
                }
            }
        }

        public static class RelationBeanX {
            /**
             * documentId : hotspot_公司观察0606
             * id : hotspot_公司观察0606
             * link : {"type":"hotspotlist","url":"https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E5%85%AC%E5%8F%B8%E8%A7%82%E5%AF%9F0606"}
             * payload : eyJkb2NpZCI6IjduR0s3MDRKUjBpIn0=
             * pv : 2327
             * recomToken : 96097e42-f45b-4174-913a-06af49400334
             * reftype : ai|||
             * relation : [{"id":"ucms_7nGK704JR0i","title":"金诚集团80后董事长韦杰等21人被捕 股价3天暴跌85%"}]
             * staticId : hotspot_公司观察0606
             * style : {"view":"hotspotbigimg"}
             * thumbnail : https://d.ifengimg.com/w698_h392/p3.ifengimg.com/2019_23/A7D7E96561AAD71A62D448EACA2AD88091396795_w381_h280.jpg.webp
             * title : 公司观察0606
             * type : hotspotpoly
             * updateTime : 2019/06/05 18:04:27
             */

            public String documentId;
            public String id;
            public LinkBeanX link;
            public String payload;
            public String pv;
            public String recomToken;
            public String reftype;
            public String staticId;
            public StyleBeanX style;
            public String thumbnail;
            public String title;
            public String type;
            public String updateTime;
            public List<RelationBean> relation;

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


            public static class LinkBeanX {
                /**
                 * type : hotspotlist
                 * url : https://api.iclient.ifeng.com/hotSpotDetailList?eventName=%E5%85%AC%E5%8F%B8%E8%A7%82%E5%AF%9F0606
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

            public static class StyleBeanX {
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
                 * id : ucms_7nGK704JR0i
                 * title : 金诚集团80后董事长韦杰等21人被捕 股价3天暴跌85%
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

        public static class PhvideoBean {
            /**
             * channelName : 侃点历史
             * columnid : 1439826
             * filesize : 39764
             * length : 271
             */

            private String channelName;
            private String columnid;
            private String filesize;
            private int length;

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public String getColumnid() {
                return columnid;
            }

            public void setColumnid(String columnid) {
                this.columnid = columnid;
            }

            public String getFilesize() {
                return filesize;
            }

            public void setFilesize(String filesize) {
                this.filesize = filesize;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }
        }

        public static class StyleBean {
            /**
             * backreason : ["0_来源:华商报"]
             * defaultreason : 0_不感兴趣
             * view : titleimgback2
             */

            private String defaultreason;
            private String view;
            private List<String> backreason;

            public String getDefaultreason() {
                return defaultreason;
            }

            public void setDefaultreason(String defaultreason) {
                this.defaultreason = defaultreason;
            }

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public List<String> getBackreason() {
                return backreason;
            }

            public void setBackreason(List<String> backreason) {
                this.backreason = backreason;
            }
        }

        public static class SubscribeBean {
            /**
             * cateid : 华商报
             * catename : 华商报
             * type : source
             */

            private String cateid;
            private String catename;
            private String type;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStaticId() {
            return staticId;
        }

        public void setStaticId(String staticId) {
            this.staticId = staticId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public LinkBeanX getLink() {
            return link;
        }

        public void setLink(LinkBeanX link) {
            this.link = link;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public static class LinkBeanX {
            /**
             * type : web
             * url : https://izhibo.ifeng.com/home.html?webkit=1
             * title : 风直播
             */

            private String type;
            private String url;
            private String title;
            private String openType;
            private String weburl;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
