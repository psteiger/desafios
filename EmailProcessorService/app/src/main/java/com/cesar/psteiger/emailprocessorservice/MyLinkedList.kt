package com.cesar.psteiger.emailprocessorservice

import android.util.Log

class MyLinkedList<T>(firstElement: T) {

    inner class Node(val value: T, var next: Node?)

    val head = Node(firstElement, null)

    fun add(value: T) {
        var current: Node = head

        while (current.next != null)
            current = current.next!!

        current.next = Node(value, null)

    }

    fun removeDup() {
        var current: Node? = head

        while (current?.next != null) {
            var ptr: Node = current

            while (ptr.next != null) {
                if (current.value == ptr.next!!.value)
                    ptr.next = ptr.next!!.next
                else
                    ptr = ptr.next!!
            }

            current = current.next
        }
    }

    override fun toString(): String {
        var ptr: Node? = head

        var result = ""
        while (ptr != null) {
            result += "${ptr.value} "
            ptr = ptr.next
        }
        return result
    }
}