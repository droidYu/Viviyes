package com.droidyu.viviyes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.droidyu.viviyes.databinding.FragmentWineBinding
import java.util.*

class WineFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentWineBinding>(
            inflater,
            R.layout.fragment_wine,
            container,
            false
        ).apply {
            rv.layoutManager = GridLayoutManager(context, 2)
            val list = mutableListOf<String>()
            repeat(Random().nextInt(10) + 5) {
                list.add("wine $it")
            }
            rv.adapter = MyAdapter(list)
//            rv.addItemDecoration(DividerItemDecoration(context,LinearLayout.HORIZONTAL))
//            rv.addItemDecoration(DividerItemDecoration(context,LinearLayout.VERTICAL))
        }.root
    }

    class MyAdapter(private val list: List<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_wine, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.tv.text = list[position]
        }

        override fun getItemCount(): Int {
            return list.size
        }

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv: TextView = view.findViewById(R.id.tv)
        }
    }
}