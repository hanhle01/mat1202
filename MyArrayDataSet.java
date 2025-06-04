package hus.oop.fraction;

public class MyArrayDataSet implements MyDataSet {
    private static int DEFAULT_CAPACITY = 16;
    private MyFraction[] fractions;
    private int length;

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số có kích thước là DEFAULT_CAPACITY.
     */
    public MyArrayDataSet() {
        this.fractions = new MyFraction[DEFAULT_CAPACITY];
        this.length = 0;
    }

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số truyền vào.
     * @param fractions
     */
    public MyArrayDataSet(MyFraction[] fractions) {
        if (fractions == null) {
            this.fractions = new MyFraction[DEFAULT_CAPACITY];
            this.length = 0;
        } else {
            this.fractions = new MyFraction[Math.max(fractions.length, DEFAULT_CAPACITY)];
            for (int i = 0; i < fractions.length; i++) {
                this.fractions[i] = new MyFraction(fractions[i]);
            }
            this.length = fractions.length;
        }
    }

    /**
     * Phương thức chèn phân số fraction vào vị trí index.
     * Nếu index nằm ngoài đoạn [0, length] thì không chèn được vào.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction là một phân số.
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (fraction == null || !checkBoundaries(index, length)) {
            return false;
        }

        if (length >= fractions.length) {
            allocateMore();
        }

        // Dịch chuyển các phần tử về phía sau
        for (int i = length; i > index; i--) {
            fractions[i] = fractions[i - 1];
        }

        fractions[index] = new MyFraction(fraction);
        length++;
        return true;
    }

    /**
     * Phương thức thêm phân số fraction vào vị trí cuối cùng chưa có dứ liệu của mảng data.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean append(MyFraction fraction) {
        if (fraction == null) {
            return false;
        }

        if (length >= fractions.length) {
            allocateMore();
        }

        fractions[length] = new MyFraction(fraction);
        length++;
        return true;
    }

    @Override
    public MyArrayDataSet toSimplify() {
        MyArrayDataSet result = new MyArrayDataSet();
        for (int i = 0; i < length; i++) {
            MyFraction simplified = new MyFraction(fractions[i]);
            simplified.simplify();
            result.append(simplified);
        }
        return result;
    }

    @Override
    public MyArrayDataSet sortIncreasing() {
        MyArrayDataSet result = new MyArrayDataSet();

        // Copy all fractions to result
        for (int i = 0; i < length; i++) {
            result.append(new MyFraction(fractions[i]));
        }

        // Bubble sort
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result.fractions[j].compareTo(result.fractions[j + 1]) > 0) {
                    MyFraction temp = result.fractions[j];
                    result.fractions[j] = result.fractions[j + 1];
                    result.fractions[j + 1] = temp;
                }
            }
        }

        return result;
    }

    @Override
    public MyArrayDataSet sortDecreasing() {
        MyArrayDataSet result = new MyArrayDataSet();

        // Copy all fractions to result
        for (int i = 0; i < length; i++) {
            result.append(new MyFraction(fractions[i]));
        }

        // Bubble sort (decreasing order)
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                int comparison = result.fractions[j].compareTo(result.fractions[j + 1]);
                if (comparison < 0 || (comparison == 0 && result.fractions[j].getDenominator() < result.fractions[j + 1].getDenominator())) {
                    MyFraction temp = result.fractions[j];
                    result.fractions[j] = result.fractions[j + 1];
                    result.fractions[j + 1] = temp;
                }
            }
        }

        return result;
    }

    @Override
    public String myDataSetToString() {
        if (length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            sb.append(fractions[i].toString());
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }

    /**
     * Phương thức mở rộng kích thước mảng gấp đôi, bằng cách tạo ra mảng mới có kích thước
     * gấp đôi, sau đó copy dự liệu từ mảng cũ vào.
     */
    private void allocateMore() {
        int newCapacity = fractions.length * 2;
        MyFraction[] newFractions = new MyFraction[newCapacity];
        for (int i = 0; i < length; i++) {
            newFractions[i] = fractions[i];
        }
        fractions = newFractions;
    }

    /**
     * Phương thức kiểm tra xem index có nằm trong khoảng [0, upperBound] hay không.
     * @param index
     * @param upperBound
     * @return true nếu index nằm trong khoảng [0, upperBound], false nếu ngược lại.
     */
    private boolean checkBoundaries(int index, int upperBound) {
        return index >= 0 && index <= upperBound;
    }
}