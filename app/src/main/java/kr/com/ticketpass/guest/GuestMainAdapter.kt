package kr.com.ticketpass.guest

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kr.com.ticketpass.R
import kr.com.ticketpass.databinding.ItemTicketListBinding
import kr.com.ticketpass.model.TicketResponse
import kr.com.ticketpass.util.ConstValue
import kr.com.ticketpass.util.SharedPreferenceManager
import java.lang.Exception

class GuestMainAdapter(
    val context: Context,
    val tickets: MutableList<TicketResponse.TicketInfo>,
    val recyclerView: RecyclerView,
    val pictureType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var lastExpandedCardPosition = 0

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
        if (ticket.expanded) {
            (holder as TicketViewHolder).expandableTicket.isVisible = true
            holder.expandableTicket.setExpanded(true)
        } else {
            (holder as TicketViewHolder).expandableTicket.isVisible = false
            holder.expandableTicket.setExpanded(false)
        }

        holder.bind(tickets[position])
    }

    fun addList(tickets: MutableList<TicketResponse.TicketInfo>) {
        this.tickets.clear()
        this.tickets.addAll(tickets)
        notifyDataSetChanged()
    }

    inner class TicketViewHolder(
        private val binding: ItemTicketListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val expandableTicket: LayoutTicket = binding.expandTicketLayout

        fun bind(ticket: TicketResponse.TicketInfo) {
            binding.apply {
                binding.model = ticket
                binding.expandTicketQr.setImageBitmap(
                    expandableTicket.createQr(
                        ticket.id,
                        SharedPreferenceManager.getStringPref(ConstValue.CONST_USER_ID)
                    )
                )
            }

            setImageBackground()

            binding.root.setOnClickListener {
                if (expandableTicket.isExpanded()) {
                    expandableTicket.setExpanded(false)
                    expandableTicket.toggle()
                    tickets.get(adapterPosition).expanded = false
                } else {
                    expandableTicket.setExpanded(true)
                    tickets.get(adapterPosition).expanded = true
                    expandableTicket.toggle()
                    if (lastExpandedCardPosition !== getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(
                            lastExpandedCardPosition
                        ) != null
                    ) {
                        (recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)!!.itemView.findViewById(
                            R.id.expand_ticket_layout
                        ) as LayoutTicket).setExpanded(false)
                        tickets.get(lastExpandedCardPosition).expanded = false
                        (recyclerView.findViewHolderForAdapterPosition(lastExpandedCardPosition)!!.itemView.findViewById(
                            R.id.expand_ticket_layout
                        ) as LayoutTicket).toggle()
                    } else if (lastExpandedCardPosition !== getAdapterPosition() && recyclerView.findViewHolderForAdapterPosition(
                            lastExpandedCardPosition
                        ) == null
                    ) {
                        tickets.get(lastExpandedCardPosition).expanded = false
                    }
                    lastExpandedCardPosition = adapterPosition
                }
            }
        }

        fun setImageBackground() {
            when (pictureType) {
                0 ->
                    picassoSet(R.drawable.bg_1_top, R.drawable.bg_1_bottom)
                1 ->
                    picassoSet(R.drawable.bg_2_top, R.drawable.bg_2_bottom)
                2 ->
                    picassoSet(R.drawable.bg_3_top, R.drawable.bg_3_bottom)
                3 ->
                    picassoSet(R.drawable.bg_4_top, R.drawable.bg_4_bottom)
            }
        }

        fun picassoSet(top: Int, middle: Int) {
            Picasso.get().load(top).fit().into(binding.guestTopImage)

            Picasso.get().load(middle).fit().into(binding.guestBottomImage)
        }
    }
}