package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bia_technologies.bia.data.mappers.OrderMapper
import com.bia_technologies.bia.databinding.OrderCardBinding
import com.bia_technologies.bia.databinding.OrderCardFinishedBinding
import com.bia_technologies.bia.domain.models.TaskModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskAdapter @Inject constructor() :
    ListAdapter<TaskModel, RecyclerView.ViewHolder>(DiffCallback) {

    inner class OrderCardBindingViewHolder(private val binding: OrderCardBinding) :
        RecyclerView.ViewHolder(binding.root), TaskItemBinding {
        override fun bind(taskModel: TaskModel) {
            with(binding) {
                taskType.text = taskModel.type
                orderDateTitle.text = taskModel.orderDate
                orderTimeTitle.text = taskModel.arrivalTime
                startPointTextview.text = taskModel.startingPoint
                endPointTextview.text = taskModel.endPoint
                detailTextview.text = taskModel.orderDetails
                detailPaymentTextview.text = taskModel.paymentOptions
                currentTaskTitle.visibility = if (taskModel.currentOrder) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                checkDetailsButton.setOnClickListener {
                    val taskModelDto = OrderMapper().convertTaskModelToDto(taskModel)
                    val action = TaskFragmentDirections.actionTaskFragmentToDetailTaskFragment(
                        taskModelDto,
                        id = taskModel.id
                    )
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    inner class OrderCardFinishedBindingViewHolder(private val binding: OrderCardFinishedBinding) :
        RecyclerView.ViewHolder(binding.root), TaskItemBinding {
        override fun bind(taskModel: TaskModel) {
            with(binding) {
                taskType.text = taskModel.type
                orderDateTitle.text = taskModel.orderDate
                orderTimeTitle.text = taskModel.arrivalTime
                startPointTextview.text = taskModel.startingPoint
                endPointTextview.text = taskModel.endPoint
            }
        }
    }

//    inner class TaskViewHolder(private val binding: ViewBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(taskModel: TaskModel) {
//
//        }
//        fun bind(taskModel: TaskModel) {
//            with(binding) {
//                taskType.text = taskModel.type
//                orderDateTitle.text = taskModel.orderDate
//                orderTimeTitle.text = taskModel.arrivalTime
//                startPointTextview.text = taskModel.startingPoint
//                endPointTextview.text = taskModel.endPoint
//                detailTextview.text = taskModel.orderDetails
//                detailPaymentTextview.text = taskModel.paymentOptions
//                currentTaskTitle.visibility = if (taskModel.currentOrder) {
//                    View.VISIBLE
//                } else {
//                    View.GONE
//                }
//                checkDetailsButton.setOnClickListener {
//                    val taskModelDto = OrderMapper().convertTaskModelToDto(taskModel)
//                    val action = TaskFragmentDirections.actionTaskFragmentToDetailTaskFragment(
//                        taskModelDto,
//                        id = taskModel.id
//                    )
//                    itemView.findNavController().navigate(action)
//                }
//            }
//        }
//    }

    override fun getItemViewType(position: Int): Int {
        val task = getItem(position)
        return if (task.completedOrder) {
            VIEW_TYPE_COMPLETED
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = if (viewType == VIEW_TYPE_COMPLETED) {
            OrderCardFinishedBinding.inflate(inflater, parent, false)
        } else {
            OrderCardBinding.inflate(inflater, parent, false)
        }
        return when (binding) {
            is OrderCardBinding -> OrderCardBindingViewHolder(binding)
            is OrderCardFinishedBinding -> OrderCardFinishedBindingViewHolder(binding)
            else -> throw IllegalArgumentException("Unsupported binding type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        if (holder is TaskItemBinding) {
            holder.bind(task)
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<TaskModel>() {
            override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
                return oldItem.contactPhone == newItem.contactPhone
            }

        }
        const val VIEW_TYPE_NORMAL = 0
        const val VIEW_TYPE_COMPLETED = 1
    }


}

interface TaskItemBinding {
    fun bind(taskModel: TaskModel)
}