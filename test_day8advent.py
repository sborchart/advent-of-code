import numpy as np

# import file
with open('/Users/sophieborchart/advent_of_code/day8input.txt', 'r') as f:
    lines = f.readlines()
    # remove all whitespaces from each line
    lines = [entry.strip() for entry in lines]

# create an array of 0's with rows len(lines) and columns len(lines[0])
trees = np.zeros((len(lines), len(lines[0])))
# form a 2D array of the trees (numpy because it uses less memory to store data but can be done regularly)
for i, line in enumerate(lines):
    trees[i, :] = np.array(list(line))

# calculate the number of trees on the edge since they're always visible
# by counting the number of rows minus the first and last to get the inner row values and
# add the value of the two columns to eliminate duplicates
num_visible_trees = (2 * (len(lines) - 2)) + (2 * len(lines[0]))

# loop through all of the trees in the array (except for the edge trees)
for i in range(1, trees.shape[0] - 1):
    for j in range(1, trees.shape[1] - 1):
        # gets the height differences between a tree and the rest of the trees in the same column, then row
        tree_columns = trees[:, j] - trees[i, j]
        tree_rows = trees[i, :] - trees[i, j]
        # makes a list of four arrays that contain the differences for the up/down/left/right trees
        trees_to_consider = [tree_rows[:j], tree_rows[j + 1:], tree_columns[:i], tree_columns[i + 1:]]
        # if ALL values either to the left/right/top/ bottom < 0, those trees are smaller than the current tree and is visible
        if sum(list(map(lambda direction: (direction < 0).all(), trees_to_consider))) > 0:
            num_visible_trees += 1

# print the answer
print("Number of visible trees is: ")
print(num_visible_trees)