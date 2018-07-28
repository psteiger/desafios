#!/usr/bin/env ruby

def isOneTypoAway(smaller, bigger)
  smaller.chars.each_with_index { |char, i|
    if char != bigger[i] && char != bigger[i+1]
      return false
    end
  }
  return true
end

def isTypo(original_string, typo_string)
  original_as_char_array = original_string.chars
  typo_as_char_array = typo_string.chars

  if original_string.length == typo_string.length + 1
    return isOneTypoAway(typo_string, original_string)
  elsif original_string.length == typo_string.length - 1
    return isOneTypoAway(original_string, typo_string)
  elsif original_string.length == typo_string.length
    num_diffs = 0
    original_as_char_array.each_with_index { |char, i|
      num_diffs += 1 if char != typo_as_char_array[i]
    }
    return num_diffs == 1
  else
    return false
  end
end

if __FILE__ == $0
  if ARGV.length < 2
    puts "Exemplo de uso: ./3.rb casa cama"
    return nil
  end

  string1 = ARGV[0]
  string2 = ARGV[1]
  puts isTypo(string1, string2)
end
