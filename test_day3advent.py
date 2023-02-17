import string

class TestDay3Advent:

    def day3_part1(self, input_file):
        alphabet = list(string.ascii_lowercase) + list(string.ascii_uppercase)
        priority_total = 0
        print('reading file: ' + input_file)
        with open(input_file, 'r') as file:
            for line in file:
                middle = (len(line) - 1) // 2
                compartment1 = line[0 : middle]
                compartment2 = line[middle : len(line)]
                shared_letter = set(compartment1).intersection(compartment2)
                print(shared_letter)
                priority_total += (alphabet.index(list(shared_letter)[0]) + 1)
        print("priority total is: ")
        print(priority_total)
        return priority_total


    def day3_part2(self, input_file):
        alphabet = list(string.ascii_lowercase) + list(string.ascii_uppercase)
        priority_total = 0
        print('reading file: ' + input_file)
        with open(input_file, 'r') as file:
            list_of_three = [0, 0, 0]
            for line in file:
                line = line.strip()
                if list_of_three.__contains__(0):
                    empty_spot = list_of_three.index(0)
                    list_of_three[empty_spot] = line                    
                    if 0 not in list_of_three:
                        shared_letter_set = set(list_of_three[0]).intersection(list_of_three[1], list_of_three[2])
                        shared_letter_char = list(shared_letter_set)[0]
                        priority_total += (alphabet.index(list(shared_letter_char)[0]) + 1)
                else:
                    list_of_three = [line, 0, 0]
        print("priority total is: ")
        print(priority_total)
        return priority_total


    def test_day3_part1(self):
        assert (self.day3_part1('/Users/sophieborchart/advent_of_code/day2input.txt')) == 7766
    
    def test_day3_part2(self):
        assert (self.day3_part2('/Users/sophieborchart/advent_of_code/day2input.txt')) == 2415


obj = TestDay3Advent()
obj.day3_part1('/Users/sophieborchart/advent_of_code/day3input.txt')
obj.day3_part2('/Users/sophieborchart/advent_of_code/day3input.txt')