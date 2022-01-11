package com.wangxingxing.wanandroid.proto

import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

/**
 * author : 王星星
 * date : 2022/1/10 18:11
 * email : 1099420259@qq.com
 * description :
 */
object UserSerializer : Serializer<User> {
    override val defaultValue: User
        get() = User.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): User = User.parseFrom(input)

    override suspend fun writeTo(t: User, output: OutputStream)= t.writeTo(output)
}