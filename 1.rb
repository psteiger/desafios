#!/usr/bin/env ruby

# Minha ideia original era desenvolver em Python, mas Ruby foi escolhido pois 
# era necessário alterar o próprio vetor, e em Python, strings são imutáveis, 
# enquanto em Ruby são mutáveis. 

def spacesPositions(string, string_len)
  count = []
  0.upto(string_len-1).each { |i|
    if string[i] == ' '
      count.push(i)
    end
  }
  return count
end

def fromPosition(currentPosition, remainingSpaces)
  return currentPosition - (remainingSpaces * 2)
end

if __FILE__ == $0
  string = ARGV[0].dup
  string_len = Integer(ARGV[1])
  spaces_positions = spacesPositions(string, string_len)
  remaining_spaces = spaces_positions.length
  i = (string_len-1) + (remaining_spaces*2)

  while remaining_spaces > 0
    from_position = fromPosition(i, remaining_spaces)

    if from_position != spaces_positions.last
      string[i] = string[from_position]
    else
      spaces_positions.pop # O(1)
      remaining_spaces -= 1
      string[i] = '2'
      string[i-1] = '3'
      string[i-2] = '&'
      i -= 2
    end

    i -= 1
  end

  puts string
end
