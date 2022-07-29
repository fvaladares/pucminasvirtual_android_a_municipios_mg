package br.pucminas.municipiosmg.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.pucminas.municipiosmg.databinding.ItemViewButtonBinding

class LetterAdapter : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()
    private var _binding: ItemViewButtonBinding? = null
    private val binding get() = _binding!!


    inner class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = binding.buttonItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {

        _binding = ItemViewButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LetterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list[position]
        holder.button.apply {
            text = item.toString()

            setOnClickListener {
                val context = holder.view.context
                val intent = Intent(context, CitiesListActivity::class.java)
                intent.putExtra(
                    CitiesListActivity.LETTER,
                    holder.button.text.toString()
                )
                context.startActivity(intent)
            }
        }


    }

    override fun getItemCount() = list.size
}