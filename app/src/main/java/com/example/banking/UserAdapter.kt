package com.example.banking

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(list: ArrayList<UserModel>): RecyclerView.Adapter<UserAdapter.UserDataHolder>() {
    var modelList = list
    lateinit var context: Context
    lateinit var database: Database

    class UserDataHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.txtname)
        var bank = itemView.findViewById<TextView>(R.id.txtbank)
        var type = itemView.findViewById<TextView>(R.id.txttype)
        var account = itemView.findViewById<TextView>(R.id.txtaccnum)
        var amount = itemView.findViewById<TextView>(R.id.txtamt)
        var time = itemView.findViewById<TextView>(R.id.txttime)
        var date = itemView.findViewById<TextView>(R.id.txtdate)
        var btnupdate = itemView.findViewById<Button>(R.id.btn_update)
        var btndelete = itemView.findViewById<Button>(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataHolder {
        context = parent.context
        return UserDataHolder(LayoutInflater.from(context).inflate(R.layout.rcv_item,parent,false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: UserDataHolder, position: Int) {
        holder.name.text = modelList.get(position).name
        holder.bank.text = modelList.get(position).bank
        holder.type.text = modelList.get(position).type
        holder.account.text = modelList.get(position).account.toString()
        holder.amount.text = modelList.get(position).amount.toString()
        holder.date.text = modelList.get(position).date
        holder.time.text = modelList.get(position).time
        database = Database(context)

        holder.btnupdate.setOnClickListener{
            var dialog = Dialog(context)
            dialog.setContentView(R.layout.update_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
            var edtname = dialog.findViewById<EditText>(R.id.updname)
            var edtbank = dialog.findViewById<EditText>(R.id.updbank)
            var edttype = dialog.findViewById<EditText>(R.id.updtype)
            var edtamount = dialog.findViewById<EditText>(R.id.updtamt)
            var edtaccount = dialog.findViewById<EditText>(R.id.updaccount)
            var edtbtn = dialog.findViewById<Button>(R.id.upd_btnupdate)

            edtname.setText(modelList.get(position).name.toString())
            edtbank.setText(modelList.get(position).bank.toString())
            edttype.setText(modelList.get(position).type.toString())
            edtamount.setText(modelList.get(position).amount.toString())
            edtaccount.setText(modelList.get(position).account.toString())

            edtbtn.setOnClickListener{
                database.updateData(
                    modelList.get(position).id,
                            edtname.text.toString(),
                            edtbank.text.toString(),
                            edttype.text.toString(),
                            edtaccount.text.toString().toInt(),
                            edtamount.text.toString().toInt(),
                )
                MainActivity.Updated()
                dialog.dismiss()
            }
            dialog.show()
        }

        holder.btndelete.setOnClickListener{
            database.deleteData(modelList.get(position).id)
            MainActivity.Updated()
        }
    }

    override fun getItemCount(): Int {
        return  modelList.size
    }
    fun update(list: ArrayList<UserModel>) {
        modelList = list
        notifyDataSetChanged()
    }
}