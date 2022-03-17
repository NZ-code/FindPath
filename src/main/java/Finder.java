import java.util.ArrayList;
import java.util.List;

public class Finder {

    private static int width = 6;
    private static int height = 6;
    public void setSymbol(char[][] map,int x,int y,char sign){
        map[x][y] = sign;
    }
    public static void printArray(char[][] map){
        for (int i=0;i<width;i++){
            for(int j =0; j< height;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    public Point getStart(char[][] my_map){
        for (int i=0;i<width;i++){
            for(int j =0; j< height;j++){
                if(my_map[i][j] == 'S')
                    return new Point(i,j);
            }

        }
        return new Point(-1,-1);
    }
    public Point getEnd(char[][] my_map){
        for (int i=0;i<width;i++){
            for(int j =0; j< height;j++){
                if(my_map[i][j] == 'E')
                    return new Point(i,j);
            }

        }
        return new Point(-1,-1);
    }
    List<Point> getAllMoves(char[][] my_map, Point start){
        List<Point> moves = new ArrayList<>();

        if(start.x - 1 >= 0 && my_map[start.x-1][start.y] != '.'){
            moves.add(new Point(start.x-1,start.y));
        }
        if(start.y + 1 < height && my_map[start.x][start.y+1] != '.'){
            moves.add(new Point(start.x,start.y+1));
        }
        if(start.y - 1 >= 0 && my_map[start.x][start.y-1] != '.'){

            moves.add(new Point(start.x,start.y-1));
        }


        if(start.x + 1 < width && my_map[start.x+1][start.y] != '.'){
            moves.add(new Point(start.x+1,start.y));
        }
        return moves;
    }
    boolean find_path_DFS(char[][] my_map,Point move, List<Point> path){
        Finder.printArray(my_map);
        System.out.println();
        if(my_map[move.x][move.y] == 'E') {
            path.add(move);
            return true;
        }
        Point start = getStart(my_map);
        setSymbol(my_map, start.x, start.y, '.');
        setSymbol(my_map, move.x, move.y, 'S');
        List<Point> moves = getAllMoves(my_map,move);
        for (Point next_move : moves){
            if(find_path_DFS(my_map, next_move,path)){
                path.add(move);
                return true;
            }
        }
        return false;
    }
    boolean find_path_Dijkstra(char[][] my_map) {
        int distances[][] = new int[width][height];
        Point parent[][] = new Point[width][height];
        List<Point> kolejka = new ArrayList<>();
        for (int i=0;i<width;i++){
            for(int j =0; j< height;j++){
                distances[i][j] = Integer.MAX_VALUE;
                kolejka.add(new Point(i,j));
            }
        }
        Point start = getStart(my_map);
        distances[start.x][start.y] = 0;

        Boolean is_end= false;
        while(!kolejka.isEmpty() && !is_end){
            Point smallestPoint = new Point(-1,-1);
            int smallest_distance = Integer.MAX_VALUE;
            for (Point p : kolejka){
                if(smallest_distance > distances[p.x][p.y]){
                    smallest_distance = distances[p.x][p.y];
                    smallestPoint = p;
                }
            }
            kolejka.remove(smallestPoint);
            List<Point>  moves = getAllMoves(my_map,smallestPoint);
            for (Point move : moves){
                if(distances[move.x][move.y] > distances[smallestPoint.x][smallestPoint.y] + 1){
                    distances[move.x][move.y] =  distances[smallestPoint.x][smallestPoint.y] + 1;
                    parent[move.x][move.y] = smallestPoint;
                    if(my_map[move.x][move.y] == 'E') {
                        is_end = true;
                        break;
                    }
                }
            }
        }
        Point point = getEnd(my_map);
        while (!point.equals(start)){
            my_map[point.x][point.y] = '*';
            System.out.println(point);
            point = parent[point.x][point.y];

        }
        return false;
    }
}
