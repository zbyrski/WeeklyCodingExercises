package MazeSolver;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private final int height;
    private final int width;
    //-1 means the cell isn't viable, 0 means the cell is a free space, and 1 means the cell has already been traveled to
    private final int[][] maze;
    private final List<String> path;

    public Maze(int height, int width){
        this.height = height;
        this.width = width;
        this.maze = new int[height][width];
        //add obstacles to the maze

        path = new ArrayList<>();
        findPath(0, 0);

    }

    //recursive, depth first search method to solve maze by "hugging the right wall"
    //if there is a valid path, returns true; if not, returns false
    public boolean findPath(int x, int y){

        //if the computer goes "out of bounds", reaches an obstacle or cell that was already reached
        if (x >= width || y >= height || maze[y][x] != 0){
            path.remove(path.size() - 1);
            return false;
        }
        //if the bottom-right of the maze has been reached, a path has been found
        if (x == width - 1 && y == height - 1) return true;

        //on each cell the number of available paths
        //this will take care of any cell(s) that only lead to dead ends
        int paths = calculatePaths(x, y);
        if (paths == 0){
            path.remove(path.remove(path.size() - 1));
            maze[y][x] = -1;
            return false;
        }
        //mark the cell as "travelled to"
        maze[y][x] = 1;

        //move through the maze, hugging the right wall
        //movement order: down, right, up, left
        return findPath(x, y + 1) || findPath(x + 1, y) || findPath(x, y - 1) || findPath(x - 1, y);
    }


    private int calculatePaths(int x, int y){
        int paths = 0;
        if (x + 1 < width && maze[y][x + 1] == 0) paths++;
        if (x - 1 <= 0 && maze[y][x - 1] == 0) paths++;
        if (y + 1 < height && maze[y + 1][x] == 0) paths++;
        if (y - 1 <= 0 && maze[y - 1][x] == 0) paths++;
        return paths;
    }

    public List<String> getPath(){
        return path;
    }
}
