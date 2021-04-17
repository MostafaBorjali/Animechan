package ir.borjali.animechan.presentation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.borjali.animechan.R
import ir.borjali.animechan.domain.model.Quote
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var quoteList: List<Quote>
    private val quoteViewModel by viewModel<QuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
        setUpViewModel()

    }

    private fun setUpUi() {
        swipeRefreshLayout.setOnRefreshListener {
            quoteViewModel.getRandomQuotes()

        }
        animeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                quoteText.text = quoteList[position].quote

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun setUpViewModel() {
        with(quoteViewModel) {
            quotesList.observe(this@MainActivity, {
                initSpinner(it)
            })
            showError.observe(this@MainActivity, {
                if (!it.equals("")){
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            })
            showLoading.observe(this@MainActivity, {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                }
            })
        }
    }

    private fun initSpinner(quotes: List<Quote>) {
        val animeTitle = ArrayList<String>()
        quoteList = quotes
        quotes.forEach { quote ->
            animeTitle.add(quote.anime)
        }
        val adapter = ArrayAdapter(
            this@MainActivity,
            R.layout.support_simple_spinner_dropdown_item, animeTitle
        )
        animeSpinner.adapter = adapter

    }
}