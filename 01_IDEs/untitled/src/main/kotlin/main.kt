fun main() {

    val pin = "0000"
    println(pin.compareTo("0000"))

    /*printHelloWorld()

    val name = "Tobias"
    val nameString: String = "Melmer"
    val currentYear = 2021
    var birthyear: Int = 1991
    var age = ageCalculator(currentYear, birthyear)

    println(name+" "+nameString+", Birthyear: "+birthyear)
    println("Your age is: "+age)
    println("Is your birthyear a leap year? "+isBirthyearALeapyear(birthyear))

    birthyear = 2016
    println("Birthyear: "+birthyear)
    println("Your age is: "+ageCalculator(currentYear, birthyear))
    println("Is your birthyear a leap year? "+isBirthyearALeapyear(birthyear))

    /*
    for (nums in birthyear..currentYear){
        println(nums)
    }
    */
    val car1 = Car("VW", "Golf", 1998)
    println(car1.toString())
    println(car1.year)
    car1.start()*/

}

// Function prints "Hello World!!"
fun printHelloWorld(){
    println("Hello World!!")
}

/*  Function calculates and returns age
    @param1 currentYear
    @param2 birthyear
    @return age
 */
fun ageCalculator(curYear: Int, birYear: Int): Int{
    return (curYear - birYear)
}

/*  Function calculates if birthyear is a leap year
    @param birthyear
    @return boolean
 */
fun isBirthyearALeapyear(birYear: Int): Boolean {
    return birYear % 4 == 0
}

