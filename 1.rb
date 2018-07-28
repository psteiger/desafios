#!/usr/bin/env ruby

# Minha ideia original era desenvolver em Python, mas Ruby foi escolhido pois 
# era necessário alterar o próprio vetor, e em Python, strings são imutáveis, 
# enquanto em Ruby são mutáveis. 

def spacesPositions(string, string_len)
  0.upto(string_len-1).select { |i| string[i] == ' ' }
end

def fromPosition(currentPosition, remainingSpaces)
  currentPosition - (remainingSpaces*2)
end

if __FILE__ == $0
  if ARGV.length < 2
    puts "Exemplo de uso: ./1.rb 'Frase com espaco    ' 16"
    return nil
  end

  string = ARGV[0].dup                                    # a string
  string_len = Integer(ARGV[1])                           # tamanho real da str  
  
  spaces_positions = spacesPositions(string, string_len)
  remaining_spaces = spaces_positions.length        
  
  to_pos = (string_len-1) + (remaining_spaces*2)

  while remaining_spaces > 0
    from_pos = fromPosition(to_pos, remaining_spaces)

    if from_pos != spaces_positions.last 
      string[to_pos] = string[from_pos]                   # just copy, O(1)
    else # write &32, all O(1)
      spaces_positions.pop  
      remaining_spaces -= 1 
      string[to_pos] = '2'  
      string[to_pos-1] = '3'
      string[to_pos-2] = '&'
      to_pos -= 2
    end

    to_pos -= 1
  end

  puts string
end
