#!/usr/bin/env ruby

class Node
  attr_accessor :val, :next

  def initialize(val, next_node)
      @val = val
      @next = next_node
  end

  def to_s
    "Node with value #{val} - Object Id: #{object_id}"
  end
end

class LinkedList
  def initialize(val)
    @head = Node.new(val, nil)
  end

  def hasNode(node)
    current = @head

    while current != nil
      return true if current.equal? node
      current = current.next
    end

    return false
  end

  def intersection(linkedList)
    current = @head
    while current != nil
      return current if linkedList.hasNode(current)
      current = current.next
    end
    return nil
  end

  def add(val)
    current = @head
    while current.next != nil
      current = current.next
    end
    current.next = Node.new(val, nil)
  end

  def addNode(node)
    current = @head

    while current.next != nil
      current = current.next
    end
    current.next = node
  end

  def pretty_print
    current = @head
    while current != nil
      print current.val.to_s + ' '
      current = current.next
    end
  end

  def remove_dup
    current = @head
    while current != nil && current.next != nil
      ptr = current
      while ptr.next != nil
        if current.val == ptr.next.val
          dup = ptr.next
          ptr.next = ptr.next.next
        else
          ptr = ptr.next
        end
      end
      current = current.next
    end
  end
end

if __FILE__ == $0
  intersectingTail = Node.new(5, Node.new(4, Node.new(3, nil)))

  linkedList1 = LinkedList.new(10)  # at least 1 element is obligatory

  linkedList1.add(12)
  linkedList1.add(14)
  linkedList1.addNode(intersectingTail)

  linkedList2 = LinkedList.new(20)  # at least 1 element is obligatory

  linkedList2.add(22)
  linkedList2.add(14) # mesmo valor, objeto distinto
  linkedList2.addNode(intersectingTail)

  puts "First list: " 
  puts linkedList1.pretty_print
  puts "Second list: "
  puts linkedList2.pretty_print
  
  puts "Intersection at node: "
  puts linkedList1.intersection(linkedList2) 
end
