#!/usr/bin/env ruby

def upToTwoThirdsJumbled(string1, string2)
  jumbled = 0
  string1.chars.each_with_index do |char, i|
    jumbled += 1 if char != string2[i]
  end
  return jumbled < (string1.length * 2) / 3
end

def isPermutation(string1, string2)
  return string1.chars.sort == string2.chars.sort &&
    string1[0] == string2[0] && 
    (string1.length <= 3 || upToTwoThirdsJumbled(string1, string2))
end

if __FILE__ == $0
  string1 = ARGV[0]
  string2 = ARGV[1]
  puts isPermutation(string1, string2)
end
