#!/usr/bin/env ruby

class Node
  attr_accessor :val, :next

  def initialize(val, next_node)
      @val = val
      @next = next_node
  end
end

class LinkedList

  def initialize(val)
    @head = Node.new(val, nil)
  end

  def add(val)
    current = @head
    while current.next != nil
      current = current.next
    end
    current.next = Node.new(val, nil)
  end

  def delete(val)
    current.next = @head
    if current.val = val
      @head = current.next
    else
      while (current.next != nil) && (current.next.val != val)
        current = current.next
      end
      unless current.next == nil
        current.next = current.next.next
      end
    end
  end

  def return_list
    elements = []
    current = @head
    while current.next != nil
      elements << current
      current = current.next
    end
    elements << current
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
  linkedlist = LinkedList.new(ARGV[0])  # at least 1 element is obligatory

  ARGV.each_with_index { |val, i| 
    linkedlist.add(val) if i != 0       # 0 already processed
  }

  linkedlist.remove_dup
  
  puts linkedlist.pretty_print
end
