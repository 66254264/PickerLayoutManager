package demo.simple.pickerrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_picker.*
import me.simple.picker_recyclerview.PickerLayoutManager
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val mItems = mutableListOf<String>()
    private val mAdapter = PickerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickerRecyclerView.run {
            layoutManager = PickerLayoutManager()
//            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        for (i in 0..20) {
            mItems.add(i.toString())
        }
        mAdapter.notifyDataSetChanged()
    }

    fun reLayout(view: View) {
        val isLoop = checkBoxIsLoop.isChecked
        val visibleCount = etVisibleCount.text.toString().toInt()
        val lm = PickerLayoutManager(PickerLayoutManager.VERTICAL, visibleCount, isLoop)
        pickerRecyclerView.layoutManager = lm
    }

    fun scrollTo(view: View) {
        try {
            val position = etScrollTo.text.toString().toInt()
            pickerRecyclerView.scrollToPosition(position)
        } catch (e: Exception) {
            toast(e.message)
        }
    }

    fun smoothScrollTo(view: View) {
        try {
            val position = etSmoothScrollTo.text.toString().toInt()
            pickerRecyclerView.smoothScrollToPosition(position)
        } catch (e: Exception) {
            toast(e.message)
        }
    }

    private fun toast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    inner class PickerAdapter : RecyclerView.Adapter<PickerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerViewHolder {
            return PickerViewHolder(
                LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.item_picker, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return mItems.size
        }

        override fun onBindViewHolder(holder: PickerViewHolder, position: Int) {
            holder.tvItem.text = mItems[position]
        }

    }

    inner class PickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem = itemView.findViewById<TextView>(R.id.tv_item)
    }
}