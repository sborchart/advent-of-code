import numpy as np

class TestDay9Advent:

    def open_file(self):
        with open('/Users/sophieborchart/advent_of_code/day9input.txt', 'r') as f:
            lines = f.readlines()
            movements = [(entry.strip().split(' ')[0], 
                            int(entry.strip().split(' ')[1])) for entry in lines]
        return movements

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
            (0, 2):(0, 1),
            # part 2 stuff - very similar, just 4 more movement possibilities since tails can move diagonally
            # when being pulled while head can only move up/down/L/R so the pulled tails have 4 more possible directions
            (2, 2):(1, 1),
            (-2, -2):(-1, -1),
            (-2, 2):(-1, 1),
            (2, -2):(1, -1) }
        # calculate difference of distance
        difference = head - tail
        # print(difference)
        # print(change_for_tail.get(tuple(difference)))
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

    def both_parts(self):
        movement_instructions = self.open_file()
        # track the coordinates of head and tail
        head = np.array([0, 0])
        tail = np.array([0, 0])

        # creating a rope with 10 parts - 1 head, 9 parts that follow head
        rope = [np.array([0,0]) for i in range(10)]

        # creates a set containing a single element of a tuple created from tail
        tail_positions_part_one = set([tuple(tail)])
        # creates a set containing the rope, like in part 1
        tail_positions_part_two = set([tuple(rope[9])])

        for direction, distance in movement_instructions:
            # updates head, and then updates tail based off the head movement
            while distance > 0:
                # part 1 stuff
                head = self.update_head(head, direction)
                tail = self.update_tail(head, tail)
                tail_positions_part_one.add(tuple(tail))
                distance -= 1
                
                # rope[0] acts as head
                rope[0] = self.update_head(rope[0], direction)

                # needs to update tail all 10 times instead of just once like before
                for i in range(1, len(rope)):
                    rope[i] = self.update_tail(rope[i - 1], rope[i])
                tail_positions_part_two.add(tuple(rope[9]))

        num_positions_tail_visits = len(tail_positions_part_one)
        answer = len(tail_positions_part_two)

        # just need the length of visited tail positions for the answer to both parts
        print("PART ONE: The number of position the tail of the rope visits at least once is: ")
        print(num_positions_tail_visits)

        print("PART TWO: The number of position the tail of the rope visits at least once is: ")
        print(answer)



obj = TestDay9Advent()
obj.both_parts()
