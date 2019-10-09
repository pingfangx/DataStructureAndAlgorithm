package com.pingfangx.study.tutorial.specialized.reflect

import com.pingfangx.study.tutorial.specialized.reflect.method.parameter.ParameterTest
import java.lang.reflect.Modifier
import java.lang.reflect.Parameter

/**
 *
 * @author pingfangx
 * @date 2019/9/28
 */
open class BaseReflectTest {
    /**
     * 打印关注的信息
     */
    open fun print() {
    }

    /**
     * 输出简单信息，力图与 toGenericString() 一致
     */
    open fun simplePrint(sb: StringBuilder) {
    }

    fun doSimplePrint() {
        val sb = StringBuilder()
        simplePrint(sb)
        print(sb)
    }

    fun print(name: String, value: String) {
        println("$name : $value")
    }

    fun noneElement(): String {
        return "\nNone"
    }

    /**
     * 获取 modifiers 的文字表示
     * 参照 java.lang.reflect.Modifier.toString
     */
    open fun modifiersByMethod(mod: Int): String {
        val sb = StringBuilder()
        //作用域
        sb.append(when {
            Modifier.isPublic(mod) -> "public "
            Modifier.isProtected(mod) -> "protected "
            Modifier.isPrivate(mod) -> "private "
            else -> " "
        })

        if (Modifier.isAbstract(mod)) {
            sb.append("abstract ")
        }
        if (Modifier.isStatic(mod)) {
            sb.append("static ")
        }
        if (Modifier.isFinal(mod)) {
            sb.append("final ")
        }
        if (Modifier.isTransient(mod)) {
            sb.append("transient ")
        }
        if (Modifier.isVolatile(mod)) {
            sb.append("volatile ")
        }
        if (Modifier.isSynchronized(mod)) {
            sb.append("synchronized ")
        }
        if (Modifier.isNative(mod)) {
            sb.append("native ")
        }
        if (Modifier.isStrict(mod)) {
            sb.append("strictfp ")
        }

        val len = sb.length
        return if (len > 0) {
            sb.substring(0, len - 1)
        } else {
            ""
        }
    }

    fun addParametersInfo(sb: StringBuilder, parameters: Array<Parameter>?) {
        sb.append('(')
        if (parameters?.isNotEmpty() == true) {
            parameters.withIndex().forEach {
                if (it.index > 0) {
                    sb.append(',')
                    sb.append(' ')
                }
                ParameterTest(it.value).simplePrint(sb)
            }
        }
        sb.append(')')
    }

    /**
     * \n\t 正好用来换行和缩进
     */
    fun Array<*>?.arrayToString(join: String = "\n\t"): String {
        return arrayToString { join + it.toString() }
    }

    inline fun <T> Array<out T>?.arrayToString(action: (it: T) -> CharSequence): String {
        return arrayToString(StringBuilder(), action)
    }


    inline fun <T> Array<out T>?.arrayToString(sb: StringBuilder, action: (it: T) -> CharSequence): String {
        return arrayToString(sb) { s, it -> s.append(action(it)) }
    }

    inline fun <T> Array<out T>?.arrayToString(sb: StringBuilder, action: (sb: StringBuilder, it: T) -> Unit): String {
        return if (this?.isNotEmpty() == true) {
            this.forEach { action(sb, it) }
            sb.toString()
        } else {
            "\nNone"
        }
    }
}