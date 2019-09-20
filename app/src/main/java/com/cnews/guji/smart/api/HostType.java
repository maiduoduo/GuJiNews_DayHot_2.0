/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.cnews.guji.smart.api;

/**
 * 请求类型
 */
public class HostType {

    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    /**
     * 凤凰新闻host
     */
    public static final int IFeng_NEWS_TYPE = 0;
    /**
     * 头条新闻host
     */
    public static final int ITOUTIAO_NEWS_TYPE = 1;

    /**
     * 搜狐新闻的host
     */
    public static final int ISOHU_NEWS_TYPE = 2;

    /**
     * EPET的host
     */
    public static final int IEPET_NEWS_TYPE = 3;
    /**
     * EPET图片的host
     */
    public static final int IEPET_IMG_TYPE = 4;
    /**
     * 测试版host
     */
    public static final int NORMAL_TEST_TYPE = 5;
    public static final int NORMAL_163_TYPE = 6;
    /**
     * 新闻详情
     *      ifeng.com
     */
    public static final int TYPE_IFeng_NEWS_DETAIL = 7;
    public static final int TYPE_IFeng_NEWS_HOME_COMMENT = 8;
    public static final int TYPE_XUNSHI = 15;
    public static final int TYPE_OPENAPI = 16;
    public static final int TYPE_LIVIDEO = 17;
    public static final int TYPE_CATEYE_URL = 18;
    public static final int TYPE_ALL_URL = 150;

}
