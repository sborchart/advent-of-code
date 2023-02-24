import re
class TestDay5Advent:

#                     [L]     [H] [W]
#                 [J] [Z] [J] [Q] [Q]
# [S]             [M] [C] [T] [F] [B]
# [P]     [H]     [B] [D] [G] [B] [P]
# [W]     [L] [D] [D] [J] [W] [T] [C]
# [N] [T] [R] [T] [T] [T] [M] [M] [G]
# [J] [S] [Q] [S] [Z] [W] [P] [G] [D]
# [Z] [G] [V] [V] [Q] [M] [L] [N] [R]
#  1   2   3   4   5   6   7   8   9 

    def day5_part1(self, input_file):
        print('reading file: ' + input_file)
        stack1 = ["Z", "J", "N", "W", "P", "S"]
        stack2 = ["G", "S", "T"]
        stack3 = ["V", "Q", "R", "L", "H"]
        stack4 = ["V", "S", "T", "D"]
        stack5 = ["Q", "Z", "T", "D", "B", "M", "J"]
        stack6 = ["M", "W", "T", "J", "D", "C", "Z", "L"]
        stack7 = ["L", "P", "M", "W", "G", "T", "J"]
        stack8 = ["N", "G", "M", "T", "B", "F", "Q", "H"]
        stack9 = ["R", "D", "G", "C", "P", "B", "Q", "W"]

        stackOfStacks = [stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9]

        regex_pattern = re.compile("move (.+) from (.+) to (.+)")

        with open(input_file, 'r') as file:
            for line in file:
                match = regex_pattern.match(line)
                if(match):
                    numObjectsToMove = (int)(match.group(1))
                    stackLosingNum = (int)(match.group(2))
                    stackGainingNum = (int)(match.group(3))
                    for i in range(0, numObjectsToMove):
                        stackLosing = stackOfStacks[stackLosingNum - 1]
                        letterToMove = stackLosing[len(stackLosing) - 1]
                        stackLosing.pop()
                        stackGaining = stackOfStacks[stackGainingNum - 1]
                        stackGaining.append(letterToMove)
        answer = ""
        for i in range (0, len(stackOfStacks)):
            singleStack = stackOfStacks[i]
            answer += singleStack[len(singleStack) - 1]
        print(answer)
        return answer
        

    # def day5_part2(self, input_file):

    def test_day5_part1(self):
        assert (self.day4_part1('/Users/sophieborchart/advent_of_code/day5input.txt')) == "MQTPGLLDN"
    
    # def test_day5_part2(self):
    #     assert (self.day4_part2('/Users/sophieborchart/advent_of_code/day5input.txt')) == 801


obj = TestDay5Advent()
obj.day5_part1('/Users/sophieborchart/advent_of_code/day5input.txt')
# obj.day5_part2('/Users/sophieborchart/advent_of_code/day5input.txt')