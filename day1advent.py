class day1advent:
    def __init__(self, input_file) -> None:
        self.input_file = input_file

    def day1_part1(self):
        print('reading file: ' + self.input_file)
        with open(self.input_file, 'r') as file:
            biggest_value = 0
            current_value = 0
            for line in file:
                if len(line.strip()) != 0:
                    current_value += int(line)
                else:
                    if current_value > biggest_value:
                        biggest_value = current_value
                    current_value = 0
            print('the most calories a single reindeer has is: ')
            print(biggest_value)



    def day1_part2(self):
       print('reading file: ' + self.input_file)
       with open(self.input_file, 'r') as file:
            biggest_values = [0, 0, 0]
            biggest_value = 0
            current_value = 0
            for j in range(0, 3):
                line = file.readline()
                if len(line.strip()) != 0:
                    biggest_values[j] = int(line)
            for line in file:
                if len(line.strip()) != 0:
                    current_value += int(line)
                else:
                    smallest_of_three = min(biggest_values)
                    if current_value > smallest_of_three:
                        biggest_values[biggest_values.index(smallest_of_three)] = current_value
                    current_value = 0
            for i in range(0, 3):
                biggest_value += biggest_values[i]
            print('the total calories the top three reindeer have together is: ')
            print(biggest_value)


obj = day1advent('/Users/sophieborchart/Desktop/day1input.txt')
obj.day1_part1()
obj.day1_part2()