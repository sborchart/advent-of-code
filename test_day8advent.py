import numpy as np

# import file
with open('/Users/sophieborchart/advent_of_code/day8input.txt', 'r') as f:
    lines = f.readlines()
    # remove all whitespaces from each line
    lines = [entry.strip() for entry in lines]




# PART 1

def part_1(tree_columns, tree_rows, i, j, num_visible_trees):
    # makes a list of four arrays that contain the differences for the up/down/left/right trees
    trees_to_consider = [tree_rows[:j], tree_rows[j + 1:], tree_columns[:i], tree_columns[i + 1:]]
    # if ALL values either to the left/right/top/ bottom < 0, those trees are smaller than the current tree and is visible
    if sum(list(map(lambda direction: (direction < 0).all(), trees_to_consider))) > 0:
        num_visible_trees += 1
    return num_visible_trees

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
        # trees[:, j] selects all elements in the j-th column of trees,
        # then it calculates the difference between the values in the j column and the value at (i,j) in the i row
        tree_columns = trees[:, j] - trees[i, j]
        tree_rows = trees[i, :] - trees[i, j]
        num_visible_trees = part_1(tree_columns, tree_rows, i, j, num_visible_trees)

# print the answer
print("Number of visible trees is: ")
print(num_visible_trees)





# PART 2


# same code to create a 2D array of 0's with rows len(lines) and columns len(lines[0]) for the scenic scores
# (need to make a new one since the original from part 1 has been modified)
scenic_scores = np.zeros((len(lines), len(lines[0])))


def part_2(tree_columns, tree_rows, i, j, scenic_scores):
    # start at column j - 1 up to the beginning of the array in iteraions of - 1 to go from right to left, etc
    directions = [tree_rows[j - 1:: - 1], tree_rows[j + 1:], tree_columns[i - 1:: - 1], tree_columns[i + 1:]]
    # using map will apply to all directions, multiply the 4 scores together to get answer
    scenic_scores[i, j] = np.prod(list(map(compute_scenic_score, directions)))
    return scenic_scores[i, j]

def compute_scenic_score(route):
    # determine which trees are taller (>=0) by determining the difference to the current tree in the loop
    tallest_trees_array = list(route >= 0)
    if True in tallest_trees_array:
        # get the first index that is taller or equally as tall,
        # index(True) returns the index of the first element in list with value True
        return tallest_trees_array.index(True) + 1
    else:
        # no routes >= 0, so the scenic score is the maximum possible length of the route
        return len(tallest_trees_array)

# loop through all of the trees in the array (except for the edge trees, since their scenic scores will always be 0)
for i in range(1, trees.shape[0] - 1):
    for j in range(1, trees.shape[1] - 1):
        tree_columns = trees[:, j] - trees[i, j]
        tree_rows = trees[i, :] - trees[i, j]
        scenic_scores[i, j] = part_2(tree_columns, tree_rows, i, j, scenic_scores)
max_value = int(np.max(scenic_scores))
print("Highest scenic score possible for any tree: ")
print(max_value)