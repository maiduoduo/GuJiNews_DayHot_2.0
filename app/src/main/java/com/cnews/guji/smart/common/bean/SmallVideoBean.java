package com.cnews.guji.smart.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.github.library.entity.MultiItemEntity;

import java.util.List;

/**
 * CN:      SmallVideoBean
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/26
 * Des:   小视频
 */
public class SmallVideoBean {

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable, MultiItemEntity {
        //小视频详情页
        //1.纵向视频：高度>宽度
        public static final int TYPE_SMALL_VIDEO_VERTICAL = 0;
        //2.横向视频：高度<宽度
        public static final int TYPE_SMALL_VIDEO_HORIZONTAL = 1;

        @Override
        public int getItemType() {
            try {
                //DOC
                if (Integer.parseInt(height) > Integer.parseInt(width)) {
                    itemType = TYPE_SMALL_VIDEO_VERTICAL;
                }else if (Integer.parseInt(width) > Integer.parseInt(height)){
                    itemType = TYPE_SMALL_VIDEO_HORIZONTAL;
                }
            } catch (Exception e) {
                e.printStackTrace();
                itemType = TYPE_SMALL_VIDEO_VERTICAL;
            }
            return itemType;
        }

        /**
         * bimageuri :
         * bookmark : 3
         * cache_version : 2
         * cai : 7
         * cdn_img : http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif
         * comment : 26
         * created_at : 2019-06-21 07:36:01
         * ding : 177
         * favourite : 3
         * hate : 7
         * height : 339
         * image0 : http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif
         * image1 : http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif
         * image2 : http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif
         * is_gif : false
         * love : 177
         * name : 天天趣图
         * original_pid : 0
         * passtime : 2019-06-21 07:36:01
         * profile_image : http://wimg.spriteapp.cn/profile/large/2018/07/31/5b60500856689_mini.jpg
         * repost : 5
         * screen_name : 天天趣图
         * status : 4
         * t : 1561073761
         * tag :
         * text : 头一次坐飞机，咱也不知道这是不是正常，咱也不敢问
         * theme_id : 58240
         * theme_name : 搞笑图片
         * theme_type : 1
         * type : 10
         * user_id : 19889419
         * videotime : 0
         * videouri :
         * width : 240
         * image_small : http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg
         * playcount : 4139
         * playfcount : 429
         */

        private int itemType;
        private String bimageuri;
        private String bookmark;
        private int cache_version;
        private String cai;
        private String cdn_img;
        private String comment;
        private String created_at;
        private String ding;
        private String favourite;
        private String hate;
        private String height;
        private String image0;
        private String image1;
        private String image2;
        private boolean is_gif;
        private String love;
        private String name;
        private String original_pid;
        private String passtime;
        private String profile_image;
        private String repost;
        private String screen_name;
        private String status;
        private int t;
        private String tag;
        private String text;
        private String theme_id;
        private String theme_name;
        private String theme_type;
        private String type;
        private String user_id;
        private int videotime;
        private String videouri;
        private String width;
        private String image_small;
        private String playcount;
        private String playfcount;

        public String getBimageuri() {
            return bimageuri;
        }

        public void setBimageuri(String bimageuri) {
            this.bimageuri = bimageuri;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public int getCache_version() {
            return cache_version;
        }

        public void setCache_version(int cache_version) {
            this.cache_version = cache_version;
        }

        public String getCai() {
            return cai;
        }

        public void setCai(String cai) {
            this.cai = cai;
        }

        public String getCdn_img() {
            return cdn_img;
        }

        public void setCdn_img(String cdn_img) {
            this.cdn_img = cdn_img;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getDing() {
            return ding;
        }

        public void setDing(String ding) {
            this.ding = ding;
        }

        public String getFavourite() {
            return favourite;
        }

        public void setFavourite(String favourite) {
            this.favourite = favourite;
        }

        public String getHate() {
            return hate;
        }

        public void setHate(String hate) {
            this.hate = hate;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getImage0() {
            return image0;
        }

        public void setImage0(String image0) {
            this.image0 = image0;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }

        public boolean isIs_gif() {
            return is_gif;
        }

        public void setIs_gif(boolean is_gif) {
            this.is_gif = is_gif;
        }

        public String getLove() {
            return love;
        }

        public void setLove(String love) {
            this.love = love;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginal_pid() {
            return original_pid;
        }

        public void setOriginal_pid(String original_pid) {
            this.original_pid = original_pid;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getRepost() {
            return repost;
        }

        public void setRepost(String repost) {
            this.repost = repost;
        }

        public String getScreen_name() {
            return screen_name;
        }

        public void setScreen_name(String screen_name) {
            this.screen_name = screen_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTheme_id() {
            return theme_id;
        }

        public void setTheme_id(String theme_id) {
            this.theme_id = theme_id;
        }

        public String getTheme_name() {
            return theme_name;
        }

        public void setTheme_name(String theme_name) {
            this.theme_name = theme_name;
        }

        public String getTheme_type() {
            return theme_type;
        }

        public void setTheme_type(String theme_type) {
            this.theme_type = theme_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getVideotime() {
            return videotime;
        }

        public void setVideotime(int videotime) {
            this.videotime = videotime;
        }

        public String getVideouri() {
            return videouri;
        }

        public void setVideouri(String videouri) {
            this.videouri = videouri;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getImage_small() {
            return image_small;
        }

        public void setImage_small(String image_small) {
            this.image_small = image_small;
        }

        public String getPlaycount() {
            return playcount;
        }

        public void setPlaycount(String playcount) {
            this.playcount = playcount;
        }

        public String getPlayfcount() {
            return playfcount;
        }

        public void setPlayfcount(String playfcount) {
            this.playfcount = playfcount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.bimageuri);
            dest.writeString(this.bookmark);
            dest.writeInt(this.cache_version);
            dest.writeString(this.cai);
            dest.writeString(this.cdn_img);
            dest.writeString(this.comment);
            dest.writeString(this.created_at);
            dest.writeString(this.ding);
            dest.writeString(this.favourite);
            dest.writeString(this.hate);
            dest.writeString(this.height);
            dest.writeString(this.image0);
            dest.writeString(this.image1);
            dest.writeString(this.image2);
            dest.writeByte(this.is_gif ? (byte) 1 : (byte) 0);
            dest.writeString(this.love);
            dest.writeString(this.name);
            dest.writeString(this.original_pid);
            dest.writeString(this.passtime);
            dest.writeString(this.profile_image);
            dest.writeString(this.repost);
            dest.writeString(this.screen_name);
            dest.writeString(this.status);
            dest.writeInt(this.t);
            dest.writeString(this.tag);
            dest.writeString(this.text);
            dest.writeString(this.theme_id);
            dest.writeString(this.theme_name);
            dest.writeString(this.theme_type);
            dest.writeString(this.type);
            dest.writeString(this.user_id);
            dest.writeInt(this.videotime);
            dest.writeString(this.videouri);
            dest.writeString(this.width);
            dest.writeString(this.image_small);
            dest.writeString(this.playcount);
            dest.writeString(this.playfcount);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.bimageuri = in.readString();
            this.bookmark = in.readString();
            this.cache_version = in.readInt();
            this.cai = in.readString();
            this.cdn_img = in.readString();
            this.comment = in.readString();
            this.created_at = in.readString();
            this.ding = in.readString();
            this.favourite = in.readString();
            this.hate = in.readString();
            this.height = in.readString();
            this.image0 = in.readString();
            this.image1 = in.readString();
            this.image2 = in.readString();
            this.is_gif = in.readByte() != 0;
            this.love = in.readString();
            this.name = in.readString();
            this.original_pid = in.readString();
            this.passtime = in.readString();
            this.profile_image = in.readString();
            this.repost = in.readString();
            this.screen_name = in.readString();
            this.status = in.readString();
            this.t = in.readInt();
            this.tag = in.readString();
            this.text = in.readString();
            this.theme_id = in.readString();
            this.theme_name = in.readString();
            this.theme_type = in.readString();
            this.type = in.readString();
            this.user_id = in.readString();
            this.videotime = in.readInt();
            this.videouri = in.readString();
            this.width = in.readString();
            this.image_small = in.readString();
            this.playcount = in.readString();
            this.playfcount = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

    }

    /**
     * code : 200
     * data : [{"bimageuri":"","bookmark":"3","cache_version":2,"cai":"7","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif","comment":"26","created_at":"2019-06-21 07:36:01","ding":"177","favourite":"3","hate":"7","height":"339","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a42372eb37.gif","is_gif":false,"love":"177","name":"天天趣图","original_pid":"0","passtime":"2019-06-21 07:36:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/07/31/5b60500856689_mini.jpg","repost":"5","screen_name":"天天趣图","status":"4","t":1561073761,"tag":"","text":"头一次坐飞机，咱也不知道这是不是正常，咱也不敢问","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","type":"10","user_id":"19889419","videotime":0,"videouri":"","width":"240"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","bookmark":"1","cache_version":2,"cai":"12","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","comment":"13","created_at":"2019-06-21 07:27:02","ding":"123","favourite":"1","hate":"12","height":"600","image0":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a09c7b828f_wpd.jpg","is_gif":false,"love":"123","name":"肉肉肉夹魔","original_pid":"0","passtime":"2019-06-21 07:27:02","playcount":"4139","playfcount":"429","profile_image":"http://wimg.spriteapp.cn/profile/large/2018/02/25/5a92804145ea6_mini.jpg","repost":"0","screen_name":"肉肉肉夹魔","status":"4","t":1561073222,"tag":"","text":"成熟的表现是什么","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","type":"41","user_id":"13290285","videotime":50,"videouri":"http://uvideo.spriteapp.cn/video/2019/0619/5d0a09c7b828f_wpd.mp4","width":"1066"},{"bimageuri":"","bookmark":"0","cache_version":2,"cai":"10","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a31274916c_1.jpg","comment":"22","created_at":"2019-06-21 07:06:01","ding":"113","favourite":"0","hate":"10","height":"1634","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a31274916c_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a31274916c_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a31274916c_1.jpg","is_gif":false,"love":"113","name":"笑起来很干净","original_pid":"0","passtime":"2019-06-21 07:06:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6e8efcd5_mini.jpg","repost":"1","screen_name":"笑起来很干净","status":"4","t":1561071961,"tag":"","text":"说出来你可能不信，我把保时捷啃了，狗肉怎么炖好吃","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","type":"10","user_id":"13347954","videotime":0,"videouri":"","width":"360"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","bookmark":"5","cache_version":2,"cai":"8","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","comment":"19","created_at":"2019-06-21 06:57:03","ding":"216","favourite":"5","hate":"8","height":"512","image0":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0619/5d0a26e08cb1d_wpd.jpg","is_gif":false,"love":"216","name":"送葬队集结号手","original_pid":"0","passtime":"2019-06-21 06:57:03","playcount":"4970","playfcount":"923","profile_image":"http://wx.qlogo.cn/mmopen/Q3auHgzwzM4oOpdNbdM4X57g8pWbTSAl4vkh8fHYR6MBUye2rc67eTxSjOmWd3DpyuiaeRjEkCuQNPK7HUROGibr5a2yGwwFVZZSWs4kibEXics/0","repost":"9","screen_name":"送葬队集结号手","status":"4","t":1561071423,"tag":"","text":"鸟：我一向有求必应！😒","theme_id":"55163","theme_name":"主版块","theme_type":"1","type":"41","user_id":"20542066","videotime":15,"videouri":"http://uvideo.spriteapp.cn/video/2019/0619/5d0a26e08cb1d_wpd.mp4","width":"288"},{"bimageuri":"","bookmark":"2","cache_version":2,"cai":"6","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/20/5d0a6af754351_1.jpg","comment":"27","created_at":"2019-06-21 06:36:01","ding":"109","favourite":"2","hate":"6","height":"1528","image0":"http://wimg.spriteapp.cn/ugc/2019/06/20/5d0a6af754351_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/20/5d0a6af754351_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/20/5d0a6af754351_1.jpg","is_gif":false,"love":"109","name":"柔指绕百结","original_pid":"0","passtime":"2019-06-21 06:36:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6d6466fc_mini.png","repost":"1","screen_name":"柔指绕百结","status":"4","t":1561070161,"tag":"","text":"图书馆奇葩遇，地上有高跟鞋，书架里还有一些衣服很迷惑\u2026\u2026","theme_id":"56781","theme_name":"情感社区","theme_type":"1","type":"10","user_id":"22980203","videotime":0,"videouri":"","width":"360"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","bookmark":"5","cache_version":2,"cai":"11","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","comment":"16","created_at":"2019-06-21 06:17:01","ding":"132","favourite":"5","hate":"11","height":"572","image0":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.jpg","is_gif":false,"love":"132","name":"敏智的选择","original_pid":"0","passtime":"2019-06-21 06:17:01","playcount":"4961","playfcount":"573","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/21/5c93280bb362e_mini.jpg","repost":"8","screen_name":"敏智的选择","status":"4","t":1561069021,"tag":"","text":"酒后人们的丑态，最后吐的稀里哗啦的是客家妹子吗？","theme_id":"0","theme_name":"","theme_type":"0","type":"41","user_id":"18380255","videotime":96,"videouri":"http://uvideo.spriteapp.cn/video/2019/0619/08f25c84929711e980df842b2b4c75ab_wpd.mp4","width":"368"},{"bimageuri":"","bookmark":"0","cache_version":2,"cai":"16","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a1dd78678c_1.jpg","comment":"63","created_at":"2019-06-21 05:56:01","ding":"87","favourite":"0","hate":"16","height":"1344","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a1dd78678c_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a1dd78678c_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a1dd78678c_1.jpg","is_gif":false,"love":"87","name":"望山夫","original_pid":"0","passtime":"2019-06-21 05:56:01","profile_image":"http://wx.qlogo.cn/mmopen/2EzJggZltBMKQovGd4IuGa76hXDzgichFuIVKKtawtILvtQbceTxWmGb5ichR3TyuE0oyXia6N6D9Pic38LZVNjia2rUJNMtKJAtm/0","repost":"0","screen_name":"望山夫","status":"4","t":1561067761,"tag":"","text":"突然对手像感兴趣，有没有老哥给看看，，","theme_id":"44289","theme_name":"互动区","theme_type":"1","type":"10","user_id":"20305026","videotime":0,"videouri":"","width":"1008"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","bookmark":"6","cache_version":2,"cai":"7","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","comment":"22","created_at":"2019-06-21 05:36:03","ding":"459","favourite":"6","hate":"7","height":"600","image0":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.jpg","is_gif":false,"love":"459","name":"瑾夏年华","original_pid":"0","passtime":"2019-06-21 05:36:03","playcount":"2605","playfcount":"208","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6e3c7b7e_mini.jpg","repost":"0","screen_name":"瑾夏年华","status":"4","t":1561066563,"tag":"","text":"20个烟头换1个冰淇淋 小学生们3年捡了5万个烟头","theme_id":"55163","theme_name":"主版块","theme_type":"1","type":"41","user_id":"20746627","videotime":93,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/78c5e694-92f5-11e9-8a6d-1866daeb0df1_wpd.mp4","width":"1066"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","bookmark":"3","cache_version":2,"cai":"11","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","comment":"25","created_at":"2019-06-21 04:15:02","ding":"126","favourite":"3","hate":"11","height":"960","image0":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.jpg","is_gif":false,"love":"126","name":"楚歌悲兮","original_pid":"0","passtime":"2019-06-21 04:15:02","playcount":"4365","playfcount":"504","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6d9c6b72_mini.jpg","repost":"6","screen_name":"楚歌悲兮","status":"4","t":1561061702,"tag":"","text":"有一种毁容叫拔智齿，拔智齿前VS拔智齿后有什么样的变化？","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","type":"41","user_id":"20746659","videotime":43,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/f8f69f64-9306-11e9-b791-1866daeb0df1_wpd.mp4","width":"540"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","bookmark":"12","cache_version":2,"cai":"9","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","comment":"9","created_at":"2019-06-21 03:35:01","ding":"186","favourite":"12","hate":"9","height":"640","image0":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.jpg","is_gif":false,"love":"186","name":"樱花⌒识盈","original_pid":"0","passtime":"2019-06-21 03:35:01","playcount":"6044","playfcount":"417","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da2b8b4_mini.jpg","repost":"1","screen_name":"樱花⌒识盈","status":"4","t":1561059301,"tag":"","text":"音乐与画面的完美结合","theme_id":"0","theme_name":"","theme_type":"0","type":"41","user_id":"20746662","videotime":78,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/e73ad644932111e9bd02842b2b4c75ab_wpd.mp4","width":"368"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","bookmark":"6","cache_version":2,"cai":"9","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","comment":"17","created_at":"2019-06-21 02:55:02","ding":"138","favourite":"6","hate":"9","height":"960","image0":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.jpg","is_gif":false,"love":"138","name":"流年絮语","original_pid":"0","passtime":"2019-06-21 02:55:02","playcount":"3604","playfcount":"264","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6dba8287_mini.jpg","repost":"14","screen_name":"流年絮语","status":"4","t":1561056902,"tag":"","text":"小孩奇葩睡姿合集。","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","type":"41","user_id":"20746886","videotime":75,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/a17d63b8-9300-11e9-9833-1866daeb0df1_wpd.mp4","width":"540"},{"bimageuri":"","bookmark":"4","cache_version":2,"cai":"7","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09ebfc8bb1b_1.jpg","comment":"15","created_at":"2019-06-21 02:52:02","ding":"67","favourite":"4","hate":"7","height":"1080","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09ebfc8bb1b_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09ebfc8bb1b_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09ebfc8bb1b_1.jpg","is_gif":false,"love":"67","name":"如花的少年","original_pid":"0","passtime":"2019-06-21 02:52:02","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6c00e0ad_mini.png","repost":"0","screen_name":"如花的少年","status":"4","t":1561056722,"tag":"","text":"有个这样的女朋友，什么体验？","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","type":"10","user_id":"22980503","videotime":0,"videouri":"","width":"360"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","bookmark":"1","cache_version":2,"cai":"3","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","comment":"15","created_at":"2019-06-21 02:15:01","ding":"134","favourite":"1","hate":"3","height":"1024","image0":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.jpg","is_gif":false,"love":"134","name":"樱花⌒识盈","original_pid":"0","passtime":"2019-06-21 02:15:01","playcount":"5429","playfcount":"1086","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da2b8b4_mini.jpg","repost":"0","screen_name":"樱花⌒识盈","status":"4","t":1561054501,"tag":"","text":"硬核大爷坐过山车 听说这个大爷经常来玩！ \u200b","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","type":"41","user_id":"20746662","videotime":12,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/f77e2402-9321-11e9-80da-1866daeb0df1_wpd.mp4","width":"576"},{"bimageuri":"","bookmark":"5","cache_version":2,"cai":"11","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/1eb5306e926f11e99050842b2b4c75ab_1.jpg","comment":"19","created_at":"2019-06-21 01:56:01","ding":"192","favourite":"5","hate":"11","height":"460","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/1eb5306e926f11e99050842b2b4c75ab_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/1eb5306e926f11e99050842b2b4c75ab_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/1eb5306e926f11e99050842b2b4c75ab_1.jpg","is_gif":false,"love":"192","name":"你又是什么鬼","original_pid":"0","passtime":"2019-06-21 01:56:01","profile_image":"http://wimg.spriteapp.cn/profile/20190318105555934552.jpg","repost":"0","screen_name":"你又是什么鬼","status":"4","t":1561053361,"tag":"","text":"我有那么一瞬间忘记了禁怎么写","theme_id":"0","theme_name":"","theme_type":"0","type":"10","user_id":"20620990","videotime":0,"videouri":"","width":"460"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","bookmark":"10","cache_version":2,"cai":"12","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","comment":"18","created_at":"2019-06-21 01:35:04","ding":"201","favourite":"10","hate":"12","height":"480","image0":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.jpg","is_gif":false,"love":"201","name":"橙子爱人_","original_pid":"0","passtime":"2019-06-21 01:35:04","playcount":"3199","playfcount":"529","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da86e61_mini.jpg","repost":"8","screen_name":"橙子爱人_","status":"4","t":1561052104,"tag":"","text":"有骗子谎称我室友以我名义进行贷款，我问了她几个问题，没想到最后是这样的结果","theme_id":"58191","theme_name":"搞笑视频","theme_type":"1","type":"41","user_id":"20746671","videotime":268,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/2996cca6-9330-11e9-91e7-1866daeb0df1_wpd.mp4","width":"852"},{"bimageuri":"","bookmark":"0","cache_version":2,"cai":"14","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a0189ee324_1.jpg","comment":"17","created_at":"2019-06-21 01:16:01","ding":"152","favourite":"0","hate":"14","height":"1080","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a0189ee324_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a0189ee324_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d0a0189ee324_1.jpg","is_gif":false,"love":"152","name":"我记得以前的生活也很精彩","original_pid":"0","passtime":"2019-06-21 01:16:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2016/05/17/573ab6cb37032_mini.jpg","repost":"2","screen_name":"我记得以前的生活也很精彩","status":"4","t":1561050961,"tag":"","text":"你高兴的太早了","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","type":"10","user_id":"13539362","videotime":0,"videouri":"","width":"1440"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","bookmark":"2","cache_version":2,"cai":"6","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","comment":"37","created_at":"2019-06-21 00:55:02","ding":"196","favourite":"2","hate":"6","height":"408","image0":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.jpg","is_gif":false,"love":"196","name":"樱花丶葬礼","original_pid":"0","passtime":"2019-06-21 00:55:02","playcount":"5158","playfcount":"2105","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6da60550_mini.jpg","repost":"11","screen_name":"樱花丶葬礼","status":"4","t":1561049702,"tag":"","text":"当脾气火爆的摩托车手与违章左拐的汽车相撞\u2026\u2026","theme_id":"55163","theme_name":"主版块","theme_type":"1","type":"41","user_id":"20746665","videotime":19,"videouri":"http://uvideo.spriteapp.cn/video/2019/0620/380bf16e-9338-11e9-9745-1866daeb0df1_wpd.mp4","width":"728"},{"bimageuri":"","bookmark":"7","cache_version":2,"cai":"21","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/18/5d0904e415764_1.jpg","comment":"47","created_at":"2019-06-21 00:36:01","ding":"123","favourite":"7","hate":"21","height":"3234","image0":"http://wimg.spriteapp.cn/ugc/2019/06/18/5d0904e415764_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/18/5d0904e415764_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/18/5d0904e415764_1.jpg","is_gif":false,"love":"123","name":"我的小思念","original_pid":"0","passtime":"2019-06-21 00:36:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6cbc5d2b_mini.jpg","repost":"2","screen_name":"我的小思念","status":"4","t":1561048561,"tag":"","text":"在恋爱期间遇到了一个比男朋友更加优秀的男生，然而他好像不准备跟我搞对象？觉得对不起未来的老公，但想断又断不了，我应该怎么做才好？","theme_id":"58240","theme_name":"搞笑图片","theme_type":"1","type":"10","user_id":"22980254","videotime":0,"videouri":"","width":"360"},{"bimageuri":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","bookmark":"11","cache_version":2,"cai":"22","cdn_img":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","comment":"43","created_at":"2019-06-21 00:24:03","ding":"134","favourite":"11","hate":"22","height":"300","image0":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","image1":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","image2":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","image_small":"http://wimg.spriteapp.cn/picture/2019/0619/5d09e0bcdc052_wpd.jpg","is_gif":false,"love":"134","name":"优艺小妞","original_pid":"0","passtime":"2019-06-21 00:24:03","playcount":"5362","playfcount":"782","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/01/02/5c2cc41654c8a_mini.jpg","repost":"4","screen_name":"优艺小妞","status":"4","t":1561047843,"tag":"","text":"梨花一枝春带雨","theme_id":"60369","theme_name":"女神萌妹","theme_type":"1","type":"41","user_id":"11933978","videotime":34,"videouri":"http://uvideo.spriteapp.cn/video/2019/0619/5d09e0bcdc052_wpd.mp4","width":"534"},{"bimageuri":"","bookmark":"1","cache_version":2,"cai":"17","cdn_img":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09f77b02119_1.jpg","comment":"21","created_at":"2019-06-20 23:56:01","ding":"106","favourite":"1","hate":"17","height":"1052","image0":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09f77b02119_1.jpg","image1":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09f77b02119_1.jpg","image2":"http://wimg.spriteapp.cn/ugc/2019/06/19/5d09f77b02119_1.jpg","is_gif":false,"love":"106","name":"少女与狐@","original_pid":"0","passtime":"2019-06-20 23:56:01","profile_image":"http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6c411b57_mini.jpg","repost":"3","screen_name":"少女与狐@","status":"4","t":1561046161,"tag":"","text":"备胎集","theme_id":"56781","theme_name":"情感社区","theme_type":"1","type":"10","user_id":"22980285","videotime":0,"videouri":"","width":"360"}]
     * msg : 成功!
     */
}
