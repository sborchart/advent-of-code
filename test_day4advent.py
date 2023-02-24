import re
class TestDay4Advent:

    def day4_part1(self, input_file):
        print('reading file: ' + input_file)
        regex_pattern = re.compile("(.+)-(.+),(.+)-(.+)")
        with open(input_file, 'r') as file:
            totalDupes = 0
            for line in file:
                match = regex_pattern.match(line)
                lowerRange1 = int(match.group(1))
                higherRange1 = int(match.group(2))
                lowerRange2 = int(match.group(3))
                higherRange2 = int(match.group(4))
                if(
                    ((lowerRange1 >= lowerRange2) and (higherRange1 <= higherRange2)) or
                    ((lowerRange2 >= lowerRange1) and (higherRange2 <= higherRange1))
                ):
                    totalDupes += 1
        print("total fully contained duplications are: ")
        print(totalDupes)
        return totalDupes
                


    def day4_part2(self, input_file):
        print('reading file: ' + input_file)
        regex_pattern = re.compile("(.+)-(.+),(.+)-(.+)")
        with open(input_file, 'r') as file:
            totalDupes = 0
            for line in file:
                match = regex_pattern.match(line)
                num1 = int(match.group(1))
                num2 = int(match.group(2))
                num3 = int(match.group(3))
                num4 = int(match.group(4))
                if (
                    ((num1 >= num3) and (num1 <= num4)) or
                    ((num3 >= num1) and (num3 <= num2))
                ):
                    totalDupes += 1
        print("total overlap duplications are: ")
        print(totalDupes)
        return totalDupes


    def test_day4_part1(self):
        assert (self.day4_part1('/Users/sophieborchart/advent_of_code/day4input.txt')) == 444
    
    def test_day4_part2(self):
        assert (self.day4_part2('/Users/sophieborchart/advent_of_code/day4input.txt')) == 801


obj = TestDay4Advent()
obj.day4_part1('/Users/sophieborchart/advent_of_code/day4input.txt')
obj.day4_part2('/Users/sophieborchart/advent_of_code/day4input.txt')