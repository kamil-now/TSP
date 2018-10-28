import java.util.ArrayList;
import java.util.Collections;

public class Element {
    private int _fitness;
    private int[] _data;

    public Element(int[] data) {
        _data = data;
        _fitness = -1;
    }

    public int getFitness(int[][] info) {
        if (_fitness == -1) {
            _fitness = 0;
            int previous = _data[0];

            for (int i = 1; i < _data.length; i++) {

                _fitness += info[previous][i];
            }
        }
        return _fitness;
    }

    public static Element getRandom(int dataLength) {
        ArrayList<Integer> list = Helper.getRangeList(dataLength);
        Collections.shuffle(list);
        return new Element(Helper.toArray(list));
    }

    public boolean isEqual(Element element) throws Exception {
        if (_data.length != element._data.length) {
            throw new Exception("ELEMENT COMPARISON ERROR");
        }
        int length = element._data.length;
        for (int i = 0; i < length; i++) {
            if (this._data[i] != element._data[i])
                return false;
        }
        return true;
    }
    public boolean isBetter(Element element, int[][] fitnessInfo) {
        if (this.getFitness(fitnessInfo) > element.getFitness(fitnessInfo)) {
            return true;
        }
        return false;
    }
    public void display(){
        Helper.displayArray(_data);
    }
}
