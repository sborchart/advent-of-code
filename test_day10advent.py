class TestDay10Advent:

    def part_one(self, input_file):
        signal_strength = 0
        x_register = 1
        cycle_count = 0
        cycle_check = 20
        for command in input_file.readlines():
            cycle_count += 1
            if command.startswith("addx"):
                cycle_count += 1
                if cycle_count >= cycle_check:
                    signal_strength += x_register * cycle_check
                    cycle_check += 40
                x_register += int(command.split()[1])
        print("Part 1 result: " + str(signal_strength))

    def part_two(self, input_file):
        lit_pixels = []
        x_register = 1
        cycle_count = 0
        print("Part 2 result: ")
        for command in input_file.readlines():
            cycle_count = self.execute_cycles(cycle_count, x_register, lit_pixels)
            if command.startswith("addx"):
                cycle_count = self.execute_cycles(cycle_count, x_register, lit_pixels)
                x_register += int(command.split()[1])


    def execute_cycles(self, cycle_count, x_register, lit_pixels):
        cycle_count += 1
        if x_register <= cycle_count <= x_register + 2:
            lit_pixels.append(cycle_count - 1)
        if cycle_count == 40:
            for idx in range(40):
                print("#", end="") if idx in lit_pixels else print(".", end="")
            print()
            lit_pixels.clear()
            cycle_count = 0
        return cycle_count



puzzle_input = '/Users/sophieborchart/advent_of_code/day10input.txt'
file_task_1 = open(puzzle_input, 'r')
file_task_2 = open(puzzle_input, 'r')
obj = TestDay10Advent()
obj.part_one(file_task_1)
obj.part_two(file_task_2)