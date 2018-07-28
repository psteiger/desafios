package com.cesar.psteiger.searchonlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.ArrayList

class ItemAdapter(ctx: Context,
                  layoutResource: Int,
                  private val items: ArrayList<String>) : ArrayAdapter<String>(ctx, layoutResource, items), Filterable {

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    private var filteredItems: ArrayList<String> = items
    private val filter: ItemFilter = ItemFilter()

    override fun getCount() = filteredItems.size

    override fun getItem(position: Int) = filteredItems[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val item = filteredItems[position]
        return inflater.inflate(R.layout.list_item, parent, false).apply {
            name.text = item
        }
    }

    override fun getFilter(): Filter = filter

    private fun String.upToTwoThirdsJumbled(other: String): Boolean {
        var jumbled = 0
        forEachIndexed { index, char ->
            if (char != other[index]) jumbled++
        }

        return jumbled < (length*2) / 3
    }

    private fun String.isOneTypoAway(bigger: String): Boolean {
        var result = true

        forEachIndexed { i, c ->
            if (c != bigger[i] && c != bigger[i+1])
                result = false
        }

        return result
    }


    private fun String.isTypo(other: String): Boolean = when {
        length == other.length-1 -> isOneTypoAway(other)
        length == other.length+1 -> other.isOneTypoAway(this)
        length == other.length -> {
            var numDiffs = 0
            forEachIndexed { i, c ->
                if (c != other[i]) numDiffs++
            }

            numDiffs == 1
        }
        else -> false
    }


    private fun String.isPermutation(other: String) =
            length == other.length &&
            toCharArray().sort() == other.toCharArray().sort() &&
                    get(0) == other[0] &&
                    (length <= 3 || upToTwoThirdsJumbled(other))

    private fun String.matchesQuery(text: String): Boolean {
        if (isNullOrBlank()) return false

        val isPermutation = withoutAccents.isPermutation(text.withoutAccents)
        val isOneTypo = withoutAccents.isTypo(text.withoutAccents)

        Log.e("matchesQuery", "perm = $isPermutation ; typo = $isOneTypo" )
        return (isPermutation && !isOneTypo) || (!isPermutation && isOneTypo)
    }

    private inner class ItemFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): Filter.FilterResults {

            Log.e("performFiltering", "text = $constraint")

            val results = Filter.FilterResults()
            val list = items.filter { constraint.isBlank() || it.matchesQuery(constraint.toString()) }
            val count = list.size

            results.values = list ?: arrayListOf<String>()
            results.count = count
            
            return results
        }

        override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
            filteredItems = results.values as ArrayList<String>
            notifyDataSetChanged()
        }
    }

    private val String.withoutAccents: String
        get() = replace('À', 'A')
                .replace('Á', 'A')
                .replace('Â', 'A')
                .replace('Ã', 'A')
                .replace('Ä', 'A')
                .replace('È', 'E')
                .replace('É', 'E')
                .replace('Ê', 'E')
                .replace('Ë', 'E')
                .replace('Í', 'I')
                .replace('Ì', 'I')
                .replace('Î', 'I')
                .replace('Ï', 'I')
                .replace('Ù', 'U')
                .replace('Ú', 'U')
                .replace('Û', 'U')
                .replace('Ü', 'U')
                .replace('Ò', 'O')
                .replace('Ó', 'O')
                .replace('Ô', 'O')
                .replace('Õ', 'O')
                .replace('Ö', 'O')
                .replace('Ñ', 'N')
                .replace('Ç', 'C')
                .replace('ª', 'A')
                .replace('º', 'O')
                .replace('§', 'S')
                .replace('³', '3')
                .replace('²', '2')
                .replace('¹', '1')
                .replace('à', 'a')
                .replace('á', 'a')
                .replace('â', 'a')
                .replace('ã', 'a')
                .replace('ä', 'a')
                .replace('è', 'e')
                .replace('é', 'e')
                .replace('ê', 'e')
                .replace('ë', 'e')
                .replace('í', 'i')
                .replace('ì', 'i')
                .replace('î', 'i')
                .replace('ï', 'i')
                .replace('ù', 'u')
                .replace('ú', 'u')
                .replace('û', 'u')
                .replace('ü', 'u')
                .replace('ò', 'o')
                .replace('ó', 'o')
                .replace('ô', 'o')
                .replace('õ', 'o')
                .replace('ö', 'o')
                .replace('ñ', 'n')
                .replace('ç', 'c')
}
