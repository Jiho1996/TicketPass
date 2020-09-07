package kr.com.ticketpass.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ItemTicketListBinding
import kr.com.ticketpass.model.TicketResponse

class GuestMainAdapter(
    val tickets: MutableList<TicketResponse.TicketInfo> // api 생성되는 대로 DTO 만들어서 붙이기
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var expireSize = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TicketViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_ticket_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ticket = tickets[position]
        // unExpired -> 0, expired -> 1
        if (position < expireSize) {
            (holder as TicketViewHolder).bind(ticket, 0)
        } else {
            (holder as TicketViewHolder).bind(ticket, 1)
        }
    }

    fun addUnExpiredList(tickets: MutableList<TicketResponse.TicketInfo>) {
        expireSize = tickets.size
        this.tickets.clear()
        this.tickets.addAll(tickets)
        notifyDataSetChanged()
    }

    fun addExpiredList(tickets: MutableList<TicketResponse.TicketInfo>) {
        this.tickets.addAll(tickets)
        notifyDataSetChanged()
    }

    class TicketViewHolder(
        private val binding: ItemTicketListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ticket: TicketResponse.TicketInfo, type: Int) {
            binding.apply {
                binding.model = ticket
                if (type == 0) {

                } else {

                }
            }
        }
    }
}