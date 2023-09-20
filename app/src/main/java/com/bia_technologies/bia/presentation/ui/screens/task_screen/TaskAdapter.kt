package com.bia_technologies.bia.presentation.ui.screens.task_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bia_technologies.bia.data.mappers.OrderMapper
import com.bia_technologies.bia.databinding.OrderCardBinding
import com.bia_technologies.bia.domain.models.TaskModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskAdapter @Inject constructor() :
    ListAdapter<TaskModel, TaskAdapter.TaskViewHolder>(DiffCallback) {

    inner class TaskViewHolder(private val binding: OrderCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
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
                    val action = TaskFragmentDirections.actionTaskFragmentToDetailTaskFragment(taskModelDto, id = taskModel.id)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = OrderCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)
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
    }


}