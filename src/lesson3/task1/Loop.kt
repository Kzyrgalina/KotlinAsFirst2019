@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var N = n
    if (N == 0) return 1
    while (N > 0) {
        count++
        N /= 10
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n > 2) fib(n - 1) + fib(n - 2) else 1

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var first_x_second = m * n
    var first = m
    var second = n
    while (first != second) {
        if (first > second) first -= second
        else second -= first
    }
    return first_x_second / first
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var div = 2
    while (true) {
        if (n % div == 0) break
        div++
    }
    return div
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var n2 = n / 2
    while (n2 > 1) {
        if (n % n2 == 0) return n2
        n2--
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = TODO()

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = TODO()

/**                                                                                                                         
 * Средняя                                                                                                                  
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int = TODO()


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var sin_sum = 0.0
    var sin_pow = 3
    var sin_sign = -1
    var actual_sin: Double
    var x0 = x

    while (x0 !in (-2 * PI)..(2 * PI)) {
        when {
            (x0 < -2 * PI) -> x0 += 2 * PI
            (x0 > 2 * PI) -> x0 -= 2 * PI
        }
    }

    actual_sin = x0
    sin_sum = actual_sin
    while (abs(actual_sin) >= abs(eps)) {
        actual_sin = ((sin_sign) * x0.pow(sin_pow) / factorial(sin_pow))
        sin_pow += 2
        sin_sign *= (-1)
        sin_sum += actual_sin
    }
    return sin_sum
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var cos_sum = 1.0
    var cos_pow = 2
    var cos_sign = -1
    var actual_cos: Double
    var x0 = x

    while (x0 !in (-2 * PI)..(2 * PI)) {
        when {
            (x0 < -2 * PI) -> x0 += 2 * PI
            (x0 > 2 * PI) -> x0 -= 2 * PI
        }
    }

    actual_cos = x0
    while (abs(actual_cos) >= abs(eps)) {
        actual_cos = ((cos_sign) * x0.pow(cos_pow) / factorial(cos_pow))
        cos_pow += 2
        cos_sign *= (-1)
        cos_sum += actual_cos
    }
    return cos_sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var revert_number = 0
    var number = n
    while (number > 0) {
        revert_number += number % 10
        revert_number *= 10
        number /= 10
    }
    revert_number /= 10
    return revert_number
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var number = n
    var revert_number = revert(n)
    return (number == revert_number)
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    var digit1: Int
    var digit2: Int
    if (number < 10) return false
    digit1 = number % 10
    digit2 = digit1
    while ((number > 0) && (digit1 == digit2)) {
        digit2 = number % 10
        number /= 10
    }
    return (digit1 != digit2)
}

/**
 * Сложная                                                                                                                  
 *                                                                                                                          
 * Найти n-ю цифру последовательности из квадратов целых чисел:                                                             
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var square = 1
    var number = n
    var length = 3
    var i = 4
    fun square_length(x: Int): Int {
        var k = 0
        var number = x
        while (number > 0) {
            k++
            number /= 10
        }
        return k
    }

    if (number in 1..3) return sqr(number)
    while (length < number) {
        square = sqr(i)
        length += square_length(sqr(i))
        i++
    }
    if (length == number) return square % 10
    else while (length != number) {
        square /= 10
        length--
    }
    return square % 10
}

/**
 * Сложная                                                                                                                  
 *                                                                                                                          
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var fibonach = 1
    var number = n
    var length = 6
    var i = 7
    fun fib_length(x: Int): Int {
        var k = 0
        var number = x
        while (number > 0) {
            k++
            number /= 10
        }
        return k
    }
    when (number) {
        1 -> return 1
        2 -> return 1
        3 -> return 2
        4 -> return 3
        5 -> return 5
        6 -> return 8
    }
    while (length < number) {
        fibonach = fib(i)
        length += fib_length(fib(i))
        i++
    }
    if (length == number) return fibonach % 10
    else while (length != number) {
        fibonach /= 10
        length--
    }
    return fibonach % 10
}
