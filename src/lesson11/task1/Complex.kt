@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson5.task1.whoAreInBoth
import java.lang.IllegalArgumentException
import java.util.IllegalFormatException

/**
 * Фабричный метод для создания комплексного числа из строки вида x+yi
 */
fun Complex(s: String): Complex {
    if (!Regex("""\d{1,}\.?\d*[-+]\d{1,}\.?\d*[i]""").matches(s)) throw IllegalArgumentException()
    val re = Regex("""^\d{1,}\.?\d*""").find(s)!!.value.toDouble()
    val im = Regex("""[-+]\d{1,}\.?\d*""").find(s)!!.value.toDouble()
    return Complex(re, im)
}

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex {
        return Complex(this.re + other.re, this.im + other.im)
    }

    /**
     * Смена знака (у обеих частей числа)S
     */
    operator fun unaryMinus(): Complex {
        return Complex(-this.re, -this.im)
    }

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex {
        return Complex(this.re - other.re, this.im - other.im)
    }

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex {
        return Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re)
    }

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        val a = (this.re * other.re + this.im * other.im) / (other.re * other.re + other.im * other.im)
        val b = (this.im * other.re - this.re * other.im) / (other.re * other.re + other.im * other.im)
        return Complex(a, b)
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        return (other is Complex && this.re == other.re && this.im == other.im)
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        return (this.re.toString() + this.im.toString() + 'i')
    }
}
