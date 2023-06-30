import numpy as np

with open('/Users/sophieborchart/advent_of_code/day11input.txt', 'r') as f:
    lines = f.readlines()
    lines = [entry.strip() for entry in lines]

class Monkey:
    def __init__(self, lines) -> None:
        self.number = int(lines[0][-2])

        start_items_string = lines[1].replace(':', '').replace(',', '')
        self.items = [int(word) for word in start_items_string.split(' ') if word.isdigit()]

        self.operation = lines[2].split(' ')[-2]
        self.operand = lines[2].split(' ')[-1]

        self.test_condition = int(lines[3].split(' ')[-1])
        self.true_monkey = int(lines[4].split(' ')[-1])
        self.false_monkey = int(lines[5].split(' ')[-1])

        self.inspections = 0


    def inspect_items(self):
        for i, item in enumerate(self.items):
            self.inspections += 1
            if self.operation == '+':
                self.items[i] += int(self.operand)
            if self.operation == '*':
                if self.operand.isdigit():
                    self.items[i] *= int(self.operand)
                else:
                    self.items[i] *= item
        
    def reduce_worries(self):
        for i, item in enumerate(self.items):
            self.items[i] = item // 3


    def throw_items(self):
        planned_throws = []
        for item in self.items:
            if item % self.test_condition == 0:
                planned_throws.append((item, self.true_monkey))
            else:
                planned_throws.append((item, self.false_monkey))
        self.items = []
        return planned_throws

    def receive_item(self, item):
        self.items.append(item)

    def print_monkey_items(self):
        print(f"Monkey {self.number}:" + ', '.join([str(item) for item in self.items]))

def solve_part_1(file):
    with open(file, 'r') as f:
        lines = f.readlines()
        lines = [entry.strip() for entry in lines]

    monkeys = []
    while len(lines) > 0:
        monkeys.append(Monkey(lines))
        lines = lines[7:] if len(lines) > 7 else []

    round = 0
    while round < 20:
        round += 1
        for monkey in monkeys:
            monkey.inspect_items()
            monkey.reduce_worries()
            planned_throws = monkey.throw_items()
            for item, target_monkey in planned_throws:
                monkeys[target_monkey].receive_item(item)
    print(f"After round {round}:")
    for monkey in monkeys:
        monkey.print_monkey_items()

    inspections = [monkey.inspections for monkey in monkeys]
    print(inspections)
    print(np.prod(sorted(inspections, reverse=True)[:2]))

solve_part_1('/Users/sophieborchart/advent_of_code/day11input.txt')