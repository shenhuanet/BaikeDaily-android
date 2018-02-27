package com.shenhua.baikedaily.bean

/**
 * Created by shenhua on 2018-02-27-0027.
 *
 * @author shenhua
 * Email shenhuanet@126.com
 */
class Baike {

    /**
     * itemId : 20768
     * title : 半斤八两为什么不是五两
     * link : https://baike.baidu.com/tashuo/browse/content?id=427d0d5adac7d4c9065bcc4a
     * publishTime : 1519618750
     * wapLink : https://baike.baidu.com/tashuo/browse/content?id=427d0d5adac7d4c9065bcc4a
     * author : du 小力
     * desc : “半斤八两” 意指彼此不相上下，但按照换算半斤不应该是五两吗？
     * pic : https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/eWH%3D278%2C208/sign=26dfe2e13dadcbef135e030b9e9816f1/d01373f082025aaf5272a5eef7edab64034f1a57.jpg
     * related : [{"title":"半斤八两","url":"https://baike.baidu.com/item/%E5%8D%8A%E6%96%A4%E5%85%AB%E4%B8%A4/1476"},{"title":"度量衡","url":"https://baike.baidu.com/item/%E5%BA%A6%E9%87%8F%E8%A1%A1"}]
     * pv : 34135
     * total : 753
     */

    var itemId: Int = 0
    var title: String? = null
    var link: String? = null
    var publishTime: Long = 0
    var wapLink: String? = null
    var author: String? = null
    var desc: String? = null
    var pic: String? = null
    var pv: Int = 0
    var total: Int = 0
    var related: List<RelatedBean>? = null

    class RelatedBean {
        /**
         * title : 半斤八两
         * url : https://baike.baidu.com/item/%E5%8D%8A%E6%96%A4%E5%85%AB%E4%B8%A4/1476
         */

        var title: String? = null
        var url: String? = null
    }
}
