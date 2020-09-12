package hw_2

import hw_2.view.TextView
import hw_2.view.View
import hw_2.view.ViewGroup
import hw_2.widget.Button

/**
 * Задача №2 - Hierarcy
Вам поручили реализовать систему базовых виджетов для приложения:

View
ViewGroup
TextView
Button
View
Базовый класс, от которого все наследуются. Содержит лишь один метод click, который выводит в консоль строку View clicked (для вывода в консоль используйте функцию println).

ViewGroup
Класс, наследующийся от View, содержит member function addView (внутри хранит пока всего одно View - как пройдём коллекции, сможет хранить список).

TextView
Класс, наследующийся от View, содержит внутри текст. Пример использования:

val text = TextView("Some Text")
text.click() // вызывается метод из `View`
println(text.text) // Some Text
text.text = "Something bad happened"
println(text.text) // Something bad happened

Button
Класс, наследующийся от TextView:

val button = Button("Click me")
button.click() // вызывается метод из `View`
println(button.text) // Click me
button.text = "Don't click me"
println(button.text) // Don't click me
Результат
В результате вы должны иметь возможность создавать сколь иерархии View, например:

val main = ViewGroup()
val title = TextView("Main Screen")
main.addView(title)

val content = ViewGroup()
val readMore = Button("Read more")
content.addView(readMore)
 */

fun main() {
    val text = TextView("Some Text")
    text.click() // вызывается метод из `View`
    println(text.text) // Some Text
    text.text = "Something bad happened"
    println(text.text) // Something bad happened


    val button = Button("Click me")
    button.click() // вызывается метод из `View`
    println(button.text) // Click me
    button.textBtn = "Don't click me"
    println(button.textBtn) // Don't click me

    val main = ViewGroup()
    val title = TextView("Main Screen")
    main.addView(title)

    val content = ViewGroup()
    val readMore = Button("Read more")
    content.addView(readMore)

}
