import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.IntStream;

public class Element {
    private int[] _data;
    private int _fitness;
    private int _value;

    public int get(int index) {
        return _data[index];
    }

    public int getFitness() {
        return _fitness;
    }

    public int getValue(int[][] info) {
        if (_value == 0) {
            int previous = _data[0];

            for (int i = 1; i < _data.length; i++) {

                _value += info[previous][i];
            }
        }

        return _value;
    }

    public void setFitness(int value) {
        _fitness = value;
    }

    public int size() {
        return _data.length;
    }

    public Element(int[] data) {
        _data = data;
    }

    public Element(int[] data, int fitness) {
        _data = data;
        _fitness = fitness;
    }

    public Element(int size) {
        _data = new int[size];
        for (int i = 0; i < size; i++) {
            _data[i] = -1;
        }
    }

    public void mutate() {
        Random rand = new Random();
        for (int i = 0; i < _data.length; i++) {
            boolean mutate = rand.nextInt(2) == 1;
            if (mutate) {
                int tmp = 0;
                while (tmp == 0)
                    tmp = rand.nextInt(_data.length);
                _data[i] = tmp;
            }
        }
    }

    public Element crossover(Element element, int cross1, int cross2) throws Exception {
        int[] tab = element._data;
        int size = tab.length;
        int[] retval = new int[size];
        for (int i = 0; i < size; i++) {
            retval[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            if (i >= cross1 && i <= cross2) {
                retval[i] = this._data[i];
            } else {
                int tmp = this._data[i];
                while (Helper.arrayContains(retval, tmp)) {
                    tmp = tab[Helper.getIndex(retval, tmp)];
                }
                retval[i] = tmp;
            }
        }

        return new Element(retval);
    }

    public Element copy() {
        return new Element(_data, _fitness);
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

    public boolean isBetter(Element element) {
        if (this.getFitness() > element.getFitness()) {
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println(toString());
    }

    public String toString() {
        StringJoiner sj = new StringJoiner("-");
        IntStream.of(_data).forEach(x -> sj.add(String.valueOf(x)));
        return sj.toString();
    }

    public static Element getRandom(int dataLength) {
        ArrayList<Integer> list = Helper.getRangeList(dataLength);
        Collections.shuffle(list);
        return new Element(Helper.toArray(list));
    }

}
