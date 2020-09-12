package hw_2.widget

import hw_2.view.TextView

class Button (
    var textBtn: String
): TextView(textBtn) {
    override fun click() {
        println(textBtn)
    }
}