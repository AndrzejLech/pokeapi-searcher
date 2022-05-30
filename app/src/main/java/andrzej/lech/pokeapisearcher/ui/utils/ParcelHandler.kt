package andrzej.lech.pokeapisearcher.ui.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

fun addParcelToFragment(destination: Fragment, data: Serializable) {
    val bundle = Bundle()
    bundle.putSerializable("data", data)
    destination.arguments = bundle
}