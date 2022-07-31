package br.pucminas.municipiosmg.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucminas.municipiosmg.R
import br.pucminas.municipiosmg.databinding.ItemViewButtonBinding

class CityAdapter(
    private val cityLetterId: String,
    private val context: Context
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    private val filteredCities: List<String>
    private var _binding: ItemViewButtonBinding? = null
    private val binding get() = _binding!!

    init {
        val cities = context.resources.getStringArray(R.array.cities).toList()
        filteredCities = cities
            .filter { it.startsWith(cityLetterId, ignoreCase = true) }
            .shuffled()
            .take(3)
            .sorted()
    }

    inner class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = binding.buttonItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        _binding = ItemViewButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CityViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = filteredCities[position]
        val context = holder.view.context

        holder.button.text = item

        holder.button.setOnClickListener {
            val queryUrl: Uri =
                Uri.parse("${CityListFragment.SEARCH_PREFIX}$item ${CityListFragment.STATE}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = filteredCities.size
}