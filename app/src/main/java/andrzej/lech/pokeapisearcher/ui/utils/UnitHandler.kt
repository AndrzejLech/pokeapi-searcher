package andrzej.lech.pokeapisearcher.ui.utils

class UnitHandler {
    fun makeMeters(length: Float): String {
        return (length/10).toString() + " m"
    }

    fun makeKilograms(weight: Float): String {
        return (weight/10).toString() + " kg"
    }
}