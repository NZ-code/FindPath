import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
       char[][] twoDimArray =  {{'0','0','0','0','0','0'},
                                {'0','0','0','0','0','0'},
                                {'0','0','0','S','0','#'},
                                {'0','0','0','0','0','0'},
                                {'0','0','0','0','0','0'},
                                {'0','0','0','0','0','E'}};
        Finder map = new Finder();
        List<Point> path = new ArrayList<>();
        //map.find_path_DFS(twoDimArray, map.getStart(twoDimArray),path);
        Finder.printArray(twoDimArray);
        map.find_path_Dijkstra(twoDimArray);
        Finder.printArray(twoDimArray);
    }
}
