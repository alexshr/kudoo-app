package com.petersommerhoff.kudoofinal

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.petersommerhoff.kudoofinal.db.AppDatabase
import com.petersommerhoff.kudoofinal.db.DB
import com.petersommerhoff.kudoofinal.view.main.RecyclerListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

  private lateinit var db: AppDatabase  // Stores an AppDatabase object

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
    db = AppDatabase.getDatabase(applicationContext)
    setUpRecyclerView()

    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show()
    }
  }

  private fun setUpRecyclerView() = with(recyclerViewTodos) {
    GlobalScope.launch {
      val todos = sampleData().toMutableList()
      withContext(Dispatchers.Main) { adapter = RecyclerListAdapter(todos) }
    }
    layoutManager = LinearLayoutManager(this@MainActivity)
    itemAnimator = DefaultItemAnimator()
    addItemDecoration(
        DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
  }

  private suspend fun sampleData() =
      withContext(DB) { db.todoItemDao().loadAllTodos() }
}


