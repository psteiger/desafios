package com.cesar.psteiger.searchonlist

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var item: ArrayList<String> = arrayListOf()
    private var itemAdapter: ItemAdapter? = null
    private var searchString = ""
        set(value) {
            Log.e("TAG", "$searchString")
            field = value
            itemAdapter?.filter?.filter(value)
        }
    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item.add("abc")
        item.add("abc def ")
        item.add("de z g")
        item.add("casa")
        item.add("cama")
        item.add("carro")
        item.add("casal")
        item.add("caasl")

        itemAdapter = ItemAdapter(this, R.layout.list_item, item)
        list.adapter = itemAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        val searchItem = menu!!.findItem(R.id.action_search_list_menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchItem?.let { searchView = searchItem.actionView as SearchView }
        searchView?.let {
            it.setSearchableInfo(searchManager.getSearchableInfo(componentName))

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String) = true
                override fun onQueryTextChange(newText: String): Boolean {
                    searchString = newText
                    return true
                }
            }
            it.setOnQueryTextListener(queryTextListener)
        }

        return super.onCreateOptionsMenu(menu)
    }
}
