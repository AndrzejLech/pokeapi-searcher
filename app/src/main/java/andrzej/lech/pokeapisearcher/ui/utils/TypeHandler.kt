package andrzej.lech.pokeapisearcher.ui.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import andrzej.lech.pokeapisearcher.R

class TypeHandler {
    fun decideTypeDrawable(context: Context, type: String): Drawable? {
        val drawable: Drawable =
            requireNotNull(ContextCompat.getDrawable(context, R.drawable.round))


        when (type) {
            "normal" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.normal))
            }
            "fire" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.fire))
            }
            "water" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.water))
            }
            "electric" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.electric))
            }
            "grass" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.grass))
            }
            "ice" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.ice))
            }
            "fighting" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.fighting))
            }
            "poison" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.poison))
            }
            "ground" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.ground))
            }
            "flying" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.flying))
            }
            "psychic" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.psychic))
            }
            "bug" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.bug))
            }
            "rock" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.rock))
            }
            "ghost" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.ghost))
            }
            "dragon" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.dragon))
            }
            "dark" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.dark))
            }
            "steel" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.steel))
            }
            "fairy" -> {
                drawable.setTint(ContextCompat.getColor(context, R.color.fairy))
            }
            else -> {}
        }
        return drawable
    }
}