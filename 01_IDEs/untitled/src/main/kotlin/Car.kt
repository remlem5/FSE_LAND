class Car (var brand: String, var model: String, var year: Int){

    fun start(){
        println("Started")
    }

    override fun toString(): String {
        return (brand +", "+model+", "+year)
    }

}