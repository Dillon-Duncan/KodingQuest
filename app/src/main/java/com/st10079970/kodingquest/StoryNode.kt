package com.st10079970.kodingquest

data class StoryNode(val storyNumber: Int, val storyText: String, var next: StoryNode? = null)

class LinkedList {
    var head: StoryNode? = null

    fun add(storyNumber: Int, storyText: String) {
        val newNode = StoryNode(storyNumber, storyText)
        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }

    fun toList(): List<StoryNode> {
        val list = mutableListOf<StoryNode>()
        var current = head
        while (current != null) {
            list.add(current)
            current = current.next
        }
        return list
    }

}
