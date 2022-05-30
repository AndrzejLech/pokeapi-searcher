package andrzej.lech.pokeapisearcher.ui.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import andrzej.lech.pokeapisearcher.R
import andrzej.lech.pokeapisearcher.network.models.Pokemon
import andrzej.lech.pokeapisearcher.ui.MainNavigation

class DetailsFragment : Fragment() {
    private val TAG = "DetailsFragment"

    companion object {
        fun newInstance(pokemon: Pokemon): DetailsFragment {
            val bundle = bundleOf("data" to pokemon)
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val activity = activity

                    if (activity is MainNavigation) {
                        activity.navigateToMain()
                    } else {
                        Log.d(TAG, "This is not MainNavigation")
                    }
                }
            }
        )
    }


}