package hw_1.model

class Profile (
    val id: String,
    val login: String,
    val name: String,
    val secondname: String,
    val statuse: String,
    val avatar: String
)
{
    val fullName: String
            get() = name + " " + secondname

}