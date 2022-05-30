package andrzej.lech.pokeapisearcher.ui.navigator

import androidx.fragment.app.Fragment
import andrzej.lech.pokeapisearcher.ui.fragments.details.DetailsFragment

enum class Destinations(val screen: Fragment) {
    Details(DetailsFragment())
}