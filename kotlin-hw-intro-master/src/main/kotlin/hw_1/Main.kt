/**
 * Условия следующие:

Если предыдущая сумма продаж от 0 до 1 000, то % составляет 30% от суммы
Если предыдущая сумма продаж от 1 001 до 10 000, то % составляет 25% от суммы
Если предыдущая сумма продаж от 10 001 до 50 000, то % составляет 20% от суммы
Если предыдущая сумма продаж от 50 001, то % составляет 15% от суммы
Эксклюзивные авторы (т.е. авторы, публикующие контент только в нашей соц.сети, дополнительно получают -5% к налогам).
Например, эксклюзивный автор, продавший на 11 000, будет платить не 20%, а 15%.
 */

package hw_1

fun main() {
    val amount = 200 // стоимость текущей продажи
    val total = 11_000 // сумма предыдущих продаж
    val fee = calculateFee(200, 11_000, exclusive = true)  // exclusive по умолчанию = false
    println(fee) // 40
}

fun calculateFee(
    amount: Int = 200, // стоимость текущей продажи
    total: Int = 11_000, // сумма предыдущих продаж
    exclusive: Boolean = false //флаг эксклюзивности
): Double {
    val fee: Double = when (total) {
        in 0..1000 -> 0.3
        in 1001..10000 -> 0.25
        in 10000..50000 -> 0.2
        else -> 0.15
    }
    return (if (exclusive) fee - 0.05 else fee) * amount
}
