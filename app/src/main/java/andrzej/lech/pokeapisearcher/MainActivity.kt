package andrzej.lech.pokeapisearcher

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation
import andrzej.lech.pokeapisearcher.ui.view.details.DetailsFragment
import andrzej.lech.pokeapisearcher.ui.view.main.MainFragment
import andrzej.lech.pokeapisearcher.ui.view.splash.SplashFragment

class MainActivity : AppCompatActivity(), MainNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requireNotNull(supportActionBar).hide()

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashFragment.newInstance())
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

        supportFragmentManager.commit {
            setReorderingAllowed(true)
                .replace(R.id.container, fragment)
        }
    }
}