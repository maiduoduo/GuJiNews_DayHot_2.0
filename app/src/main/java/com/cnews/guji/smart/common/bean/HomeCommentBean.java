package com.cnews.guji.smart.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @package: HomeCommentBean
 * @author： JSYL-DCL
 * @date: 2019/5/16
 * @describe： 评论
 * @email： 11442865
 */
public class HomeCommentBean implements Serializable {


    public int allow_comment;
    public int count;
    public int join_count;
    public List<CommentsBeanX> comments;

    public int getAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(int allow_comment) {
        this.allow_comment = allow_comment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getJoin_count() {
        return join_count;
    }

    public void setJoin_count(int join_count) {
        this.join_count = join_count;
    }

    public List<CommentsBeanX> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBeanX> comments) {
        this.comments = comments;
    }

    public static class CommentsBeanX {
        /**
         * add_time : 1561009207
         * article_id : 143979540
         * children : {"comments":[{"add_time":"1561009207","article_id":"143979540","client_ip":"175.153.171.*","comment_contents":"恕我骂人，如果你还是人，你的脑袋被猪拱了，没人性的杂种!","comment_date":"2019/06/19 21:47","comment_id":"1342205446","comment_temid":"1217988185","create_time":"1560952048","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"四川省,成都市","last_modtime":"1560952048","main_id":"1342201542","os":"android_27","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友","uptimes":91,"user_id":"60532061","user_url":"","uuid":"bb7bukFTcoK82hxyI5Xs02MF1PZI4yqYiG%2FK1bFWWbeBI5dxkA"},{"add_time":"1561009207","article_id":"143979540","client_ip":"112.111.39.*","comment_contents":"你太聪明了，怎么知道的呢，有证据吗？[cute][cute][cute][cute]","comment_date":"2019/06/19 21:50","comment_id":"1342205721","comment_temid":"1978675143","create_time":"1560952218","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"外星","last_modtime":"1560952218","main_id":"1342201542","os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友1vyNEP","uptimes":36,"user_id":"98253256","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=凤凰网友1vyNEP&guid=98253256","uuid":"167aTCEj25A2NNvDrJd%2F4VtRRUOyCZxElY55YNx9tBDZ%2BbDgUQ"}],"count":"19"}
         * client_ip : 117.136.79.*
         * comment_contents : 这个结果在飞机没爆之前就商定了的结果，海牙法庭不就是为美国服务的吗？
         * comment_date : 2019/06/19 21:01
         * comment_id : 1342201542
         * comment_temid : 1404271544
         * create_time : 1560949287
         * doc_name : 马航MH17最新调查结果：被乌东部发射的导弹击落
         * doc_url : ucms_7ndckzvwyw7
         * faceurl : http://p0.ifengimg.com/ifengimcp/pic/20180630/53bd747b982c246bc820_size5_w128_h128.jpg
         * integral : 0
         * ip_from : 其它地区,中国移动其它
         * last_modtime : 1560949287
         * main_id : 19
         * os : android_28
         * permalink : https://news.ifeng.com/c/7ndckzvwyw7
         * pics : []
         * proid : ifengnews
         * quote_id : 0
         * special_id : 0
         * uname : 手机用户2940
         * uptimes : 276
         * user_id : 47892559
         * user_url : http://comment.ifeng.com/viewpersonal.php?uname=手机用户2940&guid=47892559
         * uuid : e5faV%2Fr3gcUduXOSeY6F8IxqiCGOfIqMNRSl7KZbILFtyYTS7A
         * user_role : {"ifengnews":{"medal":"|1"}}
         */

        public String add_time;
        public String article_id;
        public ChildrenBean children;
        public String client_ip;
        public String comment_contents;
        public String comment_date;
        public String comment_id;
        public String comment_temid;
        public String create_time;
        public String doc_name;
        public String doc_url;
        public String faceurl;
        public String integral;
        public String ip_from;
        public String last_modtime;
        public int main_id;
        public String os;
        public String permalink;
        public String proid;
        public String quote_id;
        public String special_id;
        public String uname;
        public int uptimes;
        public String user_id;
        public String ext2;
        public String user_url;
        public String uuid;
        public UserRoleBean user_role;
        public List<PicBean> pics;

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public ChildrenBean getChildren() {
            return children;
        }

        public void setChildren(ChildrenBean children) {
            this.children = children;
        }

        public String getClient_ip() {
            return client_ip;
        }

        public void setClient_ip(String client_ip) {
            this.client_ip = client_ip;
        }

        public String getComment_contents() {
            return comment_contents;
        }

        public void setComment_contents(String comment_contents) {
            this.comment_contents = comment_contents;
        }

        public String getComment_date() {
            return comment_date;
        }

        public void setComment_date(String comment_date) {
            this.comment_date = comment_date;
        }

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getComment_temid() {
            return comment_temid;
        }

        public void setComment_temid(String comment_temid) {
            this.comment_temid = comment_temid;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDoc_name() {
            return doc_name;
        }

        public void setDoc_name(String doc_name) {
            this.doc_name = doc_name;
        }

        public String getDoc_url() {
            return doc_url;
        }

        public void setDoc_url(String doc_url) {
            this.doc_url = doc_url;
        }

        public String getFaceurl() {
            return faceurl;
        }

        public void setFaceurl(String faceurl) {
            this.faceurl = faceurl;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getIp_from() {
            return ip_from;
        }

        public void setIp_from(String ip_from) {
            this.ip_from = ip_from;
        }

        public String getLast_modtime() {
            return last_modtime;
        }

        public void setLast_modtime(String last_modtime) {
            this.last_modtime = last_modtime;
        }

        public int getMain_id() {
            return main_id;
        }

        public void setMain_id(int main_id) {
            this.main_id = main_id;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getPermalink() {
            return permalink;
        }

        public void setPermalink(String permalink) {
            this.permalink = permalink;
        }

        public String getProid() {
            return proid;
        }

        public void setProid(String proid) {
            this.proid = proid;
        }

        public String getQuote_id() {
            return quote_id;
        }

        public void setQuote_id(String quote_id) {
            this.quote_id = quote_id;
        }

        public String getSpecial_id() {
            return special_id;
        }

        public void setSpecial_id(String special_id) {
            this.special_id = special_id;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public int getUptimes() {
            return uptimes;
        }

        public void setUptimes(int uptimes) {
            this.uptimes = uptimes;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_url() {
            return user_url;
        }

        public void setUser_url(String user_url) {
            this.user_url = user_url;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public UserRoleBean getUser_role() {
            return user_role;
        }

        public void setUser_role(UserRoleBean user_role) {
            this.user_role = user_role;
        }

        public static class ChildrenBean {
            /**
             * comments : [{"add_time":"1561009207","article_id":"143979540","client_ip":"175.153.171.*","comment_contents":"恕我骂人，如果你还是人，你的脑袋被猪拱了，没人性的杂种!","comment_date":"2019/06/19 21:47","comment_id":"1342205446","comment_temid":"1217988185","create_time":"1560952048","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"四川省,成都市","last_modtime":"1560952048","main_id":"1342201542","os":"android_27","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友","uptimes":91,"user_id":"60532061","user_url":"","uuid":"bb7bukFTcoK82hxyI5Xs02MF1PZI4yqYiG%2FK1bFWWbeBI5dxkA"},{"add_time":"1561009207","article_id":"143979540","client_ip":"112.111.39.*","comment_contents":"你太聪明了，怎么知道的呢，有证据吗？[cute][cute][cute][cute]","comment_date":"2019/06/19 21:50","comment_id":"1342205721","comment_temid":"1978675143","create_time":"1560952218","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"外星","last_modtime":"1560952218","main_id":"1342201542","os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友1vyNEP","uptimes":36,"user_id":"98253256","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=凤凰网友1vyNEP&guid=98253256","uuid":"167aTCEj25A2NNvDrJd%2F4VtRRUOyCZxElY55YNx9tBDZ%2BbDgUQ"}]
             * count : 19
             */

            public String count;
            public List<CommentsBean> comments;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public List<CommentsBean> getComments() {
                return comments;
            }

            public void setComments(List<CommentsBean> comments) {
                this.comments = comments;
            }

            public static class CommentsBean {
                /**
                 * add_time : 1561009207
                 * article_id : 143979540
                 * client_ip : 175.153.171.*
                 * comment_contents : 恕我骂人，如果你还是人，你的脑袋被猪拱了，没人性的杂种!
                 * comment_date : 2019/06/19 21:47
                 * comment_id : 1342205446
                 * comment_temid : 1217988185
                 * create_time : 1560952048
                 * doc_name : 马航MH17最新调查结果：被乌东部发射的导弹击落
                 * doc_url : ucms_7ndckzvwyw7
                 * faceurl : https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg
                 * integral : 0
                 * ip_from : 四川省,成都市
                 * last_modtime : 1560952048
                 * main_id : 1342201542
                 * os : android_27
                 * permalink : https://news.ifeng.com/c/7ndckzvwyw7
                 * pics : []
                 * proid : ifengnews
                 * quote_id : 1342201542
                 * special_id : 0
                 * uname : 凤凰网友
                 * uptimes : 91
                 * user_id : 60532061
                 * user_url :
                 * uuid : bb7bukFTcoK82hxyI5Xs02MF1PZI4yqYiG%2FK1bFWWbeBI5dxkA
                 */

                public String add_time;
                public String article_id;
                public String client_ip;
                public String comment_contents;
                public String comment_date;
                public String comment_id;
                public String comment_temid;
                public String create_time;
                public String doc_name;
                public String doc_url;
                public String faceurl;
                public String integral;
                public String ip_from;
                public String last_modtime;
                public String main_id;
                public String os;
                public String permalink;
                public String proid;
                public String quote_id;
                public String special_id;
                public String uname;
                public int uptimes;
                public String user_id;
                public String user_url;
                public String uuid;
                public List<PicBean> pics;
                public String reply_uname;

                public class PicBean {
                }
                public String getReply_uname() {
                    return reply_uname;
                }

                public void setReply_uname(String reply_uname) {
                    this.reply_uname = reply_uname;
                }

                public String getAdd_time() {
                    return add_time;
                }

                public void setAdd_time(String add_time) {
                    this.add_time = add_time;
                }

                public String getArticle_id() {
                    return article_id;
                }

                public void setArticle_id(String article_id) {
                    this.article_id = article_id;
                }

                public String getClient_ip() {
                    return client_ip;
                }

                public void setClient_ip(String client_ip) {
                    this.client_ip = client_ip;
                }

                public String getComment_contents() {
                    return comment_contents;
                }

                public void setComment_contents(String comment_contents) {
                    this.comment_contents = comment_contents;
                }

                public String getComment_date() {
                    return comment_date;
                }

                public void setComment_date(String comment_date) {
                    this.comment_date = comment_date;
                }

                public String getComment_id() {
                    return comment_id;
                }

                public void setComment_id(String comment_id) {
                    this.comment_id = comment_id;
                }

                public String getComment_temid() {
                    return comment_temid;
                }

                public void setComment_temid(String comment_temid) {
                    this.comment_temid = comment_temid;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getDoc_name() {
                    return doc_name;
                }

                public void setDoc_name(String doc_name) {
                    this.doc_name = doc_name;
                }

                public String getDoc_url() {
                    return doc_url;
                }

                public void setDoc_url(String doc_url) {
                    this.doc_url = doc_url;
                }

                public String getFaceurl() {
                    return faceurl;
                }

                public void setFaceurl(String faceurl) {
                    this.faceurl = faceurl;
                }

                public String getIntegral() {
                    return integral;
                }

                public void setIntegral(String integral) {
                    this.integral = integral;
                }

                public String getIp_from() {
                    return ip_from;
                }

                public void setIp_from(String ip_from) {
                    this.ip_from = ip_from;
                }

                public String getLast_modtime() {
                    return last_modtime;
                }

                public void setLast_modtime(String last_modtime) {
                    this.last_modtime = last_modtime;
                }

                public String getMain_id() {
                    return main_id;
                }

                public void setMain_id(String main_id) {
                    this.main_id = main_id;
                }

                public String getOs() {
                    return os;
                }

                public void setOs(String os) {
                    this.os = os;
                }

                public String getPermalink() {
                    return permalink;
                }

                public void setPermalink(String permalink) {
                    this.permalink = permalink;
                }

                public String getProid() {
                    return proid;
                }

                public void setProid(String proid) {
                    this.proid = proid;
                }

                public String getQuote_id() {
                    return quote_id;
                }

                public void setQuote_id(String quote_id) {
                    this.quote_id = quote_id;
                }

                public String getSpecial_id() {
                    return special_id;
                }

                public void setSpecial_id(String special_id) {
                    this.special_id = special_id;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }

                public int getUptimes() {
                    return uptimes;
                }

                public void setUptimes(int uptimes) {
                    this.uptimes = uptimes;
                }

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getUser_url() {
                    return user_url;
                }

                public void setUser_url(String user_url) {
                    this.user_url = user_url;
                }

                public String getUuid() {
                    return uuid;
                }

                public void setUuid(String uuid) {
                    this.uuid = uuid;
                }

            }
        }

        public static class UserRoleBean {
            /**
             * ifengnews : {"medal":"|1"}
             */

            public IfengnewsBean ifengnews;

            public IfengnewsBean getIfengnews() {
                return ifengnews;
            }

            public void setIfengnews(IfengnewsBean ifengnews) {
                this.ifengnews = ifengnews;
            }

            public static class IfengnewsBean {
                /**
                 * medal : |1
                 */

                public String medal;

                public String getMedal() {
                    return medal;
                }

                public void setMedal(String medal) {
                    this.medal = medal;
                }
            }
        }

        public class PicBean {
        }
    }


    /**
     * allow_comment : 1
     * comments : [{"add_time":"1561009207","article_id":"143979540","children":{"comments":[{"add_time":"1561009207","article_id":"143979540","client_ip":"175.153.171.*","comment_contents":"恕我骂人，如果你还是人，你的脑袋被猪拱了，没人性的杂种!","comment_date":"2019/06/19 21:47","comment_id":"1342205446","comment_temid":"1217988185","create_time":"1560952048","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"四川省,成都市","last_modtime":"1560952048","main_id":"1342201542","os":"android_27","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友","uptimes":91,"user_id":"60532061","user_url":"","uuid":"bb7bukFTcoK82hxyI5Xs02MF1PZI4yqYiG%2FK1bFWWbeBI5dxkA"},{"add_time":"1561009207","article_id":"143979540","client_ip":"112.111.39.*","comment_contents":"你太聪明了，怎么知道的呢，有证据吗？[cute][cute][cute][cute]","comment_date":"2019/06/19 21:50","comment_id":"1342205721","comment_temid":"1978675143","create_time":"1560952218","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"外星","last_modtime":"1560952218","main_id":"1342201542","os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342201542","special_id":"0","uname":"凤凰网友1vyNEP","uptimes":36,"user_id":"98253256","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=凤凰网友1vyNEP&guid=98253256","uuid":"167aTCEj25A2NNvDrJd%2F4VtRRUOyCZxElY55YNx9tBDZ%2BbDgUQ"}],"count":"19"},"client_ip":"117.136.79.*","comment_contents":"这个结果在飞机没爆之前就商定了的结果，海牙法庭不就是为美国服务的吗？","comment_date":"2019/06/19 21:01","comment_id":"1342201542","comment_temid":"1404271544","create_time":"1560949287","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"http://p0.ifengimg.com/ifengimcp/pic/20180630/53bd747b982c246bc820_size5_w128_h128.jpg","integral":"0","ip_from":"其它地区,中国移动其它","last_modtime":"1560949287","main_id":19,"os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"0","special_id":"0","uname":"手机用户2940","uptimes":276,"user_id":"47892559","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=手机用户2940&guid=47892559","uuid":"e5faV%2Fr3gcUduXOSeY6F8IxqiCGOfIqMNRSl7KZbILFtyYTS7A"},{"add_time":"1561009207","article_id":"143979540","children":{"comments":[],"count":0},"client_ip":"223.91.97.*","comment_contents":"看来是又回到了原点","comment_date":"2019/06/19 20:54","comment_id":"1342201035","comment_temid":"1457339727","create_time":"1560948897","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"http://p0.ifengimg.com/ifengimcp/pic/20171205/249610d976da0133a4b5_size57_w170_h162.png","integral":"0","ip_from":"河南省,信阳市","last_modtime":"1560948897","main_id":"0","os":"12.3.1","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"凤凰新闻","quote_id":"0","special_id":"0","uname":"观澜结局","uptimes":110,"user_id":"77473493","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=观澜结局&guid=77473493","uuid":"c28a4g%2F2TeD5EyN0guHdK5MPsd1YJS8ytZ6zrh7NKdj6u3M4DQ"},{"add_time":"1561009207","article_id":"143979540","children":{"comments":[{"add_time":"1561009207","article_id":"143979540","client_ip":"117.136.89.*","comment_contents":"不好意思 想问一下离间谁啊？","comment_date":"2019/06/20 08:25","comment_id":"1342232512","comment_temid":"1336051255","create_time":"1560990354","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"其它地区,中国移动其它","last_modtime":"1560990354","main_id":"1342209709","os":"android_27","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342209709","special_id":"0","uname":"李郁然的小木屋","uptimes":4,"user_id":"4000000038383910624","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=李郁然的小木屋&guid=4000000038383910624","uuid":"29191BbS35Ygp2we57ujGDmAxhThpjgS9AkW9igBVmFJJuwKI9fAwjB6ryhTd%2Bmj"}],"count":"1"},"client_ip":"61.158.149.*","comment_contents":"美国人搞的，嫁祸于俄？挑拨离间!","comment_date":"2019/06/19 22:34","comment_id":"1342209709","comment_temid":"1716894794","create_time":"1560954881","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"河南省,开封市","last_modtime":"1560954881","main_id":1,"os":"android_26","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"0","special_id":"0","uname":"凤凰网友","uptimes":45,"user_id":"71377458","user_role":{"ifengnews":{"medal":"|1"}},"user_url":"","uuid":"0f74Dd2wQkTrD%2BzPpI9pe5fjUVrHhxsMqvydE6z25eOqOTq%2Feg"},{"add_time":"1561009207","article_id":"143979540","children":{"comments":[{"add_time":"1561009207","article_id":"143979540","client_ip":"14.223.182.*","comment_contents":"乌东防控部队是民间武装吗？","comment_date":"2019/06/20 10:56","comment_id":"1342248546","comment_temid":"1884762543","create_time":"1560999412","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","integral":"0","ip_from":"广东省,广州市","last_modtime":"1560999412","main_id":"1342226278","os":"android_27","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342226278","special_id":"0","uname":"凤凰网友","uptimes":1,"user_id":"4000000046334780625","user_url":"","uuid":"d8527lYo2X7vuz8FJc4zd52nzpQ%2BG6D6b0DPBeFfYfdUSFGEeqe0dh%2B1IDuH7a2k"}],"count":"1"},"client_ip":"222.95.82.*","comment_contents":"调查结束没？传言说乌克兰军方故意没有关闭交战区空域，并在客机被击落前派战机引诱乌东防控部队开火，故意给MH17设了个陷阱。这些可能才是悲剧的真相，至于谁击落了飞机其实都一样。","comment_date":"2019/06/20 07:12","comment_id":"1342226278","comment_temid":"648929734","create_time":"1560985927","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"http://my.ifengimg.com/48/113/3c1fa5d002962846/1432449064/3c1fa5d002962846_1.jpg","integral":"0","ip_from":"江苏省,苏州市","last_modtime":"1560985927","main_id":1,"os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"0","special_id":"0","uname":"凤凰网友hS9gnR","uptimes":39,"user_id":"60336894","user_role":{"ifengnews":{"medal":"|2"}},"user_url":"http://comment.ifeng.com/viewpersonal.php?uname=凤凰网友hS9gnR&guid=60336894","uuid":"2165rqw9w%2BEk7cGBm6nAESB2PWbEpr%2FxdIChPcOKTxG6lRKSLw"},{"add_time":"1561009207","article_id":"143979540","children":{"comments":[{"add_time":"1561009207","article_id":"143979540","client_ip":"113.213.17.*","comment_contents":"只有经历过的人知道。调查报告都不知道，谁会知道对错？","comment_date":"2019/06/20 13:11","comment_id":"1342260826","comment_temid":"971621792","create_time":"1561007460","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"http://p2.ifengimg.com/ifengimcp/pic/20180811/91c7498d92948a8055e2_size7_w128_h128.jpg","integral":"0","ip_from":"吉林省,长春市","last_modtime":"1561007460","main_id":"1342205161","os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"1342205161","special_id":"0","uname":"空洞见解","uptimes":0,"user_id":"75752184","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=空洞见解&guid=75752184","uuid":"c667xT8Ydk8nz9ZdO6A6HcmTWk6jsoeePDr0CyOHdlb75ohTjA"}],"count":"1"},"client_ip":"223.104.13.*","comment_contents":"唉，又是一笔糊涂账。","comment_date":"2019/06/19 21:44","comment_id":"1342205161","comment_temid":"115668216","create_time":"1560951851","doc_name":"马航MH17最新调查结果：被乌东部发射的导弹击落","doc_url":"ucms_7ndckzvwyw7","faceurl":"https://y0.ifengimg.com/vusercenter/images/default_headPortrait_1.jpg","integral":"0","ip_from":"其它地区,中国移动其它","last_modtime":"1560951851","main_id":1,"os":"android_28","permalink":"https://news.ifeng.com/c/7ndckzvwyw7","pics":[],"proid":"ifengnews","quote_id":"0","special_id":"0","uname":"凤凰网友PGkIK1","uptimes":19,"user_id":"4000000025875920625","user_url":"http://comment.ifeng.com/viewpersonal.php?uname=凤凰网友PGkIK1&guid=4000000025875920625","uuid":"0637LQWK3e1yWIgym71f2sTgTu2xmXQxCkF6Nt%2F4wjFFF07WvocFwg2sGLat%2FpQW"}]
     * count : 55
     * join_count : 1235
     */
}
