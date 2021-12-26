package com.dz.os;

import com.dz.os.exceptions.OSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Functions {

    /**
     * Сдвиг в лево на number позиций.
     * Пустые ячейки заполняются значением -1.
     *
     * @param number         на сколько производить сдвиг влево
     * @param vector         вектор значений
     * @param emptyElemValue чем заполнять пустые ячейки
     * @throws OSException
     */
    public static void moveLeftOn(int number, List<Integer> vector, Integer emptyElemValue) throws OSException {
        if (vector.isEmpty())
            throw new OSException("Размер вектора не может быть равным 0!");
        if (number <= 0)
            throw new OSException("Сдвиг не может быть <= 0!");

        for (int i = 0; i < number; i++) {
            for (int j = 1; j <= vector.size() - 1; j++) {
                vector.set(j - 1, vector.get(j));
                if (j == vector.size() - 1)
                    vector.set(j, emptyElemValue);
            }
        }
    }


    /**
     * Деление информационного вектора на вектор пораждающего полинома
     *
     * @param v информационный вектор
     * @param g образующий полином
     * @return остаток от деления
     * @throws OSException
     */
    public static List<Integer> division(List<Integer> v, List<Integer> g) throws OSException {

        while (v.get(0) != 1) {
            v.remove(0);
        }

        if(v.size() < g.size()) return v;

        int cursorOnV = 0;

        List<Integer> a = new ArrayList<>();
        List<Integer> r = new ArrayList<>();

        // записываем в вектор a первые r значений вектора v
        for (int i = 0; i <= g.size() - 1; i++) {
            a.add(v.get(i));
        }

        // указывает на следующий элемент вектора v
        cursorOnV = g.size();

        while (cursorOnV <= v.size()) {
            r = Functions.xor(a, g);

            // сдвигаем влево и берем еще один разряд вектора v,
            // пока первый разряд вектора r не будет равен 1
            if (cursorOnV == v.size()) return r;
            while (r.get(0) != 1) {
                if (cursorOnV == v.size()) return r;
                Functions.moveLeftOn(1, r, -1);
                r.set(r.size() - 1, v.get(cursorOnV));
                cursorOnV++;
            }
            copy(r, a);
        }
        return r;
    }

    /**
     * Операция xor
     *
     * @param a первый вектор
     * @param b второй вектор
     * @return вектор результат операции a xor b
     * @throws OSException
     */
    public static List<Integer> xor(List<Integer> a, List<Integer> b) throws OSException {
        if (a == null || b == null)
            throw new OSException("Векторы не могуть быть null!");
        if (a.size() != b.size())
            throw new OSException("Размеры векторов a и b для выполнения операции xor должны быть равны!");

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= a.size() - 1; i++) {
            result.add((a.get(i) == b.get(i)) ? 0 : 1);
        }
        return result;

    }

    /**
     * Копирование вектора from в вектор to
     *
     * @param from откуда копировать
     * @param to   куда копировать
     * @throws OSException
     */
    public static void copy(List<Integer> from, List<Integer> to) throws OSException {
        if (from == null || to == null)
            throw new OSException("Значения векторов не могут быть null!");

        if (from.size() != to.size())
            throw new OSException("Значения векторов не могут быть null!");

        for (int i = 0; i < from.size(); i++) {
            to.set(i, from.get(i));
        }
    }


    /**
     * Конкатенирует информационный вектор с остатком от деления
     *
     * @param vector информационный вектор
     * @param rest   остаток от деления информационного вектора на вектор пораждающего полинома
     * @throws OSException
     */
    public static void concat(List<Integer> vector, List<Integer> rest) throws OSException {
        if (vector == null || rest == null)
            throw new OSException("Значения векторов не могут быть null!");

        for (int i = vector.size() - 1, j = rest.size() - 1; j != -1; i--, j--) {
            if (rest.get(j) != 0)
                vector.set(i, rest.get(j));
        }
    }

    /**
     * Вычисление факториала числа
     *
     * @param a число
     * @return факториал числа
     */
    public static Long getFactorial(Integer a) {
        if (a <= 1) {
            return 1L;
        } else {
            return a * getFactorial(a - 1);
        }
    }

    /**
     * Число сочетаний из N по M
     *
     * @param n N
     * @param m M
     * @return число сочетаний
     * @throws OSException
     */
    public static Long combine(Integer n, Integer m) throws OSException {
        if (n <= 0 || m <= 0)
            throw new OSException("N и M не могут быть отрицательными");

        return Functions.getFactorial(n) / (Functions.getFactorial(m) * Functions.getFactorial(n - m));
    }

    static int factorial(int n) {
        return (n == 1) ? 1 : n * factorial(n - 1);
    }

    static Map<Integer, List<List<Integer>>> createErrors() {
        Integer lengthErrCount = 0;
        Map<Integer, List<List<Integer>>> errorMap = new HashMap<>();

        final Integer maxNum = 32767;

        for (int i = 1; i <= maxNum; i++) {

            Integer help = i;
            List<Integer> error = new ArrayList<>();

            for(int j =0; j < 15; j++){
                error.add(0);
            }

            int ind= 14;
            while (help / 2 >= 1) {
               int wholePart = help / 2;
                int rest = help % 2;
                help = wholePart;
                error.set(ind, rest);
                ind--;
            }
            error.set(ind, help);

            if(error.size() != 15) System.out.println("i = " + i);

            Integer errorCount = countErr(error);

            if(errorMap.containsKey(errorCount)) {
                errorMap.get(errorCount).add(error);
            }else{
                List<List<Integer>> errors = new ArrayList<>();
                errors.add(error);
                errorMap.put(errorCount, errors);
            }
        }
        return errorMap;
    }


    static int countErr(List<Integer> err){
        Integer errNum = 0;
        for(Integer el : err){
            if(el == 1)
                errNum++;
        }
        return errNum;
    }


    public static void sumVectorAndError(List<Integer> v, List<Integer> err) throws OSException {
        if(v.size() != err.size()) throw new OSException(String.format("Длина вектора: %s . Длина ошибки: %s", v.size(), err.size()));
        for(int i = 0; i < v.size(); i++){
            if(err.get(i) == 1)
                v.set(i, 1);
        }

    }


    public static boolean contain1(List<Integer> v){
        for(Integer el : v){
            if(el == 1) return true;
        }
        return false;
    }


}
