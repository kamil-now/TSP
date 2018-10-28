import java.io.FileNotFoundException;
import java.util.Random;

public class Main {
    static String _filePath = "berlin52.txt";
    static Random _rand = new Random();
    static int _populationSize = 50;
    static int _size;
    static int _length;
    static int[][] _fitnessInfo;
    static int optimalFitness = 10000;


    public static void main(String[] args) throws FileNotFoundException {
        _fitnessInfo = FileReaderWriter.readFile(System.getProperty("user.dir") + "\\" + _filePath);
        //Helper.displayArray(_fitnessInfo);
        Population initPopulation = new Population(_fitnessInfo, _populationSize, true);
        initPopulation.displayElements(_fitnessInfo);

    }

}
