package kr.com.ticketpass.host

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ItemTicketHostBinding
import kr.com.ticketpass.model.ConcertInfo
import kr.com.ticketpass.model.TicketResponse
import java.io.Serializable

class HostMainAdapter(
    val context: Context,
    val concerts: MutableList<ConcertInfo>,
    val recyclerView: RecyclerView,
    val pictureType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lastExpandedCardPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TicketViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_ticket_host,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = concerts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ticket = concerts[position]
        if (ticket.expanded) {
            (holder as TicketViewHolder).expandableTicket.isVisible = true
            holder.expandableTicket.setExpanded(true)
        } else {
            (holder as TicketViewHolder).expandableTicket.isVisible = false
            holder.expandableTicket.setExpanded(false)
        }

        holder.bind(concerts[position])
    }

    fun addList(tickets: MutableList<ConcertInfo>) {
        this.concerts.clear()
        this.concerts.addAll(tickets)
        notifyDataSetChanged()
    }

    inner class TicketViewHolder(
        private val binding: ItemTicketHostBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val expandableTicket: LayoutHostTicket = binding.hostExpandTicketLayout

        fun bind(ticket: ConcertInfo) {
            binding.apply {
                binding.model = ticket
            }

            setImageBackground()

            binding.hostExpandConcert.setOnClickListener {
                val intent = Intent(context, HostManageActivity::class.java)
                intent.putExtra("ticket", ticket as Serializable)
                context.startActivity(intent)
            }

            binding.hostExpandEnter.setOnClickListener {
                val intent = Intent(context, HostReservationActivity::class.java)
                context.startActivity(intent)
            }

            binding.root.setOnClickListener {
                if (expandableTicket.isExpanded()) {
                    expandableTicket.setExpanded(false)
                    expandableTicket.toggle()
                    concerts.get(adapterPosition).expanded = false
                } else {
                    expandableTicket.setExpanded(true)
                    concerts.get(adapterPosition).expanded = true
                    expandableTicket.toggle()
                    if (lastExpandedCardPosition !== getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(
                            lastExpandedCardPosition
                        ) != null
                    ) {
                        (recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)!!.itemView.findViewById(
                            R.id.host_expand_ticket_layout
                        ) as LayoutHostTicket).setExpanded(false)
                        concerts.get(lastExpandedCardPosition).expanded = false
                        (recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)!!.itemView.findViewById(
                            R.id.host_expand_ticket_layout
                        ) as LayoutHostTicket).toggle()
                    } else if (lastExpandedCardPosition !== getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(
                            lastExpandedCardPosition
                        ) == null
                    ) {
                        concerts.get(lastExpandedCardPosition).expanded = false
                    }
                    lastExpandedCardPosition = adapterPosition
                }
            }
        }

        fun setImageBackground() {
            when (pictureType) {
                0 ->
                    picassoSet(R.drawable.bg_1_bottom)
                1 ->
                    picassoSet(R.drawable.bg_2_bottom)
                2 ->
                    picassoSet(R.drawable.bg_3_bottom)
                3 ->
                    picassoSet(R.drawable.bg_4_bottom)
            }
        }

        fun picassoSet(middle: Int) {
            Picasso.get().load(middle).fit().into(binding.hostBg)
        }
    }
}