class TestDay2Advent:
    # def __init__(self, input_file) -> None:
    #     self.input_file = input_file


    # Rock = A / X      1 point
    # Paper = B / Y     2 points
    # Scissors = C / Z  3 points

    # outcomes
    # 0 if lost
    # 3 if tied
    # 6 if won
        
    def day2_part1(self, input_file):
        print('reading file: ' + input_file)
        with open(input_file, 'r') as file:
            points = 0
            for line in file:
                opponentPlay = line[0]
                myPlay = line[2]
                if myPlay == 'X':
                    points += 1
                    if opponentPlay == 'A':
                        points += 3
                    elif opponentPlay == 'C':
                        points += 6
                elif myPlay == 'Y':
                    points += 2
                    if opponentPlay == 'B':
                        points += 3
                    elif opponentPlay == 'A':
                        points += 6
                else: # myPlay == 'Z'
                    points += 3
                    if opponentPlay == 'C':
                        points += 3
                    elif opponentPlay == 'B':
                        points += 6
        print("total points i have: ")
        print(points)
        return points     

        # need to:
        # X lose
        # Y draw
        # Z win

    def day2_part2(self, input_file):
        print('reading file: ' + input_file)
        with open(input_file, 'r') as file:
            points = 0
            for line in file:
                opponentPlay = line[0]
                myPlay = line[2]
                if opponentPlay == 'A':
                    if myPlay == 'X':
                        # i lose, i play scissors = 3 points
                        points += 3
                    elif myPlay == 'Y':
                        # i draw, i play rock = 1 point
                        points += 4
                    else:
                        # i win, i play paper = 2 points
                        points += 8
                elif opponentPlay == 'B':
                    if myPlay == 'X':
                        # i lose, i play rock
                        points += 1
                    elif myPlay == 'Y':
                        # i draw, i play paper
                        points += 5
                    else:
                        # i win, i play scissors
                        points += 9
                else: # opponentPlay == 'C'
                    if myPlay == 'X':
                        # i lose, i play paper
                        points += 2
                    elif myPlay == 'Y':
                        # i draw, i play scissors
                        points += 6
                    else:
                        # i win, i play rock
                        points += 7

        print("total points i have: ")
        print(points)
        return points


    def test_day2_part1(self):
        assert (self.day2_part1('/Users/sophieborchart/advent_of_code/day2input.txt')) == 13924
    
    def test_day2_part2(self):
        assert (self.day2_part2('/Users/sophieborchart/advent_of_code/day2input.txt')) == 13448


# obj = TestDay2Advent()
# obj.day2_part1('/Users/sophieborchart/advent_of_code/day2input.txt')
# obj.day2_part2('/Users/sophieborchart/advent_of_code/day2input.txt')