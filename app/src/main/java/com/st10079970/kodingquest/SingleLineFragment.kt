package com.st10079970.kodingquest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class SingleLineFragment : Fragment() {
    private lateinit var singleLineText: TextView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private var currentIndex = 0
    private lateinit var sortedStories: List<StoryNode>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_line_text, container, false)
        singleLineText = view.findViewById(R.id.single_line_text)
        previousButton = view.findViewById(R.id.previous_button)
        nextButton = view.findViewById(R.id.next_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linkedList = LinkedList()
        val stories = listOf(
            3 to "With every line of code mastered, Alex gains experience points, levelling up and unlocking new abilities like Debugging Dash and Algorithmic Aura.",
            12 to "The tale of Alex, the IT student-turned-digital-legend, is forever etched in the annals of Cybersphere, inspiring aspiring programmers to pursue their dreams.",
            4 to "The Firewall Fortress looms ahead, its defenses formidable, but Alex's mastery of cybersecurity allows them to breach the walls with a series of perfectly timed hacks.",
            2 to "Armed with a trusty keyboard and a digital sword, Alex enters the Coding Caverns, where bugs and glitches guard the treasures of programming wisdom.",
            1 to "In the virtual realm of Cybersphere, our hero, Alex, a determined IT student, embarks on an epic quest for knowledge.",
            7 to "Along the journey, Alex forges alliances with NPC coders, forming a guild known as 'Syntax Sentinels,' united by their dedication to digital mastery.",
            10 to "Victory is hard-won, but Alex's leadership and IT prowess lead to the defeat of the Malware Marauders, restoring peace to Cybersphere.",
            11 to "Celebrated as a digital hero, Alex stands at the forefront of innovation, using the knowledge gained to create groundbreaking applications that shape the future of technology.",
            9 to "In a climactic battle, Alex and the Syntax Sentinels engage in a virtual war, using complex algorithms and strategic thinking to outwit the Malware Marauders' schemes.",
            5 to "Inside the fortress, a virtual reality riddle awaits – a puzzle that can only be solved by applying principles of encryption and decryption learned during countless late-night study sessions.",
            6 to "Emerging victorious, Alex discovers the hidden Repository of the Ancients, a collection of long-lost IT texts rumored to contain the ultimate programming language.",
            8 to "The guild faces its nemesis in the form of the Malware Marauders, a rival group aiming to spread chaos and disrupt the digital realm."
        )

        for (story in stories) {
            linkedList.add(story.first, story.second)
        }

        linkedList.head = mergeSort(linkedList.head)

        sortedStories = linkedList.toList().sortedBy { it.storyNumber }
        updateText()

        previousButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex--
                updateText()
            }
        }

        nextButton.setOnClickListener {
            if (currentIndex < sortedStories.size - 1) {
                currentIndex++
                updateText()
            }
        }

        singleLineText.setOnClickListener {
            findNavController().navigate(R.id.action_singleLineFragment_to_longScriptFragment)
        }
    }

    fun mergeSort(head: StoryNode?): StoryNode? {
        if (head?.next == null) return head

        val middle = getMiddle(head)
        val nextOfMiddle = middle?.next
        middle?.next = null

        val left = mergeSort(head)
        val right = mergeSort(nextOfMiddle)

        return sortedMerge(left, right)
    }

    fun getMiddle(head: StoryNode?): StoryNode? {
        var slow = head
        var fast = head?.next
        while (fast != null) {
            fast = fast.next
            if (fast != null) {
                slow = slow?.next
                fast = fast.next
            }
        }
        return slow
    }

    fun sortedMerge(a: StoryNode?, b: StoryNode?): StoryNode? {
        if (a == null) return b
        if (b == null) return a

        var result: StoryNode?
        if (a.storyNumber <= b.storyNumber) {
            result = a
            result.next = sortedMerge(a.next, b)
        } else {
            result = b
            result.next = sortedMerge(a, b.next)
        }
        return result
    }

    private fun updateText() {
        singleLineText.text = "${sortedStories[currentIndex].storyNumber}. ${sortedStories[currentIndex].storyText}"
    }
}