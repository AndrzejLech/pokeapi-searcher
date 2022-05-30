package andrzej.lech.pokeapisearcher.ui.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import andrzej.lech.pokeapisearcher.MainActivity
import andrzej.lech.pokeapisearcher.R

class Navigator {
    fun navigateTo(fragment: Fragment, activity: MainActivity) {
        activity.supportFragmentManager.commit {
            setReorderingAllowed(true)
                .replace(R.id.container, fragment)
        }
    }
}

