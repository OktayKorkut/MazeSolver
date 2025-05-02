import org.w3c.dom.Node;

import java.util.Scanner;
import java.util.Stack;

public class MazeSolverProject {

    public static void main(String[] args) {
        int grid = 5;

        int[][] maze = getMaze(grid);

        Stack<int[]> path = new Stack<>();

        maze[1][1] = 2;
        MazeUtility.plotMaze(maze);
        path.push(new int[]{1, 1});
        while(!(maze[2*grid-1][2*grid-1] == 2)){
            int[] currentPosition = path.peek();

            while(!(maze[2*grid-1][2*grid-1] == 2)) {
                int direction = findDirectionToMove(maze, currentPosition,path);

                if(direction == -1){
                    int lastX = path.peek()[1];
                    int lastY = path.peek()[0];

                    while(howManyPossibleWay(maze,currentPosition) < 3) {
                        if(currentPosition[0] == 1 && currentPosition[1] == 1){
                            break;
                        }

                        lastX = path.peek()[1];
                        lastY = path.peek()[0];
                        path.pop();
                        maze[currentPosition[0]][currentPosition[1]] = 0;
                        currentPosition = path.peek();
                        maze[currentPosition[0]][currentPosition[1]] = 2;
                        MazeUtility.plotMaze(maze);
                    }

                    int[] dontGo = new int[]{lastY, lastX};
                    direction = findDirectionButWithNotToGo(maze, currentPosition, dontGo);
                    currentPosition = move(maze, currentPosition, direction);
                    path.push(currentPosition);

                    break;
                } else {
                    currentPosition = move(maze, currentPosition, direction);
                    path.push(currentPosition);
                    break;
                }
            }

            MazeUtility.plotMaze(maze);
        }

        printStackStepByStep(path);
    }

    public static int[][] getMaze(int grid) {
        MazeGenerator maze = new MazeGenerator(grid);
        String str = maze.toString();

        int[][] maze2D = MazeUtility.Convert2D(str);
        return maze2D;
    }

    public static boolean isThereAnything(int[][] maze, int y, int x){
        if(x == 0 || y == 0 || x == maze.length-1 || y == maze.length-1){
            return true;
        }

        return maze[y][x] == 1;
    }

    private static int[] move(int[][] maze, int[] currentPosition, int direction) {
        int newX = 0;
        int newY = 0;

        switch (direction) {
            case 0: // down
                newY = currentPosition[0]+1;
                newX = currentPosition[1];
                break;
            case 1: // right
                newY = currentPosition[0];
                newX = currentPosition[1]+1;
                break;
            case 2: // up
                newY = currentPosition[0]-1;
                newX = currentPosition[1];
                break;
            case 3: // left
                newY = currentPosition[0];
                newX = currentPosition[1]-1;
                break;
            default:
                break;
        }

        if (newX < 0 || newX >= maze.length-1 || newY < 0 || newY >= maze[0].length-1) {
            return currentPosition;
        }

        if (maze[newY][newX] == 0) {
            maze[currentPosition[0]][currentPosition[1]] = 0;
            maze[newY][newX] = 2;
            return new int[]{newY, newX};
        } else {
            return currentPosition;
        }
    }

    public static int findDirectionToMove(int[][] maze, int[] current, Stack<int[]> path){
        int[] up = new int[]{current[0]-1, current[1]};
        int[] down = new int[]{current[0]+1, current[1]};
        int[] left = new int[]{current[0], current[1]-1};
        int[] right = new int[]{current[0], current[1]+1};
        int[][] directions = new int[][]{down, right, up, left};

        int[] temp = path.peek();
        if(!path.empty()) {
            path.pop();
        }

        for (int i = 0; i < directions.length; i++) {
            if(!path.empty()) {
                if (directions[i][0] == path.peek()[0] && directions[i][1] == path.peek()[1]) {
                    continue;
                }
            }

            if (isThereAnything(maze, directions[i][0], directions[i][1])) {
                continue;
            }

            path.push(temp);

            return i;
        }

        return -1;
    }

    public static int howManyPossibleWay(int[][] maze, int[] current) {
        int count = 0;

        if(maze[current[0]-1][current[1]] == 0) {
            count++;
        }

        if(maze[current[0]+1][current[1]] == 0) {
            count++;
        }

        if(maze[current[0]][current[1]-1] == 0) {
            count++;
        }

        if(maze[current[0]][current[1]+1] == 0) {
            count++;
        }

        return count;
    }

    public static int findDirectionButWithNotToGo(int[][] maze, int[] current, int[] notToGo){
        int[] up = new int[]{current[0]-1, current[1]};
        int[] down = new int[]{current[0]+1, current[1]};
        int[] left = new int[]{current[0], current[1]-1};
        int[] right = new int[]{current[0], current[1]+1};
        int[][] directions = new int[][]{down, right, up, left};

        for (int i = 0; i < directions.length; i++) {
            if (directions[i][0] == notToGo[0] && directions[i][1] == notToGo[1]) {
                continue;
            }

            if (isThereAnything(maze, directions[i][0], directions[i][1])) {
                continue;
            }

            return i;
        }

        return -1;
    }

    public static void printStackStepByStep(Stack<int[]> path) {
        Stack<int[]> temp = new Stack<>();
        while(!path.empty()) {
            temp.push(path.pop());
        }

        while(!temp.empty()) {
            int[] current = temp.pop();
            System.out.print("(" + current[0] + ", " + current[1] + ")");

            if(!temp.empty()) {
                System.out.print("->");
            }
        }
    }
}