# import the os module
import os

# read the input file
puzzle_input = '/Users/sophieborchart/advent_of_code/day7input.txt'

class Day7:
    def day7_part1(self):
        with open(puzzle_input, 'r') as f:
            lines = f.readlines()
            # make an array of each line without whitespaces
            lines = [line.strip() for line in lines]

        tree = Tree()
        while len(lines) > 0:
            line = lines.pop(0)
            # if we're changing directories, start back at the root
            if line == '$ cd /': 
                tree.current = tree.root
            # if it's listing dir contents, grab the sizes + names of each file / add dir to tree path if it's a dir
            elif line == '$ ls':
                while len(lines) > 0 and '$' not in lines[0]:
                    line = lines.pop(0)
                    size, name = line.split(' ')
                    if size.isdigit():
                        new_node = TreeNode(is_directory = False, name = name, file_size = int(size))
                    else:
                        new_node = TreeNode(is_directory = True, name = name)
                    # adds child to directory whether it's a new dir or file
                    tree.add_child(new_node)
            # $ = commands
            # moving up a dir so get the parent
            elif line == '$ cd ..':
                tree.current = tree.current.parent
            # switching to new dir by splitting the line and getting 3rd item in split
            elif '$ cd' in line:
                _, _, name = line.split(' ')
                result = filter(lambda child: child.name == name, tree.current.children)
                tree.current = list(result)[0]

        # now that the tree has been traversed and we have our info, we can calculate the size of the total of all dirs <= 10,00
        all_dirs_size = tree.root.find_dirs()
        print(all_dirs_size)



class Tree:
    def __init__(self) -> None:
        self.root = TreeNode(is_directory = True, name = "root_dir")
        self.current = self.root 

    def add_child(self, child):
        self.current.treenode_add_child(child)


        
class TreeNode:
    # items each line in the array will contain: is/isn't dir, name, size (if relevant), children (if relevant)
    def __init__(self, is_directory: bool, name, file_size = None) -> None:
        self.is_dir = is_directory
        self.name = name
        self.size = file_size
        self.children = []

    # get size: if size is directory, it will traverse all children's sizes. otherwise, it is a file and will return the file's size
    def get_size(self):
        total_size = 0
        if self.is_dir:
            for child in self.children:
                # recursively gets size
                total_size += child.get_size()
            return total_size
        else:
            return self.size

    # get the child's parent and add the child to that parent
    def treenode_add_child(self, child):
        child.parent = self
        self.children.append(child)
    
    # find all of the directories with a total size of at most 100000
    def find_dirs(self):
        dir_sizes = 0
        if self.is_dir:
            for child in self.children:
                # adds the file size if it's less than 10,000, else continues to iterate through the dirs recrively
                if child.is_dir and child.get_size() <= 100000:
                    dir_sizes += child.get_size() + child.find_dirs()
                else:
                    dir_sizes += child.find_dirs()
        return dir_sizes

    def test_day7_part1(self):
        assert (self.day7_part1('/Users/sophieborchart/advent_of_code/day7input.txt')) == "1315285"


# run the code 
obj = Day7()
obj.day7_part1()