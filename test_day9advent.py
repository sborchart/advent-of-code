import numpy as np

class TestDay9Advent:

    def update_tail(self, head, tail):
        # dictionary showing how far away the head is and how far the tail needs to move in each direction
        # first pair is head showing 2 up 1 right --> tail follows up 1 right
        # value 1 is up/down, value 2 is right/left. + is up/right, - is down/left
        change_for_tail = {
            (2, 1):(1, 1),
            (1, 2):(1, 1),
            (2, 0):(1, 0),
            (2, -1):(1, -1),
            (1, -2):(1, -1),
            (0, -2):(0, -1),
            (-1, -2):(-1,-1),
            (-2, -1):(-1, -1),
            (-2, 0):(-1, 0),
            (-2, 1):(-1, 1),
            (-1, 2):(-1, 1),
            (0, 2):(0, 1) }
        # calculate difference of distance
        difference = head - tail
        print(change_for_tail.get(tuple(difference)))
        new_tail_position = tail + np.array(change_for_tail.get(tuple(difference), (0, 0)))
        return new_tail_position
    
    # set of tuples to keep track of which coordinates were visited at least once
    def update_head(self, head, direction):
        if direction == 'U':
            head[0] += 1
        elif direction == 'D':
            head[0] -= 1
        elif direction == 'R':
            head[1] += 1
        elif direction == 'L':
            head[1] -= 1
        return head

    def part_one(self):
        with open('/Users/sophieborchart/advent_of_code/day9input.txt', 'r') as f:
            lines = f.readlines()
            # create a list of tuples with instructions on the left and number of movements on the right
            movement_instructions = [(entry.strip().split(' ')[0], int(entry.strip().split(' ')[1])) for entry in lines]

        # track the coordinates of head and tail
        head = np.array([0, 0])
        tail = np.array([0, 0])

        # creates a set containing a single element of a tuple created from tail
        tail_positions = set([tuple(tail)])
        for direction, distance in movement_instructions:
            while distance > 0:
                head = self.update_head(head, direction)
                tail = self.update_tail(head, tail)
                tail_positions.add(tuple(tail))
                distance -= 1
        num_positions_tail_visits = len(tail_positions)
        print("The number of position the tail of the rope visits at least once is: ")
        print(num_positions_tail_visits)


obj = TestDay9Advent()
obj.part_one()
