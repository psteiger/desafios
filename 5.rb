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
  if ARGV.length == 0
    puts "Example: ./5.rb 1 2 3 5 4 3 2"
    return nil
  end
  
  linkedlist = LinkedList.new(ARGV[0])  # at least 1 element is obligatory

  ARGV.each_with_index { |val, i| 
    linkedlist.add(val) if i != 0       # 0 already processed
  }

  linkedlist.remove_dup
  
  puts linkedlist.pretty_print
end
