package andrzej.lech.pokeapisearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation
import andrzej.lech.pokeapisearcher.ui.fragments.details.DetailsFragment
import andrzej.lech.pokeapisearcher.ui.fragments.main.MainFragment

class MainActivity : AppCompatActivity(), MainNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun navigateToDetail(pokemon: Pokemon) {
        val fragment = DetailsFragment.newInstance(pokemon)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
                .replace(R.id.container, fragment)
        }
    }

    override fun navigateToMain() {
        val fragment = MainFragment.newInstance()

        supportFragmentManager.commit{
            setReorderingAllowed(true)
                .replace(R.id.container, fragment)
        }
    }
}