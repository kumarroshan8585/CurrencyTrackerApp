//package com.example.currencytracker.fragment
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.widget.AppCompatButton
//import androidx.navigation.fragment.navArgs
//import com.bumptech.glide.Glide
//import com.example.currencytracker.R
//import com.example.currencytracker.databinding.FragmentDetailsBinding
//import com.example.currencytracker.databinding.FragmentWatchBinding
//import com.example.currencytracker.models.CryptoCurrency
//
//
//class DetailsFragment : Fragment() {
//
//    lateinit var binding: FragmentDetailsBinding
//
//    private val item: DetailsFragmentArgs by navArgs()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding= FragmentDetailsBinding.inflate(layoutInflater)
//
//        val data: CryptoCurrency=item.data!!
//
//        setUpDetails(data)
//        loadChart(data)
//
//        setButtonOnClick(data)
//        return binding.root
//    }
//
//    private fun setButtonOnClick(item: CryptoCurrency) {
//
//        val oneMonth=binding.button
//        val oneWeek=binding.button1
//        val oneDay=binding.button2
//        val fourHour=binding.button3
//        val oneHour=binding.button4
//        val fiftMin=binding.button5
//
//        val clickListener=View.OnClickListener {
//            when(it.id){
//                fiftMin.id -> loadChartData(it, "15m", item, oneDay, oneWeek, oneMonth, fourHour, oneHour)
//                oneHour.id -> loadChartData(it, "1Hr", item, oneDay, oneWeek, oneMonth, fourHour, fiftMin)
//                fourHour.id -> loadChartData(it, "4Hr", item, oneDay, oneWeek, oneMonth, fiftMin, oneHour)
//                oneDay.id -> loadChartData(it, "1D", item, fiftMin, oneWeek, oneMonth, fourHour, oneHour)
//                oneWeek.id -> loadChartData(it, "1W", item, oneDay, fiftMin, oneMonth, fourHour, oneHour)
//                oneMonth.id -> loadChartData(it, "1M", item, oneDay, oneWeek, fiftMin, fourHour, oneHour)
//            }
//        }
//        fiftMin.setOnClickListener(clickListener)
//        oneHour.setOnClickListener(clickListener)
//        fourHour.setOnClickListener(clickListener)
//        oneDay.setOnClickListener(clickListener)
//        oneWeek.setOnClickListener(clickListener)
//        oneMonth.setOnClickListener(clickListener)
//
//    }
//
//
//    private fun disableButton(oneDay: AppCompatButton, oneMonth: AppCompatButton, oneWeek: AppCompatButton, fourHour: AppCompatButton, oneHour: AppCompatButton) {
//        oneDay.background=null
//        oneMonth.background=null
//        oneWeek.background=null
//        fourHour.background=null
//        oneHour.background=null
//    }
//
//    private fun loadChartData(
//        it: View?,
//        s: String,
//        item: CryptoCurrency,
//        oneDay: AppCompatButton,
//        oneWeek: AppCompatButton,
//        oneMonth: AppCompatButton,
//        fourHour: AppCompatButton,
//        oneHour: AppCompatButton
//    ) {
//
//        disableButton(oneDay, oneMonth, oneWeek, fourHour, oneHour)
//        it!!.setBackgroundResource(R.drawable.active_button)
//        binding.detaillChartWebView.settings.javaScriptEnabled=true  //This line enables JavaScript in the WebView. Enabling JavaScript is necessary if
//        // the chart being loaded requires JavaScript to function properly.
//        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)  //This line sets the layer type of the WebView to software. This might be done for
//        // performance reasons or to avoid certain rendering issues.
//
//        binding.detaillChartWebView.loadUrl(  //loads the chart URL into the WebView
//            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + item.symbol
//                .toString() + "USD&interval="+s +"&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
//        )
//    }
//
//    private fun loadChart(item: CryptoCurrency) {
//
//        binding.detaillChartWebView.settings.javaScriptEnabled=true  //This line enables JavaScript in the WebView. Enabling JavaScript is necessary if
//        // the chart being loaded requires JavaScript to function properly.
//        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)  //This line sets the layer type of the WebView to software. This might be done for
//        // performance reasons or to avoid certain rendering issues.
//
//        binding.detaillChartWebView.loadUrl(  //loads the chart URL into the WebView
//            "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + item.symbol
//                .toString() + "USD&interval=D&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
//        )
//
//    }
//
//    private fun setUpDetails(data: CryptoCurrency) { //loadChart, is responsible for loading a chart of a cryptocurrency item in a WebView.
//        binding.detailSymbolTextView.text=data.symbol
//
//        Glide.with(requireContext()).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+"${data.id}"+".png")
//            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
//            .into(binding.detailImageView)
//
//        binding.detailPriceTextView.text = "%.4f".format(data.quotes!![0].price)
//
//        if(data.quotes!![0].percentChange24h > 0) {
//            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
//            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
//            binding.detailChangeTextView.text = "+ %.2f%%".format(data.quotes[0].percentChange24h)
//        } else {
//            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
//            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
//            binding.detailChangeTextView.text = "%.2f%%".format(data.quotes[0].percentChange24h)
//        }
//    }
//
//
//}

package com.example.currencytracker.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.currencytracker.R
import com.example.currencytracker.databinding.FragmentDetailsBinding

import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.currencytracker.models.CryptoCurrency

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val item: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val data: CryptoCurrency = item.data!!

        setUpDetails(data)
        loadChart(data)

        setButtonOnClick(data)
        return binding.root
    }

    private fun setButtonOnClick(item: CryptoCurrency) {
        val oneMonth = binding.button
        val oneWeek = binding.button1
        val oneDay = binding.button2
        val fourHour = binding.button3
        val oneHour = binding.button4
        val fiftMin = binding.button5

        val clickListener = View.OnClickListener {
            when (it.id) {
                fiftMin.id -> loadChartData("15m", item, it, oneDay, oneWeek, oneMonth, fourHour, oneHour)
                oneHour.id -> loadChartData("1Hr", item, it, oneDay, oneWeek, oneMonth, fourHour, fiftMin)
                fourHour.id -> loadChartData("4Hr", item, it, oneDay, oneWeek, oneMonth, fiftMin, oneHour)
                oneDay.id -> loadChartData("1D", item, it, fiftMin, oneWeek, oneMonth, fourHour, oneHour)
                oneWeek.id -> loadChartData("1W", item, it, oneDay, fiftMin, oneMonth, fourHour, oneHour)
                oneMonth.id -> loadChartData("1M", item, it, oneDay, oneWeek, fiftMin, fourHour, oneHour)
            }
        }
        fiftMin.setOnClickListener(clickListener)
        oneHour.setOnClickListener(clickListener)
        fourHour.setOnClickListener(clickListener)
        oneDay.setOnClickListener(clickListener)
        oneWeek.setOnClickListener(clickListener)
        oneMonth.setOnClickListener(clickListener)
    }

    private fun disableButton(vararg buttons: AppCompatButton) {
        buttons.forEach { it.background = null }
    }

    private fun loadChartData(
        interval: String,
        item: CryptoCurrency,
        selectedButton: View,
        vararg otherButtons: AppCompatButton
    ) {
        disableButton(*otherButtons)
        selectedButton.setBackgroundResource(R.drawable.active_button)
        configureWebView()
        binding.detaillChartWebView.loadUrl(getChartUrl(item.symbol, interval))
    }

    private fun loadChart(item: CryptoCurrency) {
        configureWebView()
        binding.detaillChartWebView.loadUrl(getChartUrl(item.symbol, "D"))
    }

    private fun configureWebView() {
        binding.detaillChartWebView.settings.javaScriptEnabled = true
        binding.detaillChartWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        binding.detaillChartWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false // This ensures the WebView handles the URL loading
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // You can add a loading spinner here if needed
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Dismiss the loading spinner here if needed
            }
        }
    }

    private fun getChartUrl(symbol: String, interval: String): String {
        return "https://s.tradingview.com/widgetembed/?frameElementId=tradingview_76d87&symbol=" + symbol +
                "USD&interval=" + interval + "&hidesidetoolbar=1&hidetoptoolbar=1&symboledit=1&saveimage=1&toolbarbg=F1F3F6&studies=[]&hideideas=1&theme=Dark&style=1&timezone=Etc%2FUTC&studies_overrides={}&overrides={}&enabled_features=[]&disabled_features=[]&locale=en&utm_source=coinmarketcap.com&utm_medium=widget&utm_campaign=chart&utm_term=BTCUSDT"
    }

    private fun setUpDetails(data: CryptoCurrency) {
        binding.detailSymbolTextView.text = data.symbol

        Glide.with(requireContext()).load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + "${data.id}" + ".png")
            .thumbnail(Glide.with(requireContext()).load(R.drawable.spinner))
            .into(binding.detailImageView)

        binding.detailPriceTextView.text = "%.4f".format(data.quotes!![0].price)

        if (data.quotes!![0].percentChange24h > 0) {
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.green))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_up)
            binding.detailChangeTextView.text = "+ %.2f%%".format(data.quotes[0].percentChange24h)
        } else {
            binding.detailChangeTextView.setTextColor(requireContext().resources.getColor(R.color.red))
            binding.detailChangeImageView.setImageResource(R.drawable.ic_caret_down)
            binding.detailChangeTextView.text = "%.2f%%".format(data.quotes[0].percentChange24h)
        }
    }
}
