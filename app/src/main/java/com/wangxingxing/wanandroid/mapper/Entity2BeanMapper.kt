package com.wangxingxing.wanandroid.mapper

import com.wangxingxing.wanandroid.bean.HomeArticleBean
import com.wangxingxing.wanandroid.db.entity.SquareArticleEntity

/**
 * author : 王星星
 * date : 2022/1/14 17:57
 * email : 1099420259@qq.com
 * description :
 */
class Entity2BeanMapper : Mapper<SquareArticleEntity, HomeArticleBean> {
    override fun map(input: SquareArticleEntity): HomeArticleBean =
        HomeArticleBean(
            id = input.id,
            apkLink = input.apkLink,
            audit = input.audit,
            author = input.author,
            canEdit = input.canEdit,
            chapterId = input.chapterId,
            chapterName = input.chapterName,
            collect = input.collect,
            courseId = input.courseId,
            desc = input.desc,
            descMd = input.descMd,
            envelopePic = input.envelopePic,
            fresh = input.fresh,
            host = input.host,
            link = input.link,
            niceDate = input.niceDate,
            niceShareDate = input.niceShareDate,
            origin = input.origin,
            prefix = input.prefix,
            projectLink = input.projectLink,
            publishTime = input.publishTime,
            realSuperChapterId = input.realSuperChapterId,
            selfVisible = input.selfVisible,
            shareDate = input.shareDate,
            shareUser = input.shareUser,
            superChapterId = input.superChapterId,
            superChapterName = input.superChapterName,
            // TODO: 2022/1/14 tags = input.tags 
            tags = listOf(),
            title = input.title,
            type = input.type,
            userId = input.userId,
            visible = input.visible,
            zan = input.zan
        )
    

}